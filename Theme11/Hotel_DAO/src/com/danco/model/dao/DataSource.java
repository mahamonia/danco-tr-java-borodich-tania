package com.danco.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.ConfigProperty;

public class DataSource {
	
	private Connection connection;
	
	@ConfigProperty(configName = "jdbc_config.properties", propertyName = "driver", type = "String")
	private String driver = "com.mysql.jdbc.Driver";
	
	@ConfigProperty(configName = "jdbc_config.properties", propertyName = "url", type = "String")
	private String url = "jdbc:mysql://localhost:3306/Hotel_service";
	
	@ConfigProperty(configName = "jdbc_config.properties", propertyName = "userName", type = "String")
	private String userName = "root";
	
	@ConfigProperty(configName = "jdbc_config.properties", propertyName = "userPassvord", type = "String")
	private String userPassvord = "root";
		
	private static final Logger LOGGER = LogManager.getLogger(DataSource.class);
	
	public DataSource() {
	}
	
	public Connection openConnection(){
		try {
			Class.forName(driver).newInstance();

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		try {
			connection = DriverManager.getConnection(url, userName, userPassvord);
			
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return connection;
		
	}
	
	public void closeConnection() {
		
		try {
			connection.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}

}
