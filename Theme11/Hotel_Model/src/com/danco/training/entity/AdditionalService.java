package com.danco.training.entity;

public class AdditionalService extends DailService{

	private String description;
	private int addPrice;

	public AdditionalService(String name, int price, String description, int addPrice ) {
		super( name, price);
		this.description = description;
		this.setAddPrice(addPrice);

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
