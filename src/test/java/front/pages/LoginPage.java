package front.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import front.common.ConfigReader;
import front.common.ConstantsFront;
import front.common.ExplicitWaits;
import front.common.SeleniumActions;
import front.common.SupportValidations;

public class LoginPage {
	WebDriver driver;
	SeleniumActions selact;
	ExplicitWaits expw;
	SupportValidations spv;
	ConfigReader config = new ConfigReader();;
	private static final Logger log = LogManager.getLogger(LoginPage.class.getName());

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		expw = new ExplicitWaits(driver);
		selact = new SeleniumActions(driver);
		spv= new SupportValidations(driver);
	}

	

	@FindBy(xpath = "//h1")
	WebElement loginHeader;

	@FindBy(xpath = "//input[@id='email']")
	WebElement emailInput;
	
	@FindBy(xpath = "//input[@id='password']")
	WebElement passwordInput;
	
	@FindBy(xpath = "//button[@id='submit']")
	WebElement submitButton;


	/**
	 * 
	 * @param product
	 */
	public void verifyLoginPage(String product) {
		spv.verifyPageHeader(loginHeader, product, ConstantsFront.EXPLICIT_WAIT_LOGIN_PAGE);
	}
	
	/**
	 * 
	 * @param email
	 * @param password
	 */
	public void fillLoginFields(String email, String password) {
		expw.waitForVisibilityOfElement(emailInput, ConstantsFront.EXPLICIT_WAIT_LOGIN_PAGE);
		selact.sendTextToElement(emailInput, email);
		log.info("Fill the input with the email: " + email);
		
		expw.waitForVisibilityOfElement(passwordInput, ConstantsFront.EXPLICIT_WAIT_LOGIN_PAGE);
		selact.sendTextToElement(passwordInput, password);
		log.info("Fill the input with the email: " + password);
	}
	
	/**
	 * 
	 */
	public void clickOnSubmit() {
		expw.waitForElementToBeClickable(submitButton, ConstantsFront.EXPLICIT_WAIT_LOGIN_PAGE);
		selact.clickOnElement(submitButton);
		log.info("Click on Submit Button");
	}

}
