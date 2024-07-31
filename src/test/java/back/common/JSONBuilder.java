package back.common;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONBuilder {

	/**
	 * 
	 * Formats a String into JSON Node
	 * 
	 * @param stringJSON
	 * @return
	 * @throws JsonParseException
	 * @throws IOException
	 */
	public  JsonNode stringTJSON(String stringJSON) 
			  throws JsonParseException, IOException { 
			    ObjectMapper mapper = new ObjectMapper();
			    JsonNode actualObj = mapper.readTree(stringJSON);
			 return actualObj ;
			}
	
}
