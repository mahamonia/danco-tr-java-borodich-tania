package com.danco.training.controller;

import java.util.Arrays;

import com.danco.training.comparator.Comparator;
import com.danco.training.entity.AdditionalService;
import com.danco.training.entity.Service;

public class ControllerAdditionalService implements IPrintService {

	private Service[] servicesList;
	private final int MAX_SERVICES;

	public ControllerAdditionalService(Service[] servicesList) {
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
	public Service [] printServicesSortedByPrice(Service[] servicesList) {

		Arrays.sort(servicesList, Comparator.SERVICE_BY_PRICE);
		return servicesList;
	}
	
	@Override
	public Service [] printServicesSortedByName(Service[] servicesList) {

		Arrays.sort(servicesList, Comparator.SERVICE_BY_NAME);
		return servicesList;

	}
	public Service []  printServicesThemPriceById(int[] IdService) {
		
		Service [] newServiceList = new AdditionalService[servicesList.length] ;
		for (int i = 0; i < servicesList.length; i++) {
			for (int j = 0; j < IdService.length - 1; j++) {
				if (servicesList[i].getId() == IdService[j]) {
					newServiceList[i]=servicesList[i];
				}
			}
		}
		return newServiceList;
	}

	public int getServicesSumPriceById(int[] IdService) {
		
		AdditionalService [] addServicesList = (AdditionalService [])servicesList;
		
		int sum = 0;
		for (int i = 0; i < addServicesList.length - 1; i++) {
			for (int j = 0; j < IdService.length - 1; j++) {
				if (addServicesList[i].getId() == IdService[j]) {
					sum += addServicesList[i].getPrice()+addServicesList[i].getAddPrice();
				}
			}
		}
		return sum;
	}

	public void changePrice(Service service, int price) {
		service.setPrice(price);
		System.out.println("changePrice");

	}
	
	public int getMAX_SERVICES() {
		return MAX_SERVICES;
	}

}
