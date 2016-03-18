package com.danco.training.entity;

public class Guest extends BaseModel{

	private String name;
	private String pasport;

	private Check check;

	public Guest(String name, String pasport) {
		this.name = name;
		this.pasport = pasport;
		this.check = null;
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

	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}
	
}
