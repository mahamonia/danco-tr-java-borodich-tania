package com.danco.training.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.comparator.Comparator;
import com.danco.training.entity.AdditionalService;
import com.danco.training.utility.ParseUtilityCSVForAdditionalService;

public class ControllerAdditionalService implements
		IControllerAdditionalService {

	private static Logger logger = LogManager
			.getLogger(ControllerAdditionalService.class);

	private List<AdditionalService> servicesList;
	private ParseUtilityCSVForAdditionalService utility = ParseUtilityCSVForAdditionalService
			.getInstance();

	public ControllerAdditionalService(List<AdditionalService> servicesList) {
		this.servicesList = servicesList;

	}

	@Override
	public void createService(AdditionalService service) {
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
	public void updateService(AdditionalService service) {
		try {
			int i = getIndexService(service);
			this.servicesList.set(i, service);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public void deleteService(AdditionalService service) {
		try {
			int i = getIndexService(service);
			if (i != -1) {
				servicesList.remove(i);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());

		}
	}

	private int getIndexService(AdditionalService service) {
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
				if (this.servicesList.get(i).getId() == Id) {
					return i;
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return -1;
	}

	@Override
	public AdditionalService getService(int Id) {
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
	public List<AdditionalService> getListAdditionalService() {

		return this.servicesList;

	}

	@Override
	public void setListAdditionalService(List<AdditionalService> servicesList) {

		this.servicesList = servicesList;
	}

	@Override
	public List<AdditionalService> printServicesSortedByPrice(
			List<AdditionalService> servicesList) {
		try {
			Collections.sort(servicesList, Comparator.SERVICE_BY_PRICE);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return servicesList;
	}

	@Override
	public List<AdditionalService> printServicesSortedByName(
			List<AdditionalService> servicesList) {

		try {
			Collections.sort(servicesList, Comparator.SERVICE_BY_NAME);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return servicesList;
	}

	@Override
	public int getServicesSumPrice(List<AdditionalService> servicesList) {

		List<AdditionalService> addServicesList = new ArrayList<AdditionalService>();

		int sum = 0;
		try {
			for (int i = 0; i < addServicesList.size(); i++) {
				sum += addServicesList.get(i).getPrice()
						+ addServicesList.get(i).getAddPrice();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sum;
	}

	@Override
	public void changePrice(AdditionalService service, int price) {

		try {
			service.setPrice(price);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void changeAdditionalPrice(AdditionalService service, int price) {
		try {
			service.setAddPrice(price);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public List<AdditionalService> importServicesList() {
		return utility.importData();
	}

	@Override
	public void exportServicesList(List<AdditionalService> servicesList) {
		utility.exportData(servicesList);
	}

}
