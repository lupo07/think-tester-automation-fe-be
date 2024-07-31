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

public class AddContactsPage {
	WebDriver driver;
	SeleniumActions selact;
	ExplicitWaits expw;
	SupportValidations spv;
	ConfigReader config = new ConfigReader();;
	private static final Logger log = LogManager.getLogger(AddContactsPage.class.getName());

	public AddContactsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		expw = new ExplicitWaits(driver);
		selact = new SeleniumActions(driver);
		spv = new SupportValidations(driver);
	}

	@FindBy(xpath = "//h1")
	WebElement addContactsHeader;

	@FindAll(@FindBy(xpath = "//input"))
	List<WebElement> formInputsElements;

	@FindBy(xpath = "//button[@id='submit']")
	WebElement submitButton;

	/**
	 * 
	 * @param product
	 */
	public void verifyAddContactsPage(String product) {
		spv.verifyPageHeader(addContactsHeader, product, ConstantsFront.EXPLICIT_WAIT_ADD_CONTACTS_PAGE);
	}

	/**
	 * 
	 * @param formInputs
	 */
	public void fillFormInputs(List<String> formInputs) {
		for (int i = 0; i < formInputsElements.size(); i++) {
			expw.waitForVisibilityOfElement(formInputsElements.get(i), ConstantsFront.EXPLICIT_WAIT_ADD_CONTACTS_PAGE);
			selact.sendTextToElement(formInputsElements.get(i), formInputs.get(i));
			log.info("Fill the input: " + formInputsElements.get(i) + " with the value: " + formInputs.get(i));
		}

	}

	/**
	 * 
	 */
	public void clickOnSubmit() {
		expw.waitForElementToBeClickable(submitButton, ConstantsFront.EXPLICIT_WAIT_ADD_CONTACTS_PAGE);
		selact.clickOnElement(submitButton);
		log.info("Click on Submit Button");
	}

}
