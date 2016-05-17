package com.danco.api.backend;

import java.util.List;

import org.hibernate.Session;

import com.danco.model.entity.Service;

public interface IControllerService {

	public void createService(Session session, Service service);

	public void updateService(Session session, Service service);

	public void deleteService(Session session, int idService);

	public Service getService(Session session, int idService);

	public List<Service> getListService(Session session, String param);
	
	public List<Service> getGuestThemServices(Session session, int idGuest);

	public int getServiceSumPrice(Session session, int idGuest);

	public void changePrice(Session session, int idService, int price);

	public List<Service> importServicesList(String nameFile);

	public void exportServicesList(Session session, String nameFile);

}
