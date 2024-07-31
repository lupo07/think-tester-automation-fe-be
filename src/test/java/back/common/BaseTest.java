package back.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import back.buider.APIsBuilder;
import back.request.ContactsRequest;
import back.request.LoginRequest;
import back.response.ContactsResponse;
import back.response.LoginResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseTest {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports report;
	public ExtentTest test;
	public APIsBuilder apiBuild;
	public LoginRequest logReq;
	public LoginResponse logResp;
	public ContactsRequest contactsReq;
	public ContactsResponse contactsResp;
	public RequestSpecification reqSpec;
	public ResponseSpecification respSpec;
	public Response response;
	public SetterGetters sg = new SetterGetters();
	public UsefulMethods um = new UsefulMethods();
	public ConfigReader config = new ConfigReader();
	private static final Logger log = LogManager.getLogger(BaseTest.class.getName());

	@BeforeClass
	public void beforeClass() throws Exception {
		// docker
	}

	@DataProvider(name = "addContactsTestData")
	public Object[][] addContactDataProvider() throws Exception {
		ExcelUtility.setExcelFile(Constants.FILE_PATH_CONTACTS_TEST + Constants.FILE_NAME_CONTACTS_TEST, "AddContactsTest");
		Object[][] testData = ExcelUtility.getTestData("add_contacts");
		return testData;
	}
	
	@DataProvider(name = "updateContactsTestData")
	public Object[][] updateContactDataProvider() throws Exception {
		ExcelUtility.setExcelFile(Constants.FILE_PATH_CONTACTS_TEST + Constants.FILE_NAME_CONTACTS_TEST, "UpdateContactsTest");
		Object[][] testData = ExcelUtility.getTestData("update_contacts");
		return testData;
	}
	
	@DataProvider(name = "deleteContactsTestData")
	public Object[][] deleteContactDataProvider() throws Exception {
		ExcelUtility.setExcelFile(Constants.FILE_PATH_CONTACTS_TEST + Constants.FILE_NAME_CONTACTS_TEST, "DeleteContactsTest");
		Object[][] testData = ExcelUtility.getTestData("delete_contacts");
		return testData;
	}

	@BeforeTest
	public void beforeTest() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm");
		LocalDateTime now = LocalDateTime.now();

		report = new ExtentReports();
		htmlReporter = new ExtentHtmlReporter("./Reports/Report_" + dtf.format(now).toString() + "_Suite.html");
		htmlReporter.loadXMLConfig("extent-config.xml");
		report.attachReporter(htmlReporter);
	}

	@BeforeMethod
	public void beforeMethod() {
		apiBuild = new APIsBuilder();
		logReq = new LoginRequest();
		logResp = new LoginResponse();
		contactsReq = new ContactsRequest();
		contactsResp = new ContactsResponse(); 
		ThreadContext.put("contextKey", this.getClass().getName());
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				log.info("======PASSED=====");
				test.log(Status.PASS, "The Test Case " + result.getName() + " is PASS");

			} else if (result.getStatus() == ITestResult.FAILURE) {
				log.info("======FAILED=====");
				test.log(Status.FAIL, "The Test Case " + result.getName() + " is FAIL");

				StringWriter sw = new StringWriter();
				result.getThrowable().printStackTrace(new PrintWriter(sw));
				String exStackTrace = sw.toString();
				log.info("Exception: " + exStackTrace);
				test.log(Status.FAIL, "The Test Case " + result.getName() + " is FAIL " + "with Error: "
						+ new RuntimeException(exStackTrace));

			} else if (result.getStatus() == ITestResult.SKIP) {
				log.info("======SKIPPED=====");
				test.log(Status.SKIP, "The Test Case " + result.getName() + " is SKIP");
				StringWriter sw = new StringWriter();
				result.getThrowable().printStackTrace(new PrintWriter(sw));
				String exStackTrace = sw.toString();
				log.info("Exception: " + exStackTrace);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("----------------------------------------------------------------------");
		log.info(" -------------- The Test " + result.getName() + " has been completed");
		log.info("----------------------------------------------------------------------");
	}

	@AfterTest
	public void afterTest() {
		report.flush();
	}

	@AfterClass
	public void afterClass() {
	}

}
