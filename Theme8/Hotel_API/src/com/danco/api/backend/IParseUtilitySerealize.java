package com.danco.api.backend;

import java.util.List;

import com.danco.training.entity.AdditionalService;
import com.danco.training.entity.DailService;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Order;
import com.danco.training.entity.Room;

public interface IParseUtilitySerealize {
	public void setSerializeData(List<Guest> guestList, List<Room> roomList,
			List<Order> orderList, List<DailService> dailList,
			List<AdditionalService> addServiceList);

	public List<Guest> getGuestsList();

	public List<Room> getRoomsList();

	public List<Order> getOrdersList();

	public List<DailService> getDailServiceList();

	public List<AdditionalService> getAdditionalServiceList();

}
