package back.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UsefulMethods {
	private static final Logger log = LogManager.getLogger(UsefulMethods.class.getName());

	/**
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public String readCurrentLineFile(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String currentLine = reader.readLine();
		log.info("The current line is:" + currentLine);
		reader.close();
		return currentLine;
	}

	/**
	 * 
	 * @param text
	 * @param filePath
	 * @throws IOException
	 */
	public void writeLineFile(String text, String filePath) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
		writer.write(text);
		log.info("The line to write is:" + text);
		writer.close();
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

	/**
	 * 
	 * This method changes a string List to an integer List
	 * 
	 * @param numbers
	 * @return
	 */
	public List<Integer> stringToInt(List<String> numbers) {
		List<Integer> numbersInt = new ArrayList<>();
		for (int i = 0; i <= numbers.size() - 1; i++) {
			int id = stringToInt(numbers.get(i));
			numbersInt.add(id);
		}
		return numbersInt;
	}

	/**
	 * 
	 * This method Parses the string argument as a signed decimal integer
	 * 
	 * @param number
	 * @return num
	 */
	public String intToString(int number) {
		String numSt = Integer.toString(number);
		log.info("The Integer has been changed into a String : " + numSt);
		return numSt;
	}

	/**
	 * 
	 * This method Parses the string argument as boolean
	 * 
	 * @param value
	 * @return bool
	 */
	public boolean stringToBoolean(String value) {
		boolean bool = Boolean.parseBoolean(value);
		log.info("The string has been changed into a bool : " + bool);
		return bool;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public float stringToFloat(String value) {
		float f = Float.parseFloat(value);
		log.info("The string has been changed into a float : " + f);
		return f;
	}

	/**
	 * 
	 * @param res
	 * @return String
	 */
	public JsonPath getJsonPath(Response res) {
		String path = res.asString();
		return new JsonPath(path);
	}

	/**
	 * 
	 * This method Parses the string argument as List
	 * 
	 * @param value
	 * @return List
	 */
	public List<String> stringToList(String value, String splitBy) {
		List<String> splitList = new ArrayList<>(Arrays.asList(value.split(splitBy)));
		log.info("The string has been changed into a List: " + splitList);
		return splitList;
	}

	/**
	 * 
	 * @param value
	 * @param regex
	 * @param replace
	 * @return
	 */
	public String replaceCharacters(String value, String regex, String replace) {
		String valueMod = value.replaceAll(regex, replace);
		return valueMod;
	}

	/**
	 * 
	 * @return
	 */
	public String randomAlphanumericString() {
		String generatedString = RandomStringUtils.randomAlphanumeric(10);
		return generatedString;
	}

	/**
	 * 
	 * @param list
	 * @param expectedString
	 */
	public void verifyListContainsText(List<String> list, String expectedString) {
		for (int i = 0; i <= list.size() - 1; i++) {
			String str = list.get(i);

			if (str.contains(expectedString)) {
				Assert.assertTrue(str.contains(expectedString));
				log.info("The Actual Element is: " + str + " and contains: " + expectedString);
				break;

			} else if (!str.contains(expectedString) && (i == list.size() - 1)) {
				log.info("The Actual Element is: " + str + " and do not contains: " + expectedString);
				Assert.assertTrue(str.contains(expectedString));
				
			} else {
				log.info("The Actual Element is: " + str);
			}
		}
	}
}
