package com.danco.model.entity;

import java.util.List;

public class Guest extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -736915922230762330L;
	private String name;
	private String pasport;
	
	private List<Chek> chekList;

	public Guest(String name, String pasport) {
		this.name = name;
		this.pasport = pasport;
		
		this.chekList = null;

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
	
	public List<Chek> getChekList() {
		return chekList;
	}

	public void setChekList(List<Chek> chekList) {
		this.chekList = chekList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
