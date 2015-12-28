package com.danco.training.entity;

public class Service{
	
	private int Id;
	private String Name;
	private int Price;
	
	public Service(int Id, String Name, int Price) {
		this.Id = Id;
		this.Name = Name;
		this.Price = Price;
		
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

}
