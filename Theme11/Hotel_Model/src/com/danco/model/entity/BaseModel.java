package com.danco.model.entity;

import java.io.Serializable;

public class BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4299767301776468357L;
	private int id;
	
	public BaseModel() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
