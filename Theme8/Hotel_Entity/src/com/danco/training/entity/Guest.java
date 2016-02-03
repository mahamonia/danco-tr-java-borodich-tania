package com.danco.training.entity;

import java.io.Serializable;

public class Guest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2154910180085017753L;
	private int Id;
	private String name;
	private String pasport;
	private String dateInSettle;
	private String dateOutSettle;
	private Order order;

	public Guest(int Id, String name, String pasport, String dateInSettle, String dateOutSettle) {
		this.Id = Id;
		this.name = name;
		this.pasport = pasport;
		this.dateInSettle = dateInSettle;
		this.dateOutSettle = dateOutSettle;
		this.order = null;
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

	public String getPasport() {
		return pasport;
	}

	public void setPasport(String pasport) {
		this.pasport = pasport;
	}

	public String getDateInSettle() {
		return dateInSettle;
	}

	public void setDateInSettle(String dateInSettle) {
		this.dateInSettle = dateInSettle;
	}

	public String getDateOutSettle() {
		return dateOutSettle;
	}

	public void setDateOutSettle(String dateOutSettle) {
		this.dateOutSettle = dateOutSettle;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}


	
}
