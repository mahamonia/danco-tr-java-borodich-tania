package com.roditeli.model;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Children")
public class Children extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8416156341257824350L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	private String name;
	@Temporal(TemporalType.DATE)
	@Column(name = "date_birth")
	private Date dateBirth;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_profile_fk")
	@JsonManagedReference
	private User parent;

	public Children() {
	}

	public Children(String name, Date dateBirth, User parent) {
		this.name = name;
		this.dateBirth = dateBirth;
		this.parent = parent;
	}

	@Override
	public int getId() {
		return this.id;
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

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public User getParent() {
		return parent;
	}

	public void setParent(User parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return name + " dateBirth " + dateBirth;
	}

}
