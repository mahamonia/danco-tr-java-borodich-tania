package com.danco.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.danco.api.IPropertyProgramm;

public class PropertyProgramm implements IPropertyProgramm {
	private static PropertyProgramm config;
	private Properties properties;
	private FileInputStream fileInputStream;

	private PropertyProgramm(String nameFile) {

		try {
			properties = new Properties();
			if (nameFile == null || nameFile.equals("")) {
				nameFile = "property.properties";
			}
			File file = new File(nameFile);
			fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream);

		} catch (Exception e) {
			e.getMessage();

		} finally {
			if (fileInputStream != null) {
			}
			try {
				fileInputStream.close();
			} catch (Exception e) {
			}
		}

	}

	public static PropertyProgramm getInstance(String nameFile) {
		if (config == null) {
			config = new PropertyProgramm(nameFile);
		}
		return config;
	}

	@Override
	public String getConfig(String key) {

		return properties.getProperty(key);
	}

}
