package frameworktest.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public static List<Map<String, String>> getJsonDataToMap(String fileName) throws IOException
	{

	    InputStream is = DataReader.class
	            .getClassLoader()
	            .getResourceAsStream("data/" + fileName);

	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.readValue(is,
	            new TypeReference<List<Map<String, String>>>() {});
	}
}

