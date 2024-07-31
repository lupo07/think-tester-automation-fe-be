package back.buider;

import static io.restassured.RestAssured.given;

import java.io.PrintStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class APIsBuilder {
	public RequestSpecBuilder REQUEST_BUILDER;
	public RequestSpecification REQUEST_SPEC;
	PrintStream logStream;

	private static final Logger log = LogManager.getLogger(APIsBuilder.class.getName());

	/**
	 * This method is the main method that will create the Request and returns the
	 * response for a Rest API request
	 * 
	 * @param reqSpec
	 * @param respSpec
	 * @param type
	 * @return
	 */
	public Response getResponse(RequestSpecification reqSpec, ResponseSpecification respSpec, String type) {
		REQUEST_SPEC = reqSpec;
		Response response = null;

		switch (type) {
		case "get" -> response = given().spec(REQUEST_SPEC).when().get();
		case "post" -> response = given().spec(REQUEST_SPEC).when().post();
		case "put" -> response = given().spec(REQUEST_SPEC).when().put();
		case "patch" -> response = given().spec(REQUEST_SPEC).when().patch();
		case "delete" -> response = given().spec(REQUEST_SPEC).when().delete();
		default -> log.info("Type is not supported");

		}
		response.then().spec(respSpec);
		return response;
	}

}
