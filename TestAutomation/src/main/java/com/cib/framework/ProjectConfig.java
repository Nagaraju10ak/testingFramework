package com.cib.framework;

import java.io.FileInputStream;
import java.util.Properties;

public class ProjectConfig {
	public static Properties prop = new Properties();
	public static String filepath;

	static {
		try {
			filepath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\cib\\framework\\projectConfig.properties";
			prop.load(new FileInputStream(filepath));				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getPropertyValue(String key) {
		return prop.getProperty(key);
	}
}
