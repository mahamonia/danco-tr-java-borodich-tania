package com.danco.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.ConfigProperty;
import com.danco.api.backend.IUtilityForConnectDB;

public class UtilityForConnectDB implements IUtilityForConnectDB{
	
	@ConfigProperty(configName = "jdbc_config.properties", propertyName = "driver", type = "String")
	private String driver;

	@ConfigProperty(configName = "jdbc_config.properties", propertyName = "url", type = "String")
	private String url;

	@ConfigProperty(configName = "jdbc_config.properties", propertyName = "userName", type = "String")
	private String userName;

	@ConfigProperty(configName = "jdbc_config.properties", propertyName = "userPassvord", type = "String")
	private String userPassvord;
	
	private static final Logger LOGGER = LogManager
			.getLogger(UtilityForConnectDB.class);
		
	public UtilityForConnectDB() {	
	}
	
	@Override
	public void loadDriver(){
		try {
			Class.forName(driver).newInstance();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
	@Override
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, userName,
					userPassvord);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return connection;
	}
	@Override
	public void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}
	
}
