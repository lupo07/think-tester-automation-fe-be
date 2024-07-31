package front.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import front.common.ConfigReader;
import front.common.ConstantsFront;
import front.common.ExplicitWaits;
import front.common.SeleniumActions;
import front.common.SupportValidations;

public class ContactsPage {
	WebDriver driver;
	SeleniumActions selact;
	ExplicitWaits expw;
	SupportValidations spv;
	ConfigReader config = new ConfigReader();;
	private static final Logger log = LogManager.getLogger(ContactsPage.class.getName());

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		expw = new ExplicitWaits(driver);
		selact = new SeleniumActions(driver);
		spv = new SupportValidations(driver);
	}

	@FindBy(xpath = "//h1")
	WebElement contactsHeader;

	@FindAll(@FindBy(xpath = "//tr[@class='contactTableBodyRow']"))
	List<WebElement> contactsTables;

	@FindBy(xpath = "//button[@id='add-contact']")
	WebElement addNewContactButton;

	/**
	 * 
	 * @param product
	 */
	public void verifyContactsPage(String product) {
		expw.waitForElementToBeClickable(addNewContactButton, ConstantsFront.EXPLICIT_WAIT_CONTACTS_PAGE);
		spv.verifyPageHeader(contactsHeader, product, ConstantsFront.EXPLICIT_WAIT_CONTACTS_PAGE);
	}

	/**
	 * 
	 */
	public void clickOnAddNewContactButton() {
		expw.waitForElementToBeClickable(addNewContactButton, ConstantsFront.EXPLICIT_WAIT_CONTACTS_PAGE);
		selact.clickOnElement(addNewContactButton);
		log.info("Click on Submit Button");
	}

	/**
	 * 
	 * @param row
	 */
	public void clickOnContactTables(int row) {
		expw.waitForVisibilityOfElement(contactsTables.get(row), ConstantsFront.EXPLICIT_WAIT_CONTACTS_PAGE);
		selact.clickOnElement(contactsTables.get(row));
		log.info("Click on Row: " + row);
	}
}
