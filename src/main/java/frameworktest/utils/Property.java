package frameworktest.utils;

import java.util.Properties;
/*
public class Property {
	  private static Properties props = new Properties();

	    static {
	        try {
	            props.load(Property.class.getClassLoader()
	                    .getResourceAsStream("GlobalData.properties"));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public static String get(String key) {
	        return props.getProperty(key);
	    }
}
*/

public class Property {
    private static Properties props = new Properties();

    static {
        try {
            props.load(Property.class.getClassLoader()
                    .getResourceAsStream("GlobalData.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get value with default and system property override
    public static String get(String key, String defaultValue) {
        // Check system property first
        String value = System.getProperty(key);
        if(value != null) {
            return value;
        }
        // Fallback to properties file
        return props.getProperty(key, defaultValue);
    }

    // Optional: simple get() without default
    public static String get(String key) {
        return get(key, null);
    }
}
