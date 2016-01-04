package com.danco.training.entity;

public class AdditionalService extends Service{
	
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
