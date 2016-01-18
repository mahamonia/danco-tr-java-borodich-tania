package com.danco.training.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.comparator.Comparator;
import com.danco.training.entity.AdditionalService;
import com.danco.training.entity.Service;

public class ControllerAdditionalService {

	private static Logger logger = LogManager
			.getLogger(ControllerAdditionalService.class);

	private List<AdditionalService> servicesList;

	public ControllerAdditionalService(List<AdditionalService> servicesList) {
		this.servicesList = servicesList;

	}

	public void createService(AdditionalService service) {
		try {
			servicesList.add(service);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void updateService(AdditionalService service) {
		try {
			int i = getIndexService(service);
			servicesList.set(i, service);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	public void deleteService(Service service) {
		try {
			int i = getIndexService(service);
			if (i != -1) {
				servicesList.remove(i);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());

		}
	}

	private int getIndexService(Service service) {
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
				if (servicesList.get(i) != null
						&& servicesList.get(i).getId() == Id) {
					return i;
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return -1;
	}

	public Service getService(int Id) {
		try {
			for (int i = 0; i < servicesList.size(); i++) {
				if (servicesList.get(i).getId() == Id) {
					return servicesList.get(i);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;

	}

	public List<AdditionalService> getListAddService() {

		return this.servicesList;

	}

	public List<AdditionalService> printServicesSortedByPrice(
			List<AdditionalService> servicesList) {
		try {
			Collections.sort(servicesList, Comparator.SERVICE_BY_PRICE);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return servicesList;
	}

	public List<AdditionalService> printServicesSortedByName(
			List<AdditionalService> servicesList) {

		try {
			Collections.sort(servicesList, Comparator.SERVICE_BY_NAME);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return servicesList;
	}

	public List<AdditionalService> printServicesThemPriceById(int[] IdService) {

		List<AdditionalService> newServiceList = new ArrayList<AdditionalService>();

		try {
			for (int i = 0; i < servicesList.size(); i++) {
				for (int j = 0; j < IdService.length - 1; j++) {
					if (servicesList.get(i).getId() == IdService[j]) {
						newServiceList.add((AdditionalService) servicesList
								.get(i));
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return newServiceList;
	}

	public int getServicesSumPriceById(int[] IdService) {

		List<AdditionalService> addServicesList = new ArrayList<AdditionalService>();
		for (Service servicesList : servicesList) {
			addServicesList.add((AdditionalService) servicesList);
		}

		int sum = 0;
		try {

			for (int i = 0; i < addServicesList.size(); i++) {
				for (int j = 0; j < IdService.length - 1; j++) {
					if (addServicesList.get(i).getId() == IdService[j]) {
						sum += addServicesList.get(i).getPrice()
								+ addServicesList.get(i).getAddPrice();
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return sum;
	}

	public void changePrice(Service service, int price) {

		try {
			service.setPrice(price);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

}
