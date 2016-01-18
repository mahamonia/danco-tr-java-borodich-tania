package com.danco.config;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyProgramm {
	private static PropertyProgramm config;
	private Properties properties;
	private FileInputStream fileInputStream;
	
	
	private PropertyProgramm() {
		properties = new Properties();
		File file = new File("property.txt");

		try {
			fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream);

		} catch (IOException e) {
		}finally{
			if (fileInputStream != null) {
			}
			try {
				fileInputStream.close();
			} catch (IOException e) {
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
