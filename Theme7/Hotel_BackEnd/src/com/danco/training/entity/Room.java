package com.danco.training.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room  implements Cloneable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3265164475946260575L;
	private int number;
	private int content;
	private Status status;
	private int stars;
	private int price;
	private List<Guest> guestList;

	public Room(int number, int content, Status status, int stars, int price) {
		
		this.number = number;
		this.content = content;
		this.status = status;
		this.stars = stars;
		this.price = price;
		this.guestList = new ArrayList<Guest>();

		
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

	public void set�ontent(int content) {
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

	public List<Guest> getGuestList() {
		return guestList;
	}

	public void setGuestList(List<Guest> guestList) {
		this.guestList = guestList;
	}
	public Room clone() throws CloneNotSupportedException {
        return (Room)super.clone();
  }

}
