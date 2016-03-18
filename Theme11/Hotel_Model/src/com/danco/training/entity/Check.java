package com.danco.training.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Check extends BaseModel{

	private LocalDateTime dateInSettle;
	private LocalDateTime dateOutSettle;
	private Guest guest;
	private List<DailService> serviceList;

	public Check( LocalDateTime dateInSettle, LocalDateTime dateOutSettle, Guest guest) {

		this.dateInSettle = dateInSettle;
		this.dateOutSettle = dateOutSettle;
		this.guest = guest;
		this.serviceList = new ArrayList<DailService>();
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

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public List<DailService> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<DailService> serviceList) {
		this.serviceList = serviceList;
	}

}
