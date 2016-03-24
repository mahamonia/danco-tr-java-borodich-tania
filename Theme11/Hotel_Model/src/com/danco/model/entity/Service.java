package com.danco.model.entity;

public class Service extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2916383920457058904L;
	private String name;
	private int price;
	
	private Chek chek;
	
	public Service(String name, int price) {
		this.name = name;
		this.price = price;
		this.chek = null;
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

	public Chek getChek() {
		return chek;
	}

	public void setChek(Chek chek) {
		this.chek = chek;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
