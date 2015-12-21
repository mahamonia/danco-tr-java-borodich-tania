package com.danco.training.run;

import com.danco.training.controller.ControllerGuest;
import com.danco.training.controller.ControllerRoom;
import com.danco.training.controller.ControllerService;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Hotel;
import com.danco.training.entity.Order;
import com.danco.training.entity.Room;
import com.danco.training.entity.Service;
import com.danco.training.services.ServiceAdmin;

public class Run {

	public static void main(String[] args) {

		Hotel hotel = new Hotel();
		Guest [] guests = hotel.getGuests();
		Room [] rooms = hotel.getRooms();
		//Order [] orders = hotel.getOrders();
		Service [] services = hotel.getServices();

		ControllerGuest contGuest = new ControllerGuest(guests);
		ControllerRoom contRoom = new ControllerRoom(rooms);
		ControllerService contService = new ControllerService(services);

		ServiceAdmin admin = new ServiceAdmin(contRoom, contGuest, contService);

		admin.inSettleGuestRoom(guests[0], rooms[0]);
		admin.printGuestThemRoom(guests, rooms);
		admin.printFreeRoom(rooms);
		
		hotel.setGuests(guests);
		hotel.setRooms(rooms);
		//hotel.setOrders(orders);

	}

}
