package com.danco.model.entity;

import java.util.Date;

public class Chek extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7745966000797087829L;
	private Date dateInSettle;
	private Date dateOutSettle;
	private boolean status;
	
	private Guest guest;	
	private Room room;
	
//	private List<Service> serviceList;

	public Chek(Date dateInSettle, Date dateOutSettle,
			boolean status, Guest guest, Room room) {

		this.dateInSettle = dateInSettle;
		this.dateOutSettle = dateOutSettle;
		this.status = status;
		this.guest = guest;
		this.room = room;
		
	//	this.serviceList = new ArrayList<Service>();

	}

	public Date getDateInSettle() {
		return dateInSettle;
	}

	public void setDateInSettle(Date dateInSettle) {
		this.dateInSettle = dateInSettle;
	}

	public Date getDateOutSettle() {
		return dateOutSettle;
	}

	public void setDateOutSettle(Date dateOutSettle) {
		this.dateOutSettle = dateOutSettle;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

//	public List<Service> getServiceList() {
//		return serviceList;
//	}
//
//	public void setServiceList(List<Service> serviceList) {
//		this.serviceList = serviceList;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
