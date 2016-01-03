package com.danco.training.controller;

import java.util.Arrays;

import com.danco.training.comparator.Comparator;
import com.danco.training.entity.DailService;
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

		for (int i = 0; i < servicesList.length; i++) {
			if (servicesList[i].getId() == Id) {
				return servicesList[i];
			}
		}
		return null;
	}

	@Override
	public Service [] printServicesSortedByPrice(Service[] servicesList) {
			
		Arrays.sort(servicesList, Comparator.SERVICE_BY_PRICE);
		return servicesList;
	}
	
	@Override
	public Service [] printServicesSortedByName(Service[] servicesList) {

		Arrays.sort(servicesList, Comparator.SERVICE_BY_NAME);
		return servicesList;

	}

	public Service [] printServicesThemPriceById(int[] IdService) { // uses for guest
		
		Service [] newServiceList = new DailService[servicesList.length] ;
		for (int i = 0; i < servicesList.length; i++) {
			for (int j = 0; j < IdService.length-1; j++) {
				if (servicesList[i].getId() == IdService[j]) {
					newServiceList[i]=servicesList[i];
				}
			}
		}
		return newServiceList;
	}

	public int getServicesSumPriceById(int[] IdService) {
		int sum = 0;
		for (int i = 0; i < servicesList.length; i++) {
			for (int j = 0; j < IdService.length; j++) {
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

	public int getMAX_SERVICES() {
		return MAX_SERVICES;
	}

}
