package com.danco.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Audit")
public class Audit extends BaseModel{

	private static final long serialVersionUID = 2747113922059660245L;

	@Id
	@GeneratedValue
	@Column(name = "idAudit")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "User_idUser")
	private User user;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date; 
	
	@Column(name = "resources")
	private String resources;
	
	public Audit() {
	}
	
	public Audit(User user, Date date, String resources){
		this.user = user;
		this.date = date;
		this.resources = resources;
	}
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
