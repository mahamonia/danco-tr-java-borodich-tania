package com.danco.training.controller;

import java.util.Arrays;

import com.danco.training.comparators.SortServiceByName;
import com.danco.training.comparators.SortServiceByPrice;
import com.danco.training.comparators.TypeSort;
import com.danco.training.entity.Service;

public class ControllerDailService implements IPrintService {

	private Service[] servicesList;
	private final int MAX_SERVICES;

	public ControllerDailService(Service[] servicesList) {
		MAX_SERVICES = servicesList.length;
		this.servicesList = servicesList;

	}

	public void createService(Service service) {

		int i = getNumberForNewService();
		servicesList[i] = service;

	}

	private int getNumberForNewService() {

		for (int i = 0; i < servicesList.length; i++) {
			if (servicesList[i] == null) {
				return i;
			}
		}
		return -1;
	}

	public void deleteService(Service service) {
		int i = getNumberService(service);
		if (i != -1) {
			servicesList[i] = null;
		}
	}

	private int getNumberService(Service service) {

		int numberService = getNumberServiceById(service.getId());
		return numberService;
	}

	private int getNumberServiceById(int Id) {
		for (int i = 0; i < this.servicesList.length; i++) {
			if (servicesList[i] != null && servicesList[i].getId() == Id) {
				return i;
			}
		}
		return -1;
	}

	public Service getService(int Id) {

		for (int i = 0; i < servicesList.length - 1; i++) {
			if (servicesList[i].getId() == Id) {
				return servicesList[i];
			}
		}
		return null;
	}

	@Override
	public void printServices(Service[] servicesList, TypeSort type) {

		sortedService(servicesList, type);

		for (int i = 0; i < servicesList.length - 1; i++) {
			System.out.println(servicesList[i].getName());
		}
	}

	@Override
	public void printServicesPrice(Service[] servicesList, TypeSort type) {

		sortedService(servicesList, type);

		for (int i = 0; i < servicesList.length - 1; i++) {
			System.out.println(servicesList[i].getName() + " cost "
					+ servicesList[i].getPrice());
		}

	}

	public void printServicesThemPriceById(int[] IdService) { // uses for guest
		for (int i = 0; i < servicesList.length - 1; i++) {
			for (int j = 0; j < IdService.length - 1; j++) {
				if (servicesList[i].getId() == IdService[j]) {
					System.out.println(servicesList[i].getName() + " cost "
							+ servicesList[i].getPrice());
				}
			}
		}
	}

	public int getServicesSumPriceById(int[] IdService) {
		int sum = 0;
		for (int i = 0; i < servicesList.length - 1; i++) {
			for (int j = 0; j < IdService.length - 1; j++) {
				if (servicesList[i].getId() == IdService[j]) {
					sum += servicesList[i].getPrice();
				}
			}
		}
		return sum;
	}
	
	

	public void changePrice(Service service, int price) {
		service.setPrice(price);
		System.out.println("change Price");

	}

	public void sortedService(Service[] servicesList, TypeSort type) {

		switch (type) {
		case BY_NAME:
			Arrays.sort(servicesList, new SortServiceByName());
			break;
		case BY_PRICE:
			Arrays.sort(servicesList, new SortServiceByPrice());
			break;
		default:
			Arrays.sort(servicesList, new SortServiceByName());
			break;
		}
	}

	public int getMAX_SERVICES() {
		return MAX_SERVICES;
	}

}
