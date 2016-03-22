package com.danco.api.backend;

import java.util.List;

import com.danco.model.entity.Service;

public interface IControllerService {

	public void createService(Service service);

	public int getIdForNewService();

	public void updateService(int idService);

	public void deleteService(int idService);

	public Service getService(int idService);

	public List<Service> getListService();
	
	public List<Service> getGuestThemServices(int idGuest);

	public List<Service> getServiceSortedByPrice();

	public List<Service> getServiceSortedByName();

	public int getServiceSumPrice(int idGuest);

	public void changePrice(int idService, int price);

	public List<Service> importServicesList();

	public void exportServicesList();

}
