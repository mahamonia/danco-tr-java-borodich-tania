package com.danco.model.entity;

import java.io.Serializable;

public abstract class BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4299767301776468357L;

	public BaseModel() {

	}
	
	public abstract int getId() ;

	public abstract void setId(int id) ;


	@Override
	public int hashCode() {
		return (getId() != 0) ? (this.getClass().hashCode() + getId()) : super.hashCode();

	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof BaseModel) && (getId() != 0) ? getId() == (((BaseModel) other)
				.getId()) : (other == this);
	}

}
