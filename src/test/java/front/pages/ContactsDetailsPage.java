package front.pages;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import front.common.ConfigReader;
import front.common.ConstantsFront;
import front.common.ExplicitWaits;
import front.common.SeleniumActions;
import front.common.SupportValidations;

public class ContactsDetailsPage {
	WebDriver driver;
	SeleniumActions selact;
	ExplicitWaits expw;
	SupportValidations spv;
	ConfigReader config = new ConfigReader();;
	private static final Logger log = LogManager.getLogger(ContactsDetailsPage.class.getName());

	public ContactsDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		expw = new ExplicitWaits(driver);
		selact = new SeleniumActions(driver);
		spv = new SupportValidations(driver);
	}

	@FindBy(xpath = "//h1")
	WebElement contactsDetailsHeader;

	@FindBy(xpath = "//button[@id='delete']")
	WebElement deleteButton;
	
	@FindBy(xpath = "//button[@id='edit-contact']")
	WebElement editButton;

	/**
	 * 
	 * @param product
	 */
	public void verifyContactsDetailsPage(String product) {
		spv.verifyPageHeader(contactsDetailsHeader, product, ConstantsFront.EXPLICIT_WAIT_CONTACT_DETAILS_PAGE);
	}

	/**
	 * 
	 */
	public void clickOnDeleteButton() {
		expw.waitForElementToBeClickable(deleteButton, ConstantsFront.EXPLICIT_WAIT_CONTACT_DETAILS_PAGE);
		expw.waitNoCondition(ConstantsFront.EXPLICIT_WAIT_SHORT_WAIT_MILLIS);
		deleteButton.click();
		expw.waitNoCondition(ConstantsFront.EXPLICIT_WAIT_SHORT_WAIT_MILLIS);
		log.info("Click on Submit Button");
	}
	
	/**
	 * 
	 */
	public void clickOnEditButton() {
		expw.waitForElementToBeClickable(editButton, ConstantsFront.EXPLICIT_WAIT_CONTACT_DETAILS_PAGE);
		selact.clickOnElement(editButton);
		log.info("Click on Edit Button");
	}
	
	/**
	 * 
	 */
	public void acceptPopUp() {
		expw.waitNoCondition(ConstantsFront.EXPLICIT_WAIT_SHORT_WAIT_MILLIS);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		log.info("The alert was displayed successfully");
	}

}
