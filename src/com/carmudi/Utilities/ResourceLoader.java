package com.carmudi.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ResourceLoader {
	private Properties prop = new Properties();

	private static final String RESOURCE_FILE = "./application.properties";
	
	private ResourceLoader() {
		
	}

	private static ResourceLoader instance;

	public static ResourceLoader getInstance() {
		if(instance == null) {
			instance = new ResourceLoader();
		}
		return instance;
	}

	
	
	public String getProperty(String key) throws IOException {
		// String propFileName = "chrome.driver";
		if(prop.containsKey(key)) {
			return prop.getProperty(key);
		}
		
		URL url = getClass().getClassLoader().getResource(RESOURCE_FILE);
		System.out.println(url.getPath());
		/*InputStream inputStream = new FileInputStream(new File(url));
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + RESOURCE_FILE + "' not found in the classpath");
		}*/
		System.out.println(prop.getProperty(key));
		return prop.getProperty(key);
	}
}
