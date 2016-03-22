package com.danco.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerService;
import com.danco.api.backend.IParseUtilityCSVForService;
import com.danco.model.dao.DataSource;
import com.danco.model.dao.ServiceDao;
import com.danco.model.entity.Service;

public class ControllerService implements IControllerService{
	

	private static Logger LOGGER = LogManager
			.getLogger(ControllerService.class);

	@Injection
	private IParseUtilityCSVForService utility;
	
	private  ServiceDao serviceDao;
	private DataSource source;

	public ControllerService(DataSource source, ServiceDao serviceDao) {
		this.source = source;
		this.serviceDao = serviceDao;

}

	@Override
	public void createService(Service service) {
		try {
			serviceDao.create(source, service);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public int getIdForNewService() {
		return 0;
	}

	@Override
	public void updateService(int idService) {
		try {
			serviceDao.update(source, idService);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
	}

	@Override
	public void deleteService(int idService) {
		try {
			serviceDao.delete(source, idService);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}	
	}

	@Override
	public Service getService(int idService) {
		return serviceDao.getById(source, idService);
	}

	@Override
	public List<Service> getListService() {
		return serviceDao.getList(source);
	}

	public List<Service> getGuestThemServices(int idGuest){	
		return serviceDao.getGuestThemServices(source, idGuest);
		
	}
	@Override
	public List<Service> getServiceSortedByPrice() {
		return serviceDao.getListServiceSortedByPrice(source);
	}

	@Override
	public List<Service> getServiceSortedByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getServiceSumPrice(int idGuest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void changePrice(int idService, int price) {
		try {
			serviceDao.getById(source, idService).setPrice(price);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}		
	}

	@Override
	public List<Service> importServicesList() {
		return utility.importData();
	}

	@Override
	public void exportServicesList() {
		utility.exportData(serviceDao.getList(source));
	}
}