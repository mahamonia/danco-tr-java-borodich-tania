package com.danco.training.entity;

public class Order {
	private int IdOrder;
	private int IdGuest;
	private Service [] Services;
	private final int MAX_SERVICES;
	private int SumPrice;
	
	public Order (int IdOrder, int IdGuest, Service [] Services, int SumPrice){
		
		MAX_SERVICES = Services.length;
		this.IdOrder = IdOrder;
		this.IdGuest = IdGuest;
		this.Services = Services;
		this.SumPrice = SumPrice;
	}



	public int getIdGuest() {
		return IdGuest;
	}

	public void setIdGuest(int idGuest) {
		IdGuest = idGuest;
	}

	public Service[] getServices() {
		return Services;
	}

	public void setServices(Service[] services) {
		Services = services;
	}

	public int getSumPrice() {
		return SumPrice;
	}

	public void setSumPrice(int sumPrice) {
		SumPrice = sumPrice;
	}

	public int getMAX_SERVICES() {
		return MAX_SERVICES;
	}

	public int getIdOrder() {
		return IdOrder;
	}

	public void setIdOrder(int idOrder) {
		IdOrder = idOrder;
	}
	
}
