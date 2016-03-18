package com.danco.training.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataSource {
	
	private static DataSource source; 
	private Statement statement;
	private Connection connection;
	
	private static final Logger LOGGER = LogManager.getLogger(DataSource.class);
	
	private DataSource() {
	}
	
	public static DataSource getInstance() {

		if (source == null) {
			source = new DataSource();
		}
		return source;
	}
	public Statement openConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Hotel_DB", "root", "root");
			statement = connection.createStatement();
			
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return statement;
		
	}
	
	public void closeConnection() {
		
		try {
			connection.close();
			statement.close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}

}
