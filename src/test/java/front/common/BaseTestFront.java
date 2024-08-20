package front.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import back.common.ExcelUtility;
import front.pages.AddContactsPage;
import front.pages.ContactsDetailsPage;
import front.pages.ContactsPage;
import front.pages.EditContactsPages;
import front.pages.LoginPage;


public class BaseTestFront {
	public WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports report;
	public ExtentTest test;
	public DriverSetUp ds;
	public LoginPage lp;
	public ContactsPage conpage;
	public AddContactsPage addconpage;
	public UsefulMethodsFront um;
	public ContactsDetailsPage condetpage;
	public EditContactsPages editconpage;
	public DockerSet dock;
	public ConfigReader config = new ConfigReader();
	private static final Logger log = LogManager.getLogger(BaseTestFront.class.getName());

	@DataProvider(name = "addContactsFrontTD")
	public Object[][] updateContactDataProvider() throws Exception {
		ExcelUtility.setExcelFile(ConstantsFront.FILE_PATH_CONTACTS_TEST + ConstantsFront.FILE_NAME_CONTACTS_TEST,
				"AddContactsFront");
		Object[][] testData = ExcelUtility.getTestData("add_contacts_fr");
		return testData;
	}

	@DataProvider(name = "deleteContactsFrontTD")
	public Object[][] deletContactProvider() throws Exception {
		ExcelUtility.setExcelFile(ConstantsFront.FILE_PATH_CONTACTS_TEST + ConstantsFront.FILE_NAME_CONTACTS_TEST,
				"DeleteContactsFront");
		Object[][] testData = ExcelUtility.getTestData("delete_contacts_fr");
		return testData;
	}

	@DataProvider(name = "editContactsFrontTD")
	public Object[][] editContactProvider() throws Exception {
		ExcelUtility.setExcelFile(ConstantsFront.FILE_PATH_CONTACTS_TEST + ConstantsFront.FILE_NAME_CONTACTS_TEST,
				"EditContactsFront");
		Object[][] testData = ExcelUtility.getTestData("edit_contacts_fr");
		return testData;
	}

	@BeforeClass
	@Parameters({ "runType", "startDocker", "environment" })
	public void beforeClass(String runType, String startDocker, String env) throws Throwable, InterruptedException {

		File fi = new File(config.getStatusFile());
		if (fi.delete()) {
			log.info("The file was deleted successfully");
		}

		if (runType.equalsIgnoreCase("remote") && startDocker.equalsIgnoreCase("start")) {
			log.info("This suite is going to: " + startDocker + " a docker instance");
			dock = new DockerSet();
			log.info("Starting Docker instance");

			if (SystemUtils.IS_OS_WINDOWS) {
				log.info("Running on Windows");

				dock.starBat(config.getOperationsBatW().get(0), config.getOperationsBatW().get(1),
						config.getStatusFile(), config.getDockerStatusLine());
			} else if (SystemUtils.IS_OS_LINUX) {
				log.info("Running on Linux");

				dock.starBat(config.getOperationsBashLnx().get(0), config.getOperationsBashLnx().get(1),
						config.getStatusFile(), config.getDockerStatusLine());
			}

		}

		if (runType.equalsIgnoreCase("remote") && startDocker.equalsIgnoreCase("alone")) {
			log.info("This suite is going to run: " + startDocker + " a docker instance");
			dock = new DockerSet();
			log.info("Starting Docker instance");

			if (SystemUtils.IS_OS_WINDOWS) {
				log.info("Running on Windows");

				dock.starBat(config.getOperationsBatW().get(0), config.getOperationsBatW().get(1),
						config.getStatusFile(), config.getDockerStatusLine());
			} else if (SystemUtils.IS_OS_LINUX) {
				log.info("Running on Linux");

				dock.starBat(config.getOperationsBashLnx().get(0), config.getOperationsBashLnx().get(1),
						config.getStatusFile(), config.getDockerStatusLine());
			}

		}
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
	@Parameters({ "runType", "browser" })
	public void setClasses(String runType, String browser) throws MalformedURLException {
		ThreadContext.put("contextKey", this.getClass().getName());
		log.info("The Test Suite " + this.getClass().getName() + " has started");

		ds = new DriverSetUp(browser);

		driver = (runType.equals("local")) ? ds.driveReturn() : ds.driveReturn(config.getLocalURL());


		log.info(" Base Url: " + config.getBaseURL() + " Browser: " + browser);
		log.info(runType + " Driver: " + driver);

		lp = new LoginPage(driver);
		conpage = new ContactsPage(driver);
		addconpage = new AddContactsPage(driver);
		condetpage = new ContactsDetailsPage(driver);
		editconpage = new EditContactsPages(driver);
		um = new UsefulMethodsFront(driver);

		driver.get(config.getBaseURL());
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				log.info("======PASSED=====");
				test.log(Status.PASS, "The Test Case " + result.getName() + " is PASS");

			} else if (result.getStatus() == ITestResult.FAILURE) {
				log.info("======FAILED=====");
				String method = result.getName();
				String temp = um.getScreenshotBase64(driver, method);
				StringWriter sw = new StringWriter();
				result.getThrowable().printStackTrace(new PrintWriter(sw));
				String exStackTrace = sw.toString();
				log.info("Exception: " + exStackTrace);
				MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(temp)
						.build();
				test.log(Status.FAIL, new RuntimeException(exStackTrace), mediaModel);

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
		driver.quit();
	}

	@AfterTest
	public void afterTest() {
		report.flush();
	}

	@AfterClass
	@Parameters({ "runType", "startDocker" })
	public void afterClass(String runType, String startDocker) throws IOException, InterruptedException {
		if (runType.equalsIgnoreCase("remote") && startDocker.equalsIgnoreCase("stop")) {
			log.info("This suite is going to: " + startDocker + " a docker instance");
			dock = new DockerSet();
			log.info("Stopping Docker instance");
			if (SystemUtils.IS_OS_WINDOWS) {
				log.info("Running on Windows");

				dock.stopBat(config.getOperationsBatW().get(2), config.getOperationsBatW().get(1),
						config.getOperationsBatW().get(3), config.getStatusFile(), config.getDockerStopLine());

			} else if (SystemUtils.IS_OS_LINUX) {
				log.info("Running on Linux");

				dock.stopBat(config.getOperationsBashLnx().get(2), config.getOperationsBashLnx().get(1),
						config.getOperationsBashLnx().get(3), config.getStatusFile(), config.getDockerStopLine());
			}
		}

		if (runType.equalsIgnoreCase("remote") && startDocker.equalsIgnoreCase("alone")) {
			log.info("This suite is going to: " + startDocker + " a docker instance");
			log.info("Stopping Docker instance");
			if (SystemUtils.IS_OS_WINDOWS) {
				log.info("Running on Windows");

				dock.stopBat(config.getOperationsBatW().get(2), config.getOperationsBatW().get(1),
						config.getOperationsBatW().get(3), config.getStatusFile(), config.getDockerStopLine());

			} else if (SystemUtils.IS_OS_LINUX) {
				log.info("Running on Linux");

				dock.stopBat(config.getOperationsBashLnx().get(2), config.getOperationsBashLnx().get(1),
						config.getOperationsBashLnx().get(3), config.getStatusFile(), config.getDockerStopLine());
			}
		}
	}

}
