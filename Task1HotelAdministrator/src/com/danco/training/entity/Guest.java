package com.danco.training.entity;

public class Guest {
	
	private int Id;
	private String Name;
	private String Pasport;
	private int IdOrder;

	public Guest(int Id, String Name, String Pasport, int IdOrder) {
		this.Id = Id;
		this.Name = Name;
		this.Pasport = Pasport;
		this.setIdOrder(IdOrder);
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPasport() {
		return Pasport;
	}

	public void setPasport(String pasport) {
		Pasport = pasport;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}


	public int getIdOrder() {
		return IdOrder;
	}

	public void setIdOrder(int idOrder) {
		IdOrder = idOrder;
	}
	
}
