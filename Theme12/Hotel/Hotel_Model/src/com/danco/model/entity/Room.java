package com.danco.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Room",uniqueConstraints = {
		@UniqueConstraint(columnNames = "number"),
		@UniqueConstraint(columnNames = "content"),
		@UniqueConstraint(columnNames = "stars"),
		@UniqueConstraint(columnNames = "price") })
public class Room extends BaseModel implements Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6490565046509298682L;
	@Column(name = "number",unique = true)
	private int number;
	
	@Column(name = "content", unique = true)
	private int content;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition="enum('free','notfree','onrepair')")
	private Status status;
	
	@Column(name = "stars", unique = true)
	private int stars;
	
	@Column(name = "price", unique = true)
	private int price;

	public Room(){
		
	}
	
	public Room(int number, int content, Status status, int stars, int price) {

		this.number = number;
		this.content = content;
		this.status = status;
		this.stars = stars;
		this.price = price;

	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getContent() {
		return content;
	}

	public void setContent(int content) {
		this.content = content;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Room clone() throws CloneNotSupportedException {
		return (Room) super.clone();
	}

}
