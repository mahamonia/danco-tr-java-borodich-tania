package com.danco.training.entity;

import java.io.Serializable;

public class AdditionalService extends Service implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7034860416131383102L;
	private String description;
	private int addPrice;

	public AdditionalService(int Id, String name, int price, String description, int addPrice) {
		super(Id, name, price);
		this.description = description;
		this.addPrice = addPrice;

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAddPrice() {
		return addPrice;
	}

	public void setAddPrice(int addPrice) {
		this.addPrice = addPrice;
	}

	
}
