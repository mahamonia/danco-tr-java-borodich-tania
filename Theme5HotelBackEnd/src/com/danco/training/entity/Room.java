package com.danco.training.entity;

public class Room  {
	private int number;
	private int content;
	private Status status;
	private int stars;
	private int price;
	private int [] IdGuest;

	public Room(int number, int content, Status status, int stars, int price, int [] IdGuest
			) {
		this.number = number;
		this.content = content;
		this.status = status;
		this.stars = stars;
		this.price =price;
		
		this.setIdGuest(IdGuest);

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

	public void setÑontent(int content) {
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

	public int [] getIdGuest() {
		return IdGuest;
	}

	public void setIdGuest(int [] idGuest) {
		IdGuest = idGuest;
	}

}
