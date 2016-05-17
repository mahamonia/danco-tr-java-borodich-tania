package com.danco.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

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
	public void createService(Session session, Service service) {
		try {
			serviceDao.create(session, service);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void updateService(Session session, Service service) {
		try {
			serviceDao.update(session, service);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void deleteService(Session session, int idService) {
		try {
			serviceDao.delete(session, serviceDao.getById(session, idService));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public Service getService(Session session, int idService) {
		Service service = null;
		try {
			service = serviceDao.getById(session, idService);
			System.out.println();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return service;
	}

	@Override
	public List<Service> getListService(Session session, String param) {
		List<Service> list = null;
		try {
			list = serviceDao.getList(session, param);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}

	public List<Service> getGuestThemServices(Session session, int idGuest) {
		List<Service> list = null;
		try {
			list = serviceDao.getGuestThemServices(session, idGuest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;

	}

	@Override
	public int getServiceSumPrice(Session session, int idGuest) {
		int sum = 0;
		try {
			sum = serviceDao.getSumServiceForGuest(session, idGuest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return sum;
	}

	@Override
	public void changePrice(Session session, int idService, int price) {
		try {
			serviceDao.getById(session, idService).setPrice(price);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public List<Service> importServicesList(String nameFile) {
		return utility.importData(nameFile);		
	}

	@Override
	public void exportServicesList(Session session,String nameFile) {
		try {
			utility.exportData(serviceDao.getList(session, "id"), nameFile);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}