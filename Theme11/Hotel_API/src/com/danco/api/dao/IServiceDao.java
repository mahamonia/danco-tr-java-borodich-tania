package com.danco.api.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.danco.model.entity.Service;

public interface IServiceDao extends IBaseDao<Service> {
	
	public List<Service> getListServiceSorted(Connection connect, String param)throws SQLException;

	public List<Service> getGuestThemServices(Connection connect, int idGuest)throws SQLException;

	public int getSumServiceForGuest(Connection connect, int idGuest)throws SQLException;
}
