package com.danco.model.entity;

import java.io.Serializable;

public class BaseModel implements Serializable {
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

	@Override
	public int hashCode() {
		return (id != 0) ? (this.getClass().hashCode() + id) : super.hashCode();

	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof BaseModel) && (id != 0) ? id == (((BaseModel) other)
				.getId()) : (other == this);
	}

}
