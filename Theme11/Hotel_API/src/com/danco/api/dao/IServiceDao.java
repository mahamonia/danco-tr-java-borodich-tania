package com.danco.api.dao;

import java.sql.Connection;

import java.util.List;

import com.danco.model.entity.Service;

public interface IServiceDao extends IBaseDao<Service> {
	
	public List<Service> getListServiceSortedByPrice(Connection connect);

	public List<Service> getListServiceSortedByName(Connection connect);

	public List<Service> getGuestThemServices(Connection connect, int idGuest);

	public int getSumServiceForGuest(Connection connect, int idGuest);
}
