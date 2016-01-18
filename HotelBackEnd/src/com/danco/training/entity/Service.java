package com.danco.training.entity;

import java.io.Serializable;

public abstract class Service implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4763230708974893196L;
	private int Id;
	private String name;
	private int price;

	
	public Service(int Id, String name, int price) {
		this.Id = Id;
		this.name = name;
		this.price = price;

		
	}

	public int getId() {
		return Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
