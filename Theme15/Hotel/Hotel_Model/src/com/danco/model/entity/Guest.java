package com.danco.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Guest")
public class Guest extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -736915922230762330L;
	
	@Id
	@GeneratedValue
	@Column(name = "idGuest")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "pasport")
	private String pasport;

	@OneToMany(mappedBy = "guest")
	private List<Chek> chekList;

	public Guest(){		
	}
	
	public Guest(String name, String pasport) {
		this.name = name;
		this.pasport = pasport;

		this.chekList = null;
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
