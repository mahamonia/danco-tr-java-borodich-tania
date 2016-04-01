package com.danco.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Chek", uniqueConstraints = {
		@UniqueConstraint(columnNames = "dateInSettle"),
		@UniqueConstraint(columnNames = "dateOutSettle"), 
		@UniqueConstraint(columnNames = "status") })
public class Chek extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7745966000797087829L;
	@Column(name = "dateInSettle", unique = true)
	private Date dateInSettle;
	
	@Column(name = "dateOutSettle", unique = true)
	private Date dateOutSettle;
	
	@Column(name = "status", unique = true)
	private boolean status;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Guest_idGuest", nullable = false)
	private Guest guests;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Room_idRoom", nullable = false)
	private Room room;
	
	@OneToMany(targetEntity = com.danco.model.entity.Service.class, mappedBy = "cheks",fetch = FetchType.LAZY )
	private List<Service> serviceList;

	public Chek(){
		
	}
	
	public Chek(Date dateInSettle, Date dateOutSettle,
			Guest guest, Room room) {

		this.dateInSettle = dateInSettle;
		this.dateOutSettle = dateOutSettle;
		this.guests = guest;
		this.room = room;
		
		this.serviceList = new ArrayList<Service>();

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
		return guests;
	}

	public void setGuest(Guest guest) {
		this.guests = guest;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<Service> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<Service> serviList) {
		this.serviceList = serviList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
