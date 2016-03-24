package com.danco.model.entity;

public class Guest extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -736915922230762330L;
	private String name;
	private String pasport;

	public Guest(String name, String pasport) {
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
