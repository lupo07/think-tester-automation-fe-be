package front.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;


public class UsefulMethodsFront {
	WebDriver driver;
	private static final Logger log = LogManager.getLogger(UsefulMethodsFront.class.getName());

	public UsefulMethodsFront(WebDriver driver) {
		this.driver = driver;
	}
	
	
	/**
	 * 
	 * This method Parses the string argument as a signed decimal integer
	 * 
	 * @param number
	 * @return num
	 */
	public int stringToInt(String number) {
		int num = Integer.parseInt(number);
		log.info("The string has been changed into a int : " + num);
		return num;
	}
	
	public String getScreenshotBase64(WebDriver driver, String filename) {
		String encodedBase64 = null;
		TakesScreenshot tsc = (TakesScreenshot) driver;
		File src = tsc.getScreenshotAs(OutputType.FILE);
		
		String directory;
		
		//IF we are running as a Remote Execution:
		if( driver.toString().contains("RemoteWebDriver")) {
			((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
			log.debug("RemoteWebDriver detected..");
			directory = "./ScreenShot/";
		
		} else {
			log.debug("Local WebDriver detected..");
			directory = "\\ScreenShot\\";
		}
		String path = directory + filename + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		
		try {		
			FileUtils.copyFile(src, destination);
			FileInputStream fileInputStream = new FileInputStream(destination);
			byte[] bytes = new byte[(int) destination.length()];
			fileInputStream.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64(bytes));
			
			fileInputStream.close();
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return encodedBase64;
	}

}
