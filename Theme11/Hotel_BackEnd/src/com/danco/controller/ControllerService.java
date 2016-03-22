package com.danco.controller;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerService;
import com.danco.api.backend.IParseUtilityCSVForService;
import com.danco.model.dao.ServiceDao;
import com.danco.model.entity.Service;

public class ControllerService implements IControllerService{
	

	private static Logger LOGGER = LogManager
			.getLogger(ControllerService.class);

	@Injection
	private IParseUtilityCSVForService utility;
	
	private  ServiceDao serviceDao;

	public ControllerService(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;

}

	@Override
	public void createService(Connection connect, Service service) {
		try {
			serviceDao.create(connect, service);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public int getIdForNewService(Connection connect) {
		return 0;
	}

	@Override
	public void updateService(Connection connect ,int idService) {
		try {
			serviceDao.update(connect, idService);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
	}

	@Override
	public void deleteService(Connection connect, int idService) {
		try {
			serviceDao.delete(connect, idService);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}	
	}

	@Override
	public Service getService(Connection connect, int idService) {
		return serviceDao.getById(connect, idService);
	}

	@Override
	public List<Service> getListService(Connection connect) {
		return serviceDao.getList(connect);
	}

	public List<Service> getGuestThemServices(Connection connect, int idGuest){	
		return serviceDao.getGuestThemServices(connect, idGuest);
		
	}
	@Override
	public List<Service> getServiceSortedByPrice(Connection connect ) {
		return serviceDao.getListServiceSortedByPrice(connect);
	}

	@Override
	public List<Service> getServiceSortedByName(Connection connect) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getServiceSumPrice(Connection connect, int idGuest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void changePrice(Connection connect, int idService, int price) {
		try {
			serviceDao.getById(connect, idService).setPrice(price);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}		
	}

	@Override
	public List<Service> importServicesList(Connection connect) {
		return utility.importData();
	}

	@Override
	public void exportServicesList(Connection connect) {
		utility.exportData(serviceDao.getList(connect));
	}
}