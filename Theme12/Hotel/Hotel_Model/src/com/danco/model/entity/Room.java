package com.danco.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Room")
public class Room extends BaseModel implements Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6490565046509298682L;
	
	@Id
	@GeneratedValue
	@Column(name = "idRoom")
	private int id;

	@Column(name = "number")
	private int number;

	@Column(name = "content")
	private int content;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition="enum('FREE','NOTFREE','ONREPAIR')")
	private Status status;

	@Column(name = "stars")
	private int stars;

	@Column(name = "price")
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

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
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
