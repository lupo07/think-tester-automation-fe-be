package back.common;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBase {
	JSONBuilder jsonb;
	public RequestSpecBuilder REQUEST_BUILDER;
	public RequestSpecification REQUEST_SPEC;
	PrintStream logStream;
	public ResponseSpecBuilder RESPONSE_BUILDER;
	private static final Logger log = LogManager.getLogger(RequestSpecBase.class.getName());

	public RequestSpecBase() {
		// RestAssured.filters(new RequestLoggingFilter());
		logStream = IoBuilder.forLogger(log).buildPrintStream();
		RestAssuredConfig restAssuredConfig = new RestAssuredConfig();
		LogConfig logConfig = restAssuredConfig.getLogConfig();
		logConfig.defaultStream(logStream).enablePrettyPrinting(true);
		// ResponseLoggingFilter.logResponseTo(logStream);
		REQUEST_BUILDER = new RequestSpecBuilder().addFilter(ResponseLoggingFilter.logResponseTo(logStream))
				.addFilter(RequestLoggingFilter.logRequestTo(logStream));

		// RESPONSE_BUILDER = new ResponseSpecBuilder();
		jsonb = new JSONBuilder();
	}

	/**
	 * 
	 * Build the Request Spec with no JSON body and Path parameter
	 * 
	 * @param baseURI
	 * @return
	 */
	public RequestSpecification getReqSpecNoJsonPathParam(String baseURI, String basePath, String param, String value) {
		log.info("Build the Request Spec with no JSON body and Path parameter");
		REQUEST_BUILDER.setBaseUri(baseURI);
		REQUEST_SPEC = REQUEST_BUILDER.build();
		REQUEST_SPEC.basePath(basePath);
		REQUEST_SPEC.pathParam(param, value);

		return REQUEST_SPEC;
	}

	/**
	 * 
	 * Build the Request Spec with JSON body but no Auth, no Header
	 * 
	 * @param baseURI
	 * @param json
	 * @return
	 * @throws IOException
	 * @throws JsonParseException
	 */
	public RequestSpecification getReqSpecJsonNoAuthNoHeaders(String baseURI, String basePath, String bodyJsonGroup)
			throws JsonParseException, IOException {

		JsonNode json = jsonb.stringTJSON(bodyJsonGroup);
		log.info("JSON: " + json);

		REQUEST_BUILDER.setBaseUri(baseURI);
		REQUEST_BUILDER.setBody(json);
		REQUEST_SPEC = REQUEST_BUILDER.build();
		REQUEST_SPEC.basePath(basePath);
		return REQUEST_SPEC;
	}

	/**
	 * 
	 * Build the Request Spec with JSON body but no Auth
	 * 
	 * @param baseURI
	 * @param json
	 * @param keyHeader
	 * @param headerValue
	 * @return
	 * @throws IOException
	 * @throws JsonParseException
	 */
	public RequestSpecification getReqSpecJsonNoAuth(String baseURI, String basePath, String bodyJsonGroup,
			String keyHeader, String headerValue) throws JsonParseException, IOException {

		JsonNode json = jsonb.stringTJSON(bodyJsonGroup);
		log.info("JSON: " + json);

		REQUEST_BUILDER.setBaseUri(baseURI);
		REQUEST_BUILDER.addHeader(keyHeader, headerValue);
		REQUEST_BUILDER.setBody(json);
		REQUEST_SPEC = REQUEST_BUILDER.build();
		REQUEST_SPEC.basePath(basePath);
		return REQUEST_SPEC;
	}

	/***
	 * 
	 * Build the Request Spec with JSON body but no Auth and a Map for the Headers
	 * 
	 * @param baseURI
	 * @param basePath
	 * @param bodyJsonGroup
	 * @param headers
	 * @return
	 * @throws JsonParseException
	 * @throws IOException
	 */
	public RequestSpecification getReqSpecJsonNoAuth(String baseURI, String basePath, String bodyJsonGroup,
			Map<String, String> headers) throws JsonParseException, IOException {

		JsonNode json = jsonb.stringTJSON(bodyJsonGroup);
		log.info("JSON: " + json);
		log.info("HEADERS " + headers);
		REQUEST_BUILDER.setBaseUri(baseURI);
		REQUEST_BUILDER.addHeaders(headers);
		REQUEST_BUILDER.setBody(json);
		REQUEST_SPEC = REQUEST_BUILDER.build();
		REQUEST_SPEC.basePath(basePath);
		return REQUEST_SPEC;
	}

	/**
	 * 
	 * @param baseURI
	 * @param basePath
	 * @param headers
	 * @return
	 */
	public RequestSpecification getReqSpecNoJson(String baseURI, String basePath, Map<String, String> headers) {
		log.info("The set the Request ");
		REQUEST_BUILDER.setBaseUri(baseURI);
		REQUEST_BUILDER.addHeaders(headers);
		REQUEST_SPEC = REQUEST_BUILDER.build();
		REQUEST_SPEC.basePath(basePath);
		return REQUEST_SPEC;
	}

}
