package com.tr;

public class Guest{

	private int Id;
	@ConfigProperty(configName = "", propertyName="",type="String")
	private String name;
	private String pasport;


	public Guest(int Id, String name, String pasport) {
		this.Id = Id;
		this.name = name;
		this.pasport = pasport;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasport() {
		return pasport;
	}

	public void setPasport(String pasport) {
		this.pasport = pasport;
	}


	public int getId() {
		return Id;
	}

}
