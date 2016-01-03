package com.danco.training.services;

import com.danco.training.controller.ControllerAdditionalService;
import com.danco.training.controller.ControllerDailService;
import com.danco.training.controller.ControllerGuest;
import com.danco.training.controller.ControllerRoom;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Room;
import com.danco.training.entity.Service;
import com.danco.training.entity.Status;

public class ServiceAdmin {

	private final int RESIDENTCE_IN_ROOM = 1;

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

	public void settleGuestInRoom(Guest guest, Room room, String dateInSettle,
			String dateOutSettle) {

		contGuest.createOrderForGuest(guest);

		// add guest order with service residence in Room
		Service service = contDailService.getService(RESIDENTCE_IN_ROOM);
		contGuest.addServiceForGuest(guest, service);

		// reserve a room
		contGuest.addDateInSettle(guest, dateInSettle);
		contGuest.addDateOutSettle(guest, dateOutSettle);

		// add id guest in room
		contGuest.addRoomForGuest(guest, room);
		contRoom.changeRoomStatus(room, Status.NOTFREE);
	}

	public void addServiceForGuest(Guest guest, Service service) {

		contGuest.addServiceForGuest(guest, service);
	}

	public void settleGuestOutRoom(Guest guest, Room room) {

		// 1. guest pay the order
		// 2. change room status
		contRoom.changeRoomStatus(room, Status.FREE);
	}

	public int getSumOrderGuest(Guest guest) {
		int[] services = contGuest.getGuestThemServices(guest);
		int sumOrder = contDailService.getServicesSumPriceById(services);

		return sumOrder += contAdditionalService
				.getServicesSumPriceById(services);

	}

	public void printGuestsThemRoom(Guest[] guestsList, Room[] roomsList) {
		contGuest.printGuestsThemRoom(guestsList, roomsList);

	}

	public int getAmountGuest(Guest[] guestsList) {
		return contGuest.getAmountGuests(guestsList);

	}

	public Room[] printSortetdRoomsByContent(Room[] roomsList) {
		return contRoom.printRoomSortedByContetn(roomsList);

	}

	public Room[] printSortetdRoomsByNumber(Room[] roomsList) {
		return contRoom.printRoomSortedByNumber(roomsList);

	}

	public Room[] printSortetdRoomsByPrice(Room[] roomsList) {
		return contRoom.printRoomSortedByPrice(roomsList);

	}

	public Room[] printRoomFreeSortetdByContent(Room[] roomsList) {
		roomsList = contRoom.printRoomFree(roomsList);
		return contRoom.printRoomSortedByContetn(roomsList);

	}

	public Room[] printRoomFreeSortetdByPrice(Room[] roomsList) {
		roomsList = contRoom.printRoomFree(roomsList);
		return contRoom.printRoomSortedByPrice(roomsList);

	}

	public Room[] printRoomFreeSortetdByNumber(Room[] roomsList) {
		roomsList = contRoom.printRoomFree(roomsList);
		return contRoom.printRoomSortedByNumber(roomsList);

	}

	public Room[] printRoomFreeSortetdByStars(Room[] roomsList) {
		roomsList = contRoom.printRoomFree(roomsList);
		return contRoom.printRoomSortedByStars(roomsList);

	}

	public int getAmountFreeRoom(Room[] roomsList) {
		return contRoom.printAmountRoomFree(roomsList);

	}

	public Guest[] printRoomThemGuestsAndDateInSettle(Room room,
			Guest[] guestsList) {
		return contRoom.printRoomThemGuestsAndDateInSettle(room, guestsList);

	}

	private Service[] concatenateDailServicesListAdditionalServicesList(
			Service[] dailServicesList, Service[] additionalServicesList) {

		int lengthDailService = dailServicesList.length;
		int lengthAdditionalService = additionalServicesList.length;
		int newLength = lengthDailService + lengthAdditionalService;
		Service[] services = new Service[newLength];

		System.arraycopy(dailServicesList, 0, services, 0, lengthDailService);
		System.arraycopy(additionalServicesList, 0, services,
				lengthDailService, lengthAdditionalService);

		return services;
	}

	public Service[] printServicesSortedByName(Service[] dailServicesList,
			Service[] additionalServicesList) {

		contDailService.printServicesSortedByName(dailServicesList);
		contAdditionalService.printServicesSortedByName(additionalServicesList);

		Service[] services = concatenateDailServicesListAdditionalServicesList(
				dailServicesList, additionalServicesList);
		return services;

	}

	public Service[] printServicesSortedByPrice(Service[] dailServicesList,
			Service[] additionalServicesList) {

		contDailService.printServicesSortedByPrice(dailServicesList);
		contAdditionalService
				.printServicesSortedByPrice(additionalServicesList);

		Service[] services = concatenateDailServicesListAdditionalServicesList(
				dailServicesList, additionalServicesList);
		return services;
	}

	public Service[] printGuestThemServiceList(Guest guest) {

		int[] IdServices = contGuest.getGuestThemServices(guest);
		Service[] dailServicesList = contDailService
				.printServicesThemPriceById(IdServices);
		Service[] additionalServicesList = contAdditionalService
				.printServicesThemPriceById(IdServices);
		Service[] services = concatenateDailServicesListAdditionalServicesList(
				dailServicesList, additionalServicesList);
		return services;
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
