package com.danco.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Guest", uniqueConstraints = {
		@UniqueConstraint(columnNames = "name"),
		@UniqueConstraint(columnNames = "pasport") })
public class Guest extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -736915922230762330L;
	@Column(name = "name",unique = true)
	private String name;

	@Column(name = "pasport", unique = true)
	private String pasport;

	@OneToMany(targetEntity = Chek.class, mappedBy = "guests")
	private List<Chek> chekList;

	public Guest(){
		
	}
	
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
