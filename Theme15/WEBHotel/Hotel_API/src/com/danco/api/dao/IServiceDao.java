package com.danco.api.dao;

import java.util.List;

import org.hibernate.Session;

import com.danco.model.entity.Service;

public interface IServiceDao extends IBaseDao<Service> {
	
	public List<Service> getList(Session session, String param)throws Exception;

	public List<Service> getGuestThemServices(Session session, int idGuest)throws Exception;

	public int getSumServiceForGuest(Session session, int idGuest)throws Exception;
}
