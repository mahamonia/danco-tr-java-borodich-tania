package com.danco.model.entity;

public class Service extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2916383920457058904L;
	private String name;
	private int price;

	private int idCheck;
	
	public Service(String name, int price) {
		this.name = name;
		this.price = price;
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

	public int getIdCheck() {
		return idCheck;
	}

	public void setIdCheck(int idCheck) {
		this.idCheck = idCheck;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
