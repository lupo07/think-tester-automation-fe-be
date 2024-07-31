package back.response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import back.common.Constants;
import back.common.ResponseSpecBase;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.ResponseSpecification;

public class ContactsResponse extends ResponseSpecBase {
	ResponseSpecification respSpec;
	private static final Logger log = LogManager.getLogger(ContactsResponse.class.getName());
	
	/**
	 * 
	 * @param statusCode
	 * @return
	 */
	public ResponseSpecification setAddContacts(int statusCode, String path) {
		log.info("The set the Add Contacts Response");
		verifyStatusCode(statusCode);
		verifyResponseTime(Constants.SHORT_WAIT);
		verifyBodyMatcher(JsonSchemaValidator.matchesJsonSchemaInClasspath(path));
		respSpec = RESPONSE_BUILDER.build();
		return respSpec;
	}
	
	/**
	 * 
	 * @param statusCode
	 * @return
	 */
	public ResponseSpecification setUpdateContact(int statusCode) {
		log.info("The set the Update Contact Response");
		verifyStatusCode(statusCode);
		verifyResponseTime(Constants.L_WAIT);
//		verifyBodyMatcher(JsonSchemaValidator.matchesJsonSchemaInClasspath(path));
		respSpec = RESPONSE_BUILDER.build();
		return respSpec;
	}
	
	/**
	 * 
	 * @param statusCode
	 * @return
	 */
	public ResponseSpecification setDeleteContact(int statusCode) {
		log.info("The set the Delete Contact Response");
		verifyStatusCode(statusCode);
		verifyResponseTime(Constants.L_WAIT);
//		verifyBodyMatcher(JsonSchemaValidator.matchesJsonSchemaInClasspath(path));
		respSpec = RESPONSE_BUILDER.build();
		return respSpec;
	}

}
