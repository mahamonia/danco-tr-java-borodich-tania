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
@Table(name = "Event")
public class Event extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3082440532758064130L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;
	private String text;
	
	public Event() {
	}

	public Event(Date date, String text) {
		this.date = date;
		this.text = text;
	}
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
