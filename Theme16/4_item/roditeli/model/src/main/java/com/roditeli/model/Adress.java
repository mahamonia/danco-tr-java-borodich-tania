package com.roditeli.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Adress")
public class Adress extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6855136257472359915L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	private String country;
	private String city;
	private String street;
	private String house;
	private String apartment;

	@OneToMany(mappedBy = "adress", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<UserProfile> userList;

	public Adress() {
	}

	public Adress(String country, String city, String street, String house,
			String apartment) {
		this.country = country;
		this.city = city;
		this.street = street;
		this.house = house;
		this.apartment = apartment;
		this.userList = new ArrayList<UserProfile>();
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
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

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public List<UserProfile> getUserList() {
		return userList;
	}

	public void setUserList(List<UserProfile> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return country + ", " + city + ", street " + street + ", house " + house
				+ ", apartment " + apartment;
	}

}
