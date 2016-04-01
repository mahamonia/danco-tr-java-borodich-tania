package com.danco.api.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import com.danco.model.entity.Service;

public interface IServiceDao extends IBaseDao<Service> {
	
	public List<Service> getListServiceSorted(Session session, String param)throws SQLException;

	public List<Service> getGuestThemServices(Session session, int idGuest)throws SQLException;

	public int getSumServiceForGuest(Session session, int idGuest)throws SQLException;
}
