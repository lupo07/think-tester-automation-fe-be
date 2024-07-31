package back.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * This class reads the values from the file configuration.properties
 * 
 * @return Value from configuration.properties
 */
public class ConfigReader {
	private static final Logger log = LogManager.getLogger(ConfigReader.class.getName());
	Properties pro;

	public ConfigReader() {

		try {
			File src = new File("./configuration.properties");
			FileInputStream fis = new FileInputStream(src);

			pro = new Properties();

			pro.load(fis);

		} catch (Exception e) {
			log.debug("Exception is ==" + e.getMessage());
		}
	}

	public String getBaseURL() {
		return pro.getProperty("BaseURL");
	}

	public String getFilterURL() {
		return pro.getProperty("FilterURL");
	}


	//////// General Use
	/**
	 * <ol>
	 * <li>Index 0: Content-Type</li>
	 * <li>Index 1: User-Agent</li>
	 * <li>Index 2: Accept-Encoding</li>
	 * <li>Index 3: AUTH_USER</li>
	 * <li>Index 4: AUTH_OWNER</li>
	 * <li>Index 5: Cookie</li>
	 * <li>Index 6: AUTH_SCOPE</li>
	 * <li>Index 7: App-version</li>
	 * <li>Index 8: Authorization</li>
	 * <li>Index 9: Accept-Language</li>
	 * <li>Index 10: x-application-id</li>
	 * <li>Index 11: API_KEY</li>
	 * </ol>
	 * 
	 * @return
	 */
	public List<String> getKeyHeader() {
		String property = pro.getProperty("KeyHeader");
		List<String> propertyList = new ArrayList<>(Arrays.asList(property.split(",")));
		return propertyList;
	}

	/**
	 * <ol>
	 * <li>Index 0: application/json</li>
	 * <li>Index 1: PostmanRuntime/7.26.8</li>
	 * <li>Index 2: gzip,deflate,br</li>
	 * <li>Index 3: JSESSIONID=C2FD48B74CFD7D11F0B3DAC2C96654E0</li>
	 * <li>Index 4: storekeepers</li>
	 * </ol>
	 * 
	 * @return
	 */
	public List<String> getHeaderValue() {
		String property = pro.getProperty("HeaderValue");
		List<String> propertyList = new ArrayList<>(Arrays.asList(property.split(";")));
		return propertyList;
	}

	/**
	 * <ol>
	 * <li>Index 0: id</li>
	 * <li>Index 1: rt</li>
	 * <li>Index 2: level</li>
	 * <li>Index 3: bundleID</li>
	 * <li>Index 4: balanceId</li>
	 * <li>Index 5: flow_id</li>
	 * <li>Index 6: model_id</li>
	 * <li>Index 7: date</li>
	 * <li>Index 8: orderId</li>
	 * <li>Index 9: order_id</li>
	 * <li>Index 10:configId</li>
	 * <li>Index 11:cacheKey</li>
	 * <li>Index 12:latitude</li>
	 * <li>Index 13:longitude</li>
	 * <li>Index 14:config_type</li>
	 * </ol>
	 * 
	 * @return
	 */
	public List<String> getPathParameters() {
		String property = pro.getProperty("PathParameters");
		List<String> propertyList = new ArrayList<>(Arrays.asList(property.split(",")));
		return propertyList;
	}

	/**
	 * <ol>
	 * <li>Index 0: storekeeper_id</li>
	 * <li>Index 1: id</li>
	 * <li>Index 2: order_id</li>
	 * <li>Index 3: key</li>
	 * <li>Index 4: ids</li>
	 * <li>Index 5: gateway</li>
	 * <li>Index 6: account</li>
	 * <li>Index 7: storekeeperId</li>
	 * <li>Index 8: value</li>
	 * <li>Index 9: orderId</li>
	 * <li>Index 10: date</li>
	 * </ol>
	 * 
	 * @return
	 */
	public List<String> getQueryParameters() {
		String property = pro.getProperty("QueryParameters");
		List<String> propertyList = new ArrayList<>(Arrays.asList(property.split(",")));
		return propertyList;
	}

	/**
	 * <ol>
	 * <li>Index 0: id</li>
	 * <li>Index 0: id[0]</li>
	 * </ol>
	 * 
	 * @return
	 */
	public List<String> getJsonQueries() {
		String property = pro.getProperty("JsonQueries");
		List<String> propertyList = new ArrayList<>(Arrays.asList(property.split(",")));
		return propertyList;
	}



}