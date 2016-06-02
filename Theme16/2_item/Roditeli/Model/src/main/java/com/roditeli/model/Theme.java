package com.roditeli.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "Theme")
public class Theme extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1481132250949065752L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	private String name;
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;
	
	public Theme() {
	}

	public Theme(String name, Date date) {
		this.name = name;
		this.date = date;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
