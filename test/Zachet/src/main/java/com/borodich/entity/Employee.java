package com.borodich.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Employee")
public class Employee extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7794362759060442324L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "f_name")
	private String fName;
	
	@Column(name = "l_name")
	private String lName;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "b_date")
	private Date bDate;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition="enum('FEMALE','MAN')")
	private Gender gender;
	
	private String title;
	
	public Employee() {
	}
	
	public Employee(String fName, String lName, Date bDate, Gender gender, String title) {
		this.fName = fName;
		this.lName = lName;
		this.bDate = bDate;
		this.gender = gender;
		this.title = title;

	}
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
}
