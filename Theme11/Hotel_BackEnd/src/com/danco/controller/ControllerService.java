package com.danco.controller;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerService;
import com.danco.api.backend.IParseUtilityCSVForService;
import com.danco.api.dao.IServiceDao;
import com.danco.model.entity.Service;

public class ControllerService implements IControllerService {

	private static Logger LOGGER = LogManager
			.getLogger(ControllerService.class);

	@Injection
	private IParseUtilityCSVForService utility;
	@Injection
	private IServiceDao serviceDao;

	public ControllerService() {
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
	public void updateService(Connection connect, Service service) {
		try {
			serviceDao.update(connect, service);
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
		Service service = null;
		try {
			service = serviceDao.getById(connect, idService);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return service;
	}

	@Override
	public List<Service> getListService(Connection connect) {
		List<Service> list = null;
		try {
			list = serviceDao.getList(connect);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}

	public List<Service> getGuestThemServices(Connection connect, int idGuest) {
		List<Service> list = null;
		try {
			list = serviceDao.getGuestThemServices(connect, idGuest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;

	}

	@Override
	public List<Service> getServiceSortedBy(Connection connect, String param) {
		List<Service> list = null;
		try {
			list = serviceDao.getListServiceSorted(connect, param);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}

	@Override
	public int getServiceSumPrice(Connection connect, int idGuest) {
		int sum = 0;
		try {
			sum = serviceDao.getSumServiceForGuest(connect, idGuest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return sum;
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
	public List<Service> importServicesList() {
		return utility.importData();		
	}

	@Override
	public void exportServicesList(Connection connect) {
		try {
			utility.exportData(serviceDao.getList(connect));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}