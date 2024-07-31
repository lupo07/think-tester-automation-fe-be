package back.suites;

import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.JsonParseException;

import back.common.BaseTest;
import back.common.Constants;
import back.common.Endpoints;
import io.restassured.path.json.JsonPath;

public class ContactsTest extends BaseTest {
	private static final Logger log = LogManager.getLogger(ContactsTest.class.getName());

	@Test(dataProvider = "addContactsTestData")
	public void addContact(String loginBody, String jsonPathToken, String addContactBody, String addContactJsonSchema)
			throws JsonParseException, IOException {
		log.info("The Test Suite " + ContactsTest.class.getName() + " has started");
		test = report.createTest("Add Contact information");

		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 1 Set the Request Specification for Login ------------------");
		test.log(Status.INFO, "Step 1 Set the Request Specification for Login");
		reqSpec = logReq.setUserLogin(Endpoints.BASE_THINKING_URL, Endpoints.LOGIN_USER, loginBody,
				config.getKeyHeader().get(0), config.getHeaderValue().get(0));

		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 2 Set the Response Specification for Login ------------------");
		test.log(Status.INFO, "Step 2 Set the Response Specification for Login ");
		respSpec = logResp.setUserLogin(Constants.SUCCESSFUL_STATUS_CODE);

		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 3 Send the Endpoint Get Users for Login ------------------");
		test.log(Status.INFO, "Step 3 Send the Endpoint Get Users for Login");
		response = apiBuild.getResponse(reqSpec, respSpec, Constants.REST_METHOD_POST);
		JsonPath jsPath = um.getJsonPath(response);
		sg.setToken(jsPath.get(jsonPathToken));
		log.info("User Token: " + sg.getToken());

		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 4 Set the Request Specification for Add Contact ------------------");
		test.log(Status.INFO, "Step 4 Set the Request Specification for Add Contact ");

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put(config.getKeyHeader().get(0), config.getHeaderValue().get(0));
		headers.put(config.getKeyHeader().get(8), "Bearer " + sg.getToken());

		reqSpec = contactsReq.setAddContact(Endpoints.BASE_THINKING_URL, Endpoints.CONTACTS, addContactBody, headers);

		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 5 Set the Response Specification for Add Contact  ------------------");
		test.log(Status.INFO, "Step 5 Set the Response Specification for Add Contact ");
		respSpec = contactsResp.setAddContacts(Constants.SUCCESSFUL_STATUS_CREATED, addContactJsonSchema);

		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 6 Send the Endpoint for Add Contact  ------------------");
		test.log(Status.INFO, "Step 6 Send the Endpoint for Add Contact ");
		response = apiBuild.getResponse(reqSpec, respSpec, Constants.REST_METHOD_POST);

	}

	@Test(dataProvider = "updateContactsTestData")
	public void updateContact(String loginBody, String jsonPathToken, String updateContactBody) throws JsonParseException, IOException {
		log.info("The Test Suite " + ContactsTest.class.getName() + " has started");
		test = report.createTest("Update Contact information");

		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 1 Set the Request Specification for Login ------------------");
		test.log(Status.INFO, "Step 1 Set the Request Specification for Login");
		reqSpec = logReq.setUserLogin(Endpoints.BASE_THINKING_URL, Endpoints.LOGIN_USER, loginBody,
				config.getKeyHeader().get(0), config.getHeaderValue().get(0));

		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 2 Set the Response Specification for Login ------------------");
		test.log(Status.INFO, "Step 2 Set the Response Specification for Login ");
		respSpec = logResp.setUserLogin(Constants.SUCCESSFUL_STATUS_CODE);

		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 3 Send the Endpoint Get Users for Login ------------------");
		test.log(Status.INFO, "Step 3 Send the Endpoint Get Users for Login");
		response = apiBuild.getResponse(reqSpec, respSpec, Constants.REST_METHOD_POST);
		JsonPath jsPath = um.getJsonPath(response);
		sg.setToken(jsPath.get(jsonPathToken));
		log.info("User Token: " + sg.getToken());
		
		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 4 Set the Request Specification for Update Contact ------------------");
		test.log(Status.INFO, "Step 4 Set the Request Specification for Update Contact ");

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put(config.getKeyHeader().get(0), config.getHeaderValue().get(0));
		headers.put(config.getKeyHeader().get(8), "Bearer " + sg.getToken());

		reqSpec = contactsReq.setUpdateContact(Endpoints.BASE_THINKING_URL, Endpoints.CONTACTS, updateContactBody, headers);
		
		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 5 Set the Response Specification for Update Contact  ------------------");
		test.log(Status.INFO, "Step 5 Set the Response Specification for Update Contact ");
		respSpec = contactsResp.setUpdateContact(Constants.ERROR_STATUS_UNAVAILABLE);
		
		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 6 Send the Endpoint for Update Contact  ------------------");
		test.log(Status.INFO, "Step 6 Send the Endpoint for Update Contact ");
		response = apiBuild.getResponse(reqSpec, respSpec, Constants.REST_METHOD_PATCH);
		
	}
	
	@Test (dataProvider = "deleteContactsTestData")
	public void deleteContact(String loginBody, String jsonPathToken) throws JsonParseException, IOException {
		log.info("The Test Suite " + ContactsTest.class.getName() + " has started");
		test = report.createTest("Delete Contact information");

		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 1 Set the Request Specification for Login ------------------");
		test.log(Status.INFO, "Step 1 Set the Request Specification for Login");
		reqSpec = logReq.setUserLogin(Endpoints.BASE_THINKING_URL, Endpoints.LOGIN_USER, loginBody,
				config.getKeyHeader().get(0), config.getHeaderValue().get(0));

		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 2 Set the Response Specification for Login ------------------");
		test.log(Status.INFO, "Step 2 Set the Response Specification for Login ");
		respSpec = logResp.setUserLogin(Constants.SUCCESSFUL_STATUS_CODE);

		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 3 Send the Endpoint Get Users for Login ------------------");
		test.log(Status.INFO, "Step 3 Send the Endpoint Get Users for Login");
		response = apiBuild.getResponse(reqSpec, respSpec, Constants.REST_METHOD_POST);
		JsonPath jsPath = um.getJsonPath(response);
		sg.setToken(jsPath.get(jsonPathToken));
		log.info("User Token: " + sg.getToken());
		
		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 4 Set the Request Specification for Delete Contact ------------------");
		test.log(Status.INFO, "Step 4 Set the Request Specification for Delete Contact ");

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put(config.getKeyHeader().get(0), config.getHeaderValue().get(0));
		headers.put(config.getKeyHeader().get(8), "Bearer " + sg.getToken());
		
		reqSpec = contactsReq.setDeleteContact(Endpoints.BASE_THINKING_URL, Endpoints.CONTACTS, headers);
		
		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 5 Set the Response Specification for Delete Contact  ------------------");
		test.log(Status.INFO, "Step 5 Set the Response Specification for Delete Contact ");
		respSpec = contactsResp.setDeleteContact(Constants.ERROR_STATUS_UNAVAILABLE);
		
		log.info("\n ----------------------------------------------------------------------");
		log.info(" -------------- Step 6 Send the Endpoint for Delete Contact  ------------------");
		test.log(Status.INFO, "Step 6 Send the Endpoint for Delete Contact ");
		response = apiBuild.getResponse(reqSpec, respSpec, Constants.REST_METHOD_DELETE);
		
	}
}
