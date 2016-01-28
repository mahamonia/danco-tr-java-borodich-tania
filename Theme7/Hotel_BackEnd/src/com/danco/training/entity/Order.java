package com.danco.training.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.danco.training.entity.AdditionalService;

public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3299762870887211471L;

	private int Id;
	private int sumPrice;
	private Guest guest;
	private List<Service> serviceList;

	public Order(int Id, int sumPrice, Guest guest) {

		this.Id = Id;
		this.sumPrice = sumPrice;
		this.guest = guest;
		this.serviceList = new ArrayList<Service>();
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getSumPrice() {
		sumPrice = 0;

		for (int i = 0; i < serviceList.size(); i++) {
			sumPrice = +serviceList.get(i).getPrice();
			if (serviceList.get(i).getId() >=5) { // begin additional services 
				AdditionalService service = (AdditionalService) serviceList
						.get(i);
				sumPrice = +service.getAddPrice();
			}
		}
		return sumPrice;
	}

	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public List<Service> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}

}
