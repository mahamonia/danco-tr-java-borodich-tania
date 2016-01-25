package com.danco.training.controller;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.comparator.Comparator;
import com.danco.training.entity.DailService;
import com.danco.training.utility.ParseUtilityCSVForDailService;

public class ControllerDailService implements IControllerDailService {

	private static Logger logger = LogManager
			.getLogger(ControllerDailService.class);

	private List<DailService> servicesList;
	private ParseUtilityCSVForDailService utility = ParseUtilityCSVForDailService.getInstance();

	public ControllerDailService(List<DailService> servicesList) {

		this.servicesList = servicesList;

	}

	@Override
	public void createService(DailService service) {
		try {
			this.servicesList.add(service);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	public int getIdForNewService() {
		int newId = 0;

		try {
			newId = this.servicesList.size() + 1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return newId;

	}

	@Override
	public void updateService(DailService service) {
		try {
			int i = getIndexService(service);
			this.servicesList.set(i, service);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void deleteService(DailService service) {
		try {
			int i = getIndexService(service);
			if (i != -1) {
				this.servicesList.remove(i);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	private int getIndexService(DailService service) {
		int indexService = 0;
		try {
			indexService = getIndexServiceById(service.getId());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return indexService;
	}

	private int getIndexServiceById(int Id) {
		try {
			for (int i = 0; i < this.servicesList.size(); i++) {
				if (this.servicesList.get(i) != null
						&& this.servicesList.get(i).getId() == Id) {
					return i;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return -1;
	}

	@Override
	public DailService getService(int Id) {
		try {
			for (int i = 0; i < this.servicesList.size(); i++) {
				if (this.servicesList.get(i).getId() == Id) {
					return this.servicesList.get(i);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<DailService> getListDailService() {
		return this.servicesList;

	}
	@Override
public void setListDailService(List<DailService> servicesList) {
		
		this.servicesList = servicesList;
	}

	@Override
	public List<DailService> printServicesSortedByPrice(List<DailService> servicesList) {
		try {
			Collections.sort(servicesList, Comparator.SERVICE_BY_PRICE);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return servicesList;
	}

	@Override
	public List<DailService> printServicesSortedByName(List<DailService> servicesList) {
		try {
			Collections.sort(servicesList, Comparator.SERVICE_BY_NAME);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return servicesList;

	}

	@Override
	public int getServicesSumPrice(List<DailService> serviceList) {

		int sum = 0;
		try {
			for (int i = 0; i < serviceList.size(); i++) {
				sum += serviceList.get(i).getPrice();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sum;
	}

	@Override
	public void changePrice(DailService service, int price) {
		try {
			service.setPrice(price);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	public List<DailService> importServicesList() {
		return utility.importData();
	}
	@Override
	public void exportServicesList(List<DailService> servicesList) {
		utility.exportData(servicesList);
	}
}
