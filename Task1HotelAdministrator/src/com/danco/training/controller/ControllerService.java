package com.danco.training.controller;


import com.danco.training.entity.Service;

public class ControllerService implements IPrintService{
	
	private Service [] Services;
	//private final int MAX_SERVICES;
	
	public ControllerService(Service [] Services) {
		//MAX_SERVICES = Services.length;
		this.Services = Services;

	}


	public void changePrice(Service Service, int Price) {
		System.out.println("changePrice");
		
	}


	public void printService(Service Service) {
		System.out.println("printService");
		
	}


	public void printServicePrice(Service Service) {
		System.out.println("printServicePrice");
		
	}


	@Override
	public void printService(Service[] Services) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void printServicePrice(Service[] Services) {
		// TODO Auto-generated method stub
		
	}


//	public int getMAX_SERVICES() {
//		return MAX_SERVICES;
//	}

}
