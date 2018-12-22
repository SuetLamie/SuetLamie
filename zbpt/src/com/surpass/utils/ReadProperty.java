package com.surpass.utils;

import java.util.Properties;

public class ReadProperty {
	public static Properties loadFile(String path) {
		Properties prop = null;
		try {
			prop = new Properties();
			prop.load(ReadProperty.class.getResourceAsStream(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static void main(String[] args) {
		Properties properties = ReadProperty.loadFile("/config/map.properties");
		System.err.println(properties.getProperty("baidu.map.ak"));
	}
}
