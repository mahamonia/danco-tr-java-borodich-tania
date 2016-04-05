package com.danco.model.entity;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Chek")
public class Chek extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7745966000797087829L;
	
	@Id
	@GeneratedValue
	@Column(name = "idChek")
	private int id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dateInSettle")
	private Date dateInSettle;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dateOutSettle")
	private Date dateOutSettle;
	
	@Column(name = "status")
	private boolean status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Guest_idGuest", nullable = false)
	private Guest guest;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Room_idRoom", nullable = false)
	private Room room;
	
	@OneToMany(mappedBy = "chek",fetch = FetchType.EAGER)
	private List<Service> serviceList;

	public Chek(){
		
	}
	
	public Chek(Date dateInSettle, Date dateOutSettle,
			Guest guest, Room room) {

		this.dateInSettle = dateInSettle;
		this.dateOutSettle = dateOutSettle;
		this.guest = guest;
		this.room = room;
		
		this.serviceList = new ArrayList<Service>();

	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
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
