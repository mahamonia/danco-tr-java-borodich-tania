package com.danco.training.services;

import com.danco.training.comparators.*;
import com.danco.training.controller.ControllerAdditionalService;
import com.danco.training.controller.ControllerDailService;
import com.danco.training.controller.ControllerGuest;
import com.danco.training.controller.ControllerRoom;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Room;
import com.danco.training.entity.Service;
import com.danco.training.entity.Status;

public class ServiceAdmin {

	private ControllerRoom contRoom;
	private ControllerGuest contGuest;
	private ControllerDailService contDailService;
	private ControllerAdditionalService contAdditionalService;

	public ServiceAdmin(ControllerGuest contGuest, ControllerRoom contRoom,
			ControllerDailService contDailService,
			ControllerAdditionalService contAdditionalService) {
		this.contRoom = contRoom;
		this.contGuest = contGuest;
		this.contDailService = contDailService;
		this.contAdditionalService = contAdditionalService;

	}

	public void inSettleGuestRoom(Guest guest, Room room, String dateInSettle, String dateOutSettle) {

		contGuest.createOrderForGuest(guest);

		// add guest order with service residence in Room
		Service service = contDailService.getService(1);
		contGuest.addServiceForGuest(guest, service);
		
		//reserve a room
		contGuest.addDateInSettle(guest, dateInSettle);
		contGuest.addDateOutSettle(guest, dateOutSettle);
		
// add id guest in room
		contGuest.addRoomForGuest(guest, room);

		contRoom.changeRoomStatus(room, Status.NOTFREE);
		System.out.println("settle guest in room");
	}

	public void addServiceForGuest(Guest guest, Service service) {

		contGuest.addServiceForGuest(guest, service);

	}

	public void outSettleGuestRoom(Guest guest, Room room) {

		// 1. guest pay the order

		contRoom.changeRoomStatus(room, Status.FREE);
		System.out.println("settle guest out room");
	}

	public int getSumOrderGuest(Guest guest) {
		int[] services = contGuest.getGuestThemService(guest);
		int sumOrder = contDailService.getServicesSumPriceById(services);
		return sumOrder += contAdditionalService
				.getServicesSumPriceById(services);

	}

	public void printGuestsThemRoom(Guest[] guestsList, Room[] roomsList,
			TypeSort type) {
		contGuest.printGuestsThemRoom(guestsList, roomsList, type);
	}

	public void printRooms(Room[] roomsList, TypeSort type) {
		System.out.println("List rooms: ");
		contRoom.printRoom(roomsList, type);

	}

	public void printRoomFree(Room[] roomsList, TypeSort type) {
		System.out.println("free room");
		contRoom.printRoomFree(roomsList, type);
	}

	public void printServices(Service[] dailServicesList,
			Service[] additionalServicesList, TypeSort type) {

		contDailService.printServices(dailServicesList, type);
		contAdditionalService.printServices(additionalServicesList, type);
	}

	public void printGuestThemServiceList(Guest guest) {

		int[] IdServices = contGuest.getGuestThemService(guest);
		contDailService.printServicesThemPriceById(IdServices);
		contAdditionalService.printServicesThemPriceById(IdServices);

	}

	public void changeRoomStatus(Room room, Status status) {
		contRoom.changeRoomStatus(room, status);
		System.out.println("change Room Status");
	}

	public void changeRoomPrice(Room room, int price) {
		System.out.println("change Room Price");

	}

	public void changeServicePrice(Service service, int price) {
		System.out.println("change Service Price");
	}

}
