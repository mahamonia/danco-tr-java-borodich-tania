package com.danco.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Service")
public class Service extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2916383920457058904L;
	
	@Column(name = "name", unique = true)
	private String name;
	
	@Column(name = "price", unique = true)
	private int price;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Chek_idChek", nullable = false)
	private Chek cheks;
	
	public Service(){
		
	}
	
	public Service(String name, int price) {
		this.name = name;
		this.price = price;
		this.cheks = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Chek getChek() {
		return cheks;
	}

	public void setChek(Chek chek) {
		this.cheks = chek;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
