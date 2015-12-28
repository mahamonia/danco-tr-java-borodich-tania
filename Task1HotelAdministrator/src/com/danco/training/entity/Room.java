package com.danco.training.entity;

public class Room  {
	private int Number;
	private int Level;
	private Status Status;
	private int Stars;
	private int Price;
	private String DateInSettle;
	private String DateOutSettle;
	private int IdGuest;

	public Room(int Number, int Level, Status Status, int Stars, int Price,
			String DateInSettle, String DateOutSettle, int IdGuest) {
		this.Number = Number;
		this.Level = Level;
		this.Status = Status;
		this.Stars = Stars;
		this.Price =Price;
		this.DateInSettle = DateInSettle;
		this.DateOutSettle = DateOutSettle;
		this.IdGuest = IdGuest;

	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(short number) {
		Number = number;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(byte level) {
		Level = level;
	}

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}

	public int getStars() {
		return Stars;
	}

	public void setStars(byte stars) {
		Stars = stars;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public String getDateInSettle() {
		return DateInSettle;
	}

	public void setDateInSettle(String dateInSettle) {
		DateInSettle = dateInSettle;
	}

	public String getDateOutSettle() {
		return DateOutSettle;
	}

	public void setDateOutSettle(String dateOutSettle) {
		DateOutSettle = dateOutSettle;
	}

	public int getIdGuest() {
		return IdGuest;
	}

	public void setIdGuest(int idGuest) {
		IdGuest = idGuest;
	}
	

}
