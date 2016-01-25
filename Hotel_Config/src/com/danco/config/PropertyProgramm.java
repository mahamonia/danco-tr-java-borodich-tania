package com.danco.config;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PropertyProgramm {
	private static PropertyProgramm config;
	private Properties properties;
	private FileInputStream fileInputStream;
	
	private static Logger logger = LogManager
			.getLogger(PropertyProgramm.class);
	
	
	private PropertyProgramm() {
		properties = new Properties();
		File file = new File("property.txt");

		try {
			fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally{
			if (fileInputStream != null) {
			}
			try {
				fileInputStream.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

	}
	
	public static PropertyProgramm getInstance  (){
		if (config == null) {
			config = new PropertyProgramm();
		}
		return config;	
	}
	

	public Boolean getConfigStatusRoom(String key) {
		Boolean status = true;
		String str = properties.getProperty(key);
		status = Boolean.valueOf(str);
		
		return status;
	}

	public int getConfigHistoryRoom(String key) {
		int amount =0;
		String str = properties.getProperty(key);
		amount = Integer.parseInt(str);

		return amount;

	}
	
	public String getConfigFile(String key) {
		String file = properties.getProperty(key);
		return file;
		
	}

}
