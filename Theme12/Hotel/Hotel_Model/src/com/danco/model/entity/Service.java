package com.danco.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Service")
public class Service extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2916383920457058904L;

	@Id
	@GeneratedValue
	@Column(name = "idService")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private int price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Chek_idChek")
	private Chek chek;
	
	public Service(){		
	}
	
	public Service(String name, int price) {
		this.name = name;
		this.price = price;
		this.chek = null;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
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
		return chek;
	}

	public void setChek(Chek chek) {
		this.chek = chek;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
