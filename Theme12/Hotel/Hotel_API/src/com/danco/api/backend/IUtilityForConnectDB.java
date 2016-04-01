package com.danco.api.backend;

import java.sql.Connection;

public interface IUtilityForConnectDB {

	public void loadDriver();
	
	public Connection getConnection();

	public void closeConnection(Connection connection);

}
