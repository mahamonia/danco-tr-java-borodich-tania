package com.danco.api.dao;

import java.sql.Connection;

public interface IDataSource {
	
	public Connection openConnection();
	public void closeConnection() ;

}
