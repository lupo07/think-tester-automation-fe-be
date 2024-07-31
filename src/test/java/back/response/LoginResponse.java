package back.response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import back.common.Constants;
import back.common.ResponseSpecBase;
import io.restassured.specification.ResponseSpecification;

public class LoginResponse extends ResponseSpecBase {
	ResponseSpecification respSpec;
	private static final Logger log = LogManager.getLogger(LoginResponse.class.getName());
	
	/**
	 * 
	 * @param statusCode
	 * @return
	 */
	public ResponseSpecification setUserLogin(int statusCode) {
		log.info("The set the Response");
		verifyStatusCode(statusCode);
		verifyResponseTime(Constants.SHORT_WAIT);
		respSpec = RESPONSE_BUILDER.build();
		return respSpec;
	}

}
