package back.common;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matcher;

import io.restassured.builder.ResponseSpecBuilder;

public class ResponseSpecBase {
	public ResponseSpecBuilder RESPONSE_BUILDER;
	private static final Logger log = LogManager.getLogger(ResponseSpecBase.class.getName());
	
	public  ResponseSpecBase() {
		RESPONSE_BUILDER = new ResponseSpecBuilder();
	}
	
	/**
	 * 
	 * @param statusCode
	 * @return
	 */
	public ResponseSpecBuilder verifyStatusCode(int statusCode) {
		log.info("The expected Status code: "+ statusCode);
		RESPONSE_BUILDER.expectStatusCode(statusCode);
		return RESPONSE_BUILDER;
	}
	
	
	/**
	 * 
	 * @param matcher
	 * @return
	 */
	public ResponseSpecBuilder verifyBodyMatcher(Matcher<?> matcher) {
		log.info("The expected JSON SChema: "+ matcher);
		RESPONSE_BUILDER.expectBody(matcher);
		return RESPONSE_BUILDER;
	}
	
	
	
	/**
	 * 
	 * @param timeout
	 * @return
	 */
	public ResponseSpecBuilder verifyResponseTime(long timeout) {
		log.info("The expected wait time is : " + timeout + " seconds.");
		RESPONSE_BUILDER.expectResponseTime(lessThan(timeout), TimeUnit.SECONDS);
		return RESPONSE_BUILDER;
	}
	
	/**
	 * 
	 * @param jsonQuery
	 * @param expectedString
	 * @return
	 */
	public ResponseSpecBuilder verifyStringEquals(String jsonQuery, String expectedString) {
		log.info("The JsonQuery is: "+ jsonQuery);
		log.info("The expected string is: "+ expectedString);
		RESPONSE_BUILDER.expectBody(jsonQuery, equalTo(expectedString));
 		return RESPONSE_BUILDER;
	}

	
	/**
	 * 
	 * @param jsonQuery
	 * @param expectedInt
	 * @return
	 */
	public ResponseSpecBuilder verifyIntegerEquals(String jsonQuery, int expectedInt) {
		log.info("The JsonQuery is: "+ jsonQuery);
		log.info("The expected integer is: "+ expectedInt);
		RESPONSE_BUILDER.expectBody(jsonQuery, equalTo(expectedInt));
        return RESPONSE_BUILDER;
	}
}
