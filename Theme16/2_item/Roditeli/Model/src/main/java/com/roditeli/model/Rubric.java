package com.roditeli.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Rubric")
public class Rubric extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9152644752684067628L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	private String name;
	
	public Rubric() {
	}

	public Rubric(String name) {
		this.name = name;
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

}
