package back.request;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;

import back.common.RequestSpecBase;
import io.restassured.specification.RequestSpecification;

public class LoginRequest extends RequestSpecBase {
	RequestSpecification reqSpec;
	private static final Logger log = LogManager.getLogger(LoginRequest.class.getName());

	/**
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws IOException
	 */
	public RequestSpecification setUserLogin(String baseURI, String endPoint, String bodyJson, String keyHeader, String header)
			throws JsonParseException, IOException {
		log.info("The set the Request for Login User");
		reqSpec = getReqSpecJsonNoAuth(baseURI, endPoint, bodyJson, keyHeader, header);
		return reqSpec;
	}

}
