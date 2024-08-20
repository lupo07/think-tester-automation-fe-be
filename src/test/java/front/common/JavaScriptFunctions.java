package front.common;


import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptFunctions {
	WebDriver driver;
	private static final Logger log = LogManager.getLogger(JavaScriptFunctions.class.getName());

	public JavaScriptFunctions(WebDriver driver) {
		this.driver = driver;
	}

	/***
	 * Highlight web element
	 */
	public  void shadeElem(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: #FDFF47; border: 2px solid #000000;');",
				element);
		log.info("Shade element " + element);
		
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);
	}

	/***
	 * Scroll / move to a web element
	 */
	public  void scrollToElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView()", element);
		log.info("Scrolling the element " + element + " into view");
	}

	/***
	 * Scroll / move to a web element with no logs
	 */
	public void scrollToElementNoLogs(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView()", element);
	}

	/***
	 * Click a web element
	 */
	public void clickOnElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		log.info("Click on the element " + element);
	}

	/***
	 * SendKeys to a web element
	 */
	public void sendKeysOnElement(WebElement element, String text) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value='" + text + "';", element);
		log.info("Send Text " + text + " to " + element);
	}

	/***
	 * Scroll up by 250
	 */
	public void scrollUp() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-250)", "");
		log.info("Scrolling up");
	}

	/***
	 * Scroll down by 100
	 */
	public void scrollDown() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,100)", "");
		log.info("Scrolling up");
	}
	
	/***
	 * Scroll till end of the page
	 */
	public void scrollToBottom() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		log.info("Scrolling till end of the page");
	}

	/**
	 * Blurs out from a webElem (Mostly used for Input) 	
	 * 
	 * @param webEl
	 */
	public void blur(WebElement webEl) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].focus(); arguments[0].blur(); return true", webEl);

		log.info("Blurring out from element " + webEl);
	}
	
	/***
	 * Double click a web element
	 */
	public void doubleClickOnElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("var evt = document.createEvent('MouseEvents');"+ 
			    "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"+ 
			    "arguments[0].dispatchEvent(evt);", element);
		log.info("Click on the element " + element);
	}
	
}
