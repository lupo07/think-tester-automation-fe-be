package back.request;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;

import back.common.RequestSpecBase;
import io.restassured.specification.RequestSpecification;

public class ContactsRequest extends RequestSpecBase {
	RequestSpecification reqSpec;
	private static final Logger log = LogManager.getLogger(ContactsRequest.class.getName());
	
	/**
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws IOException
	 */
	public RequestSpecification setAddContact(String baseURI, String endPoint, String bodyJson, Map<String, String> headers)
			throws JsonParseException, IOException {
		log.info("The set the Request for Adding a Contact");
		reqSpec = getReqSpecJsonNoAuth(baseURI, endPoint, bodyJson, headers);
		return reqSpec;
	}
	
	/**
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws IOException
	 */
	public RequestSpecification setUpdateContact(String baseURI, String endPoint, String bodyJson, Map<String, String> headers)
			throws JsonParseException, IOException {
		log.info("The set the Request for Update a Contact");
		reqSpec = getReqSpecJsonNoAuth(baseURI, endPoint, bodyJson, headers);
		return reqSpec;
	}
	
	/**
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws IOException
	 */
	public RequestSpecification setDeleteContact(String baseURI, String endPoint, Map<String, String> headers)
			throws JsonParseException, IOException {
		log.info("The set the Request for Delete a Contact");
		reqSpec = getReqSpecNoJson(baseURI, endPoint, headers);
		return reqSpec;
	}

}
