package com.danco.training.entity;

import java.io.Serializable;

public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3299762870887211471L;
	private int Id;
	private int idGuest;
	private int [] idServices;
	private final int MAX_SERVICES;
	private int sumPrice;
	private Guest guest;
	private DailService dailService;
	private AdditionalService addService;
	
	public Order (int Id, int idGuest, int [] idServices, int sumPrice){
		
		MAX_SERVICES = idServices.length;
		this.Id = Id;
		this.idGuest = idGuest;
		this.idServices = idServices;
		this.sumPrice = sumPrice;
	}

	public int[] getIdServices() {
		return idServices;
	}

	public void setIdServices(int[] idServices) {
		this.idServices = idServices;
	}

	public int getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}

	public int getId() {
		return Id;
	}

	public int getIdGuest() {
		return idGuest;
	}

	public int getMAX_SERVICES() {
		return MAX_SERVICES;
	}

	
	
}
