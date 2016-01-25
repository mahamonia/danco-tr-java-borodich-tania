package com.tr;

public class Order{

	private int Id;
	@ConfigProperty(configName = "", propertyName="Guest",type="String")
	private String name;
	private Guest guest;

	public Order(int Id, String name, Guest guest) {

		this.Id = Id;
		this.name = name;
		this.guest = guest;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
}
