package com.danco.model.entity;

import java.time.LocalDateTime;

public class Check extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7745966000797087829L;
	private LocalDateTime dateInSettle;
	private LocalDateTime dateOutSettle;
	private boolean status;
	
	private int idGuest;	
	private int idRoom;

	public Check(LocalDateTime dateInSettle, LocalDateTime dateOutSettle,
			boolean status, int idGuest, int idRoom) {

		this.dateInSettle = dateInSettle;
		this.dateOutSettle = dateOutSettle;
		this.status = status;
		this.idGuest = idGuest;
		this.idRoom = idRoom;

	}

	public LocalDateTime getDateInSettle() {
		return dateInSettle;
	}

	public void setDateInSettle(LocalDateTime dateInSettle) {
		this.dateInSettle = dateInSettle;
	}

	public LocalDateTime getDateOutSettle() {
		return dateOutSettle;
	}

	public void setDateOutSettle(LocalDateTime dateOutSettle) {
		this.dateOutSettle = dateOutSettle;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getIdGuest() {
		return idGuest;
	}

	public void setIdGuest(int idGuest) {
		this.idGuest = idGuest;
	}

	public int getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
