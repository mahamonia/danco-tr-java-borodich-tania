package com.roditeli.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Adress")
public class Adress extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6855136257472359915L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name = "country")
	private String country;
	@Column(name = "city")
	private String city;
	@Column(name = "street")
	private String street;
	@Column(name = "house")
	private int house;
	@Column(name = "apartment")
	private int apartment;
	
	private List<User> userList;

	public Adress() {
	}
	
	public Adress(String country, String city, String street, int house, int apartment) {
		this.country = country;
		this.city = city;
		this.street = street;
		this.house = house;
		this.apartment = apartment;
		this.userList = new ArrayList<User>();
	}
	
	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id =id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getHouse() {
		return house;
	}

	public void setHouse(int house) {
		this.house = house;
	}

	public int getApartment() {
		return apartment;
	}

	public void setApartment(int apartment) {
		this.apartment = apartment;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
