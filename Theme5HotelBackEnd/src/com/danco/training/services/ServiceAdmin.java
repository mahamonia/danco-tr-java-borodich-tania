package com.danco.training.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.danco.training.controller.ControllerAdditionalService;
import com.danco.training.controller.ControllerDailService;
import com.danco.training.controller.ControllerGuest;
import com.danco.training.controller.ControllerRoom;
import com.danco.training.entity.AdditionalService;
import com.danco.training.entity.DailService;
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
			String dateOutSettle) throws IOException {

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

		throw new RuntimeException();

	}

	public void addServiceForGuest(Guest guest, Service service)
			throws IOException {

		contGuest.addServiceForGuest(guest, service);
		throw new RuntimeException();
	}

	public void settleGuestOutRoom(Guest guest, Room room) throws IOException {

		// 1. guest pay the order
		// 2. change room status
		contRoom.changeRoomStatus(room, Status.FREE);
		throw new RuntimeException();
	}

	public int getSumOrderGuest(Guest guest) throws IOException {
		int[] services = contGuest.getGuestThemServices(guest);
		int sumOrder = contDailService.getServicesSumPriceById(services);
		if (sumOrder == 0) {
			throw new RuntimeException();
		}

		return sumOrder += contAdditionalService
				.getServicesSumPriceById(services);

	}

	public void printGuestsThemRoom(List<Guest> guestsList, List<Room> roomsList)
			throws IOException {
		contGuest.printGuestsThemRoom(guestsList, roomsList);
		// throw new RuntimeException();
	}

	public int getAmountGuest(List<Guest> guestsList) throws IOException {
		if (contGuest.getAmountGuests(guestsList) == 0) {
			throw new RuntimeException();
		}
		return contGuest.getAmountGuests(guestsList);

	}

	public List<Room> printSortetdRoomsByContent(List<Room> roomsList)
			throws IOException {
		if (contRoom.printRoomSortedByContetn(roomsList) == null) {
			throw new RuntimeException();
		}
		return contRoom.printRoomSortedByContetn(roomsList);

	}

	public List<Room> printSortetdRoomsByNumber(List<Room> roomsList)
			throws IOException {
		if (contRoom.printRoomSortedByNumber(roomsList) == null) {
			throw new RuntimeException();
		}
		return contRoom.printRoomSortedByNumber(roomsList);

	}

	public List<Room> printSortetdRoomsByPrice(List<Room> roomsList)
			throws IOException {
		if (contRoom.printRoomSortedByPrice(roomsList) == null) {
			throw new RuntimeException();
		}
		return contRoom.printRoomSortedByPrice(roomsList);

	}

	public List<Room> printRoomFreeSortetdByContent(List<Room> roomsList)
			throws IOException {
		roomsList = contRoom.printRoomFree(roomsList);
		if (roomsList == null) {
			throw new RuntimeException();
		}
		return contRoom.printRoomSortedByContetn(roomsList);

	}

	public List<Room> printRoomFreeSortetdByPrice(List<Room> roomsList)
			throws IOException {
		roomsList = contRoom.printRoomFree(roomsList);
		if (roomsList == null) {
			throw new RuntimeException();
		}
		return contRoom.printRoomSortedByPrice(roomsList);

	}

	public List<Room> printRoomFreeSortetdByNumber(List<Room> roomsList)
			throws IOException {
		roomsList = contRoom.printRoomFree(roomsList);
		if (roomsList == null) {
			throw new RuntimeException();
		}
		return contRoom.printRoomSortedByNumber(roomsList);

	}

	public List<Room> printRoomFreeSortetdByStars(List<Room> roomsList)
			throws IOException {
		roomsList = contRoom.printRoomFree(roomsList);
		if (roomsList == null) {
			throw new RuntimeException();
		}
		return contRoom.printRoomSortedByStars(roomsList);

	}

	public int getAmountFreeRoom(List<Room> roomsList) throws IOException {
		if (contRoom.printAmountRoomFree(roomsList) == 0) {
			throw new RuntimeException();
		}
		return contRoom.printAmountRoomFree(roomsList);

	}

	public List<Guest> printRoomThemGuestsAndDateInSettle(Room room,
			List<Guest> guestsList) throws IOException {
		if (contRoom.printRoomThemGuestsAndDateInSettle(room, guestsList) == null) {
			throw new RuntimeException();
		}
		return contRoom.printRoomThemGuestsAndDateInSettle(room, guestsList);

	}

	private List<Service> concatenateServicesList(
			List<Service> dailServicesList, List<Service> additionalServicesList) {

		List<Service> services = new ArrayList<Service>();

		services.addAll(dailServicesList);
		services.addAll(additionalServicesList);

		return services;

	}

	public List<Service> printServicesSortedByName(
			List<Service> dailServicesList, List<Service> additionalServicesList)
			throws IOException {

		contDailService.printServicesSortedByName(dailServicesList);
		contAdditionalService.printServicesSortedByName(additionalServicesList);

		if (concatenateServicesList(dailServicesList, additionalServicesList) == null) {
			throw new RuntimeException();
		}

		return concatenateServicesList(dailServicesList, additionalServicesList);

	}

	public List<Service> printServicesSortedByPrice(
			List<Service> dailServicesList, List<Service> additionalServicesList)
			throws IOException {

		contDailService.printServicesSortedByPrice(dailServicesList);
		contAdditionalService
				.printServicesSortedByPrice(additionalServicesList);

		if (concatenateServicesList(dailServicesList, additionalServicesList) == null) {
			throw new RuntimeException();
		}
		return concatenateServicesList(dailServicesList, additionalServicesList);
	}

	// public List<Service> printGuestThemServiceList(Guest guest) {
	//
	// int[] IdServices = contGuest.getGuestThemServices(guest);
	// List<DailService> dailServicesList = contDailService
	// .printServicesThemPriceById(IdServices);
	// List<AdditionalService> additionalServicesList = contAdditionalService
	// .printServicesThemPriceById(IdServices);
	// return concatenateServicesList(dailServicesList, additionalServicesList);
	// }

	public void changeRoomStatus(Room room, Status status) throws IOException {
		contRoom.changeRoomStatus(room, status);
		throw new RuntimeException();
	}

	public void changeRoomPrice(Room room, int price) throws IOException {
		contRoom.changeRoomPrice(room, price);
		throw new RuntimeException();
	}

	public void changeServicePrice(Service service, int price)
			throws IOException {

		if (service instanceof DailService) {
			contDailService.changePrice(service, price);
		} else if (service instanceof AdditionalService) {
			contAdditionalService.changePrice(service, price);
		}
		throw new RuntimeException();
	}

}
