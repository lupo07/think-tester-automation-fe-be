package front.suites;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import front.common.ConstantsFront;
import front.common.BaseTestFront;

public class ContactsFrontTest extends BaseTestFront {
	private static final Logger log = LogManager.getLogger(ContactsFrontTest.class.getName());

	@Test(dataProvider = "addContactsFrontTD")
	public void addContact(String email, String password, String formValues) {
		log.info("The Test Suite " + ContactsFrontTest.class.getName() + " has started");
		test = report.createTest("Add a new Contact");
		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 1 Login Page ------------------");
		test.log(Status.INFO, "Step 1 Login Page");
		lp.verifyLoginPage(ConstantsFront.LOGIN_PAGE_HEADER);
		lp.fillLoginFields(email, password);
		lp.clickOnSubmit();
		
		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 2 Click on Add New Contact Button ------------------");
		test.log(Status.INFO, "Step 2 Click on Add New Contact Button");
		conpage.verifyContactsPage(ConstantsFront.CONTACTS_PAGE_HEADER);
		conpage.clickOnAddNewContactButton();
		
		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 3 Fill the Form ------------------");
		test.log(Status.INFO, "Step 3 Fill the Form");
		List<String> formValuesList = 
        		new ArrayList<>(Arrays.asList(formValues.split(","))); 
		addconpage.verifyAddContactsPage(ConstantsFront.ADD_CONTACTS_PAGE_HEADER);
		addconpage.fillFormInputs(formValuesList);
		
		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 4 Submit the Form ------------------");
		test.log(Status.INFO, "Step 4 Submit the Form");
		addconpage.clickOnSubmit();
			
	}
	
	@Test(dataProvider = "deleteContactsFrontTD")
	public void deleteContact(String email, String password, String row) {
		log.info("The Test Suite " + ContactsFrontTest.class.getName() + " has started");
		test = report.createTest("Add a new Contact");
		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 1 Login Page ------------------");
		test.log(Status.INFO, "Step 1 Login Page");
		lp.verifyLoginPage(ConstantsFront.LOGIN_PAGE_HEADER);
		lp.fillLoginFields(email, password);
		lp.clickOnSubmit();
		
		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 2 Click on an existing Contact Row ------------------");
		test.log(Status.INFO, "Step 2 Click on an existing Contact Row");	
		conpage.verifyContactsPage(ConstantsFront.CONTACTS_PAGE_HEADER);
		conpage.clickOnContactTables(um.stringToInt(row));
		
		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 3 Click on Delete button ------------------");
		test.log(Status.INFO, "Step 3 Click on Delete button");
		condetpage.verifyContactsDetailsPage(ConstantsFront.CONTACTS_DETAILS_PAGE_HEADER);
		condetpage.clickOnDeleteButton();
		condetpage.acceptPopUp();
			
	}
	
	@Test(dataProvider = "editContactsFrontTD")
	public void editContact(String email, String password, String row, String formEdit) {
		log.info("The Test Suite " + ContactsFrontTest.class.getName() + " has started");
		test = report.createTest("Add a new Contact");
		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 1 Login Page ------------------");
		test.log(Status.INFO, "Step 1 Login Page");
		lp.verifyLoginPage(ConstantsFront.LOGIN_PAGE_HEADER);
		lp.fillLoginFields(email, password);
		lp.clickOnSubmit();
		
		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 2 Click on an existing Contact Row ------------------");
		test.log(Status.INFO, "Step 2 Click on an existing Contact Row");	
		conpage.verifyContactsPage(ConstantsFront.CONTACTS_PAGE_HEADER);
		conpage.clickOnContactTables(um.stringToInt(row));
		
		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 3 Click on Delete button ------------------");
		test.log(Status.INFO, "Step 3 Click on Delete button");
		condetpage.verifyContactsDetailsPage(ConstantsFront.CONTACTS_DETAILS_PAGE_HEADER);
		condetpage.clickOnEditButton();
		
		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 4 Fill the Form ------------------");
		test.log(Status.INFO, "Step 4 Fill the Form");
		List<String> formValuesList = 
        		new ArrayList<>(Arrays.asList(formEdit.split(","))); 
		editconpage.fillFormInputs(formValuesList);
		editconpage.clickOnSubmit();
			
	}
}
