package com.danco.api.backend;

import java.sql.Connection;
import java.util.List;

import com.danco.model.entity.Service;

public interface IControllerService {

	public void createService(Connection connect, Service service);

	public void updateService(Connection con, Service service);

	public void deleteService(Connection con, int idService);

	public Service getService(Connection con, int idService);

	public List<Service> getListService(Connection con);
	
	public List<Service> getGuestThemServices(Connection con, int idGuest);

	public List<Service> getServiceSortedByPrice(Connection con);

	public List<Service> getServiceSortedByName(Connection con);

	public int getServiceSumPrice(Connection con, int idGuest);

	public void changePrice(Connection con, int idService, int price);

	public List<Service> importServicesList(Connection con);

	public void exportServicesList(Connection con);

}
