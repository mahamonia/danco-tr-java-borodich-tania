package com.danco.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProcessorProperty{

	private Properties properties;
	private FileInputStream fileInputStream;

	private static final Logger LOGGER = LogManager
			.getLogger(ProcessorProperty.class);

	public ProcessorProperty(String nameFile) {
		try {
			this.properties = new Properties();
			if (nameFile == null || nameFile.isEmpty()) {
				nameFile = "property.properties";
			}
			File file = new File(nameFile);
			fileInputStream = new FileInputStream(file);
			this.properties.load(fileInputStream);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());

		} finally {
			if (fileInputStream != null) {
			}
			try {
				fileInputStream.close();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

	public String getConfig(String key) {
		return this.properties.getProperty(key);
	}

}
