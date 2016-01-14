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

	private static ServiceAdmin admin;

	private final int RESIDENTCE_IN_ROOM = 1;

	private ControllerRoom contRoom;
	private ControllerGuest contGuest;
	private ControllerDailService contDailService;
	private ControllerAdditionalService contAdditionalService;

	private ServiceAdmin(ControllerGuest contGuest,
			ControllerRoom contRoom, ControllerDailService contDailService,
			ControllerAdditionalService contAdditionalService) {
		this.contGuest= contGuest;
		this.contRoom = contRoom;
		this.contDailService = contDailService;
		this.contAdditionalService = contAdditionalService;

	}

	public static ServiceAdmin getInstance(ControllerGuest contGuest,
			ControllerRoom contRoom, ControllerDailService contDailService,
			ControllerAdditionalService contAdditionalService) {
		

		if (admin == null) {
			admin = new ServiceAdmin(contGuest,
					contRoom, contDailService,
					contAdditionalService);
		}
		return admin;
	}

	public void createGuest(Guest guest) {
		contGuest.createGuest(guest);
	}

	public Guest getGuestById(int id) {
		return contGuest.getGuestById(id);

	}

	public int getIdForNewGuest() {
		int newId = contGuest.getIdForNewGuest();
		return newId;

	}

	public int getIdForNewOrder() {
		int newId = contGuest.getIdForNewOrder();
		return newId;

	}

	public List<Guest> getListGuest() {

		List<Guest> guestsList = contGuest.getListGuest();
		return guestsList;

	}

	public void updateGuest(Guest guest) {
		contGuest.updateGuest(guest);

	}

	public Room getRoomByNumber(int number) {
		return contRoom.getRoomByNumber(number);

	}

	public List<Room> getListRoom() {

		List<Room> roomsList = contRoom.getListRoom();
		return roomsList;

	}

	public void updateRoom(Room room) {
		contRoom.updateRoom(room);

	}

	public Service getServiceById(int id) {

		if (contDailService.getService(id) != null) {
			return contDailService.getService(id);
		} else if (contAdditionalService.getService(id) != null) {
			return contAdditionalService.getService(id);
		} else
			return null;

	}

	public List<Service> getListDailService() {

		List<Service> servicesList = contDailService.getListDailService();
		return servicesList;
	}

	public List<AdditionalService> getListAdditionalService() {

		List<AdditionalService> servicesList = contAdditionalService.getListAddService();
		return servicesList;
	}

	public void updateDailService(Service service) {

		contDailService.updateService(service);
	}

	public void updateAdditionalService(AdditionalService service) {

		contAdditionalService.updateService(service);
	}

	public void updateService(Service service) {

		if (service instanceof DailService) {
			updateDailService(service);
		} else
			updateAdditionalService( (AdditionalService)service);

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

	public void settleGuestOutRoom(Guest guest) throws IOException {

		// 1. guest pay the order
		// 2. change room status
		List<Room> roomsList = contRoom.getListRoom();
		Room room = contGuest.getRoomInLiveGuest(guest, roomsList);
		contRoom.changeRoomStatus(room, Status.FREE);
		throw new RuntimeException();
	}

	public Room getRoomInLiveGuest(Guest guest) {
		List<Room> roomsList = contRoom.getListRoom();
		Room room = contGuest.getRoomInLiveGuest(guest, roomsList);
		return room;

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

	public void printGuestsThemRoom() throws IOException {
		List<Guest> guestsList = contGuest.getListGuest();
		List<Room> roomsList = contRoom.getListRoom();
		contGuest.printGuestsThemRoom(guestsList, roomsList);
		// throw new RuntimeException();
	}

	public int getAmountGuest() throws IOException {
		List<Guest> guestsList = contGuest.getListGuest();
		if (contGuest.getAmountGuests(guestsList) == 0) {
			throw new RuntimeException();
		}
		return contGuest.getAmountGuests(guestsList);

	}

	public List<Room> printSortedRoomsByContent() throws IOException {
		List<Room> roomsList = contRoom.getListRoom();
		if (contRoom.printRoomSortedByContetn(roomsList) == null) {
			throw new RuntimeException();
		}
		return contRoom.printRoomSortedByContetn(roomsList);

	}

	public List<Room> printSortedRoomsByNumber() throws IOException {
		List<Room> roomsList = contRoom.getListRoom();
		if (contRoom.printRoomSortedByNumber(roomsList) == null) {
			throw new RuntimeException();
		}
		return contRoom.printRoomSortedByNumber(roomsList);

	}

	public List<Room> printSortedRoomsByPrice() throws IOException {
		List<Room> roomsList = contRoom.getListRoom();
		if (contRoom.printRoomSortedByPrice(roomsList) == null) {
			throw new RuntimeException();
		}
		return contRoom.printRoomSortedByPrice(roomsList);

	}

	public List<Room> printSortedRoomByStars() throws IOException {
		List<Room> roomsList = contRoom.getListRoom();
		if (contRoom.printRoomSortedByStars(roomsList) == null) {
			throw new RuntimeException();
		}
		return contRoom.printRoomSortedByStars(roomsList);

	}

	public List<Room> printRoomFreeSortetdByContent() throws IOException {
		List<Room> roomsList = contRoom.getListRoom();
		roomsList = contRoom.printRoomFree(roomsList);
		if (roomsList == null) {
			throw new RuntimeException();
		}
		return contRoom.printRoomSortedByContetn(roomsList);

	}

	public List<Room> printRoomFreeSortetdByPrice() throws IOException {
		List<Room> roomsList = contRoom.getListRoom();
		roomsList = contRoom.printRoomFree(roomsList);
		if (roomsList == null) {
			throw new RuntimeException();
		}
		return contRoom.printRoomSortedByPrice(roomsList);

	}

	public List<Room> printRoomFreeSortetdByNumber() throws IOException {
		List<Room> roomsList = contRoom.getListRoom();
		roomsList = contRoom.printRoomFree(roomsList);
		if (roomsList == null) {
			throw new RuntimeException();
		}
		return contRoom.printRoomSortedByNumber(roomsList);

	}

	public List<Room> printRoomFreeSortetdByStars() throws IOException {
		List<Room> roomsList = contRoom.getListRoom();
		roomsList = contRoom.printRoomFree(roomsList);
		if (roomsList == null) {
			throw new RuntimeException();
		}
		return contRoom.printRoomSortedByStars(roomsList);

	}

	public int getAmountFreeRoom() throws IOException {
		List<Room> roomsList = contRoom.getListRoom();
		if (contRoom.printAmountRoomFree(roomsList) == 0) {
			throw new RuntimeException();
		}
		return contRoom.printAmountRoomFree(roomsList);

	}

	public List<Guest> printRoomThemGuestsAndDateInSettle(Room room)
			throws IOException {
		List<Guest> guestsList = contGuest.getListGuest();
		if (contRoom.printRoomThemGuestsAndDateInSettle(room, guestsList) == null) {
			throw new RuntimeException();
		}
		return contRoom.printRoomThemGuestsAndDateInSettle(room, guestsList);

	}


	public List<Service> printDailServicesSortedByName() throws IOException {
		List<Service> dailServicesList = contDailService.getListDailService();
		contDailService.printServicesSortedByName(dailServicesList);

		if (dailServicesList == null) {
			throw new RuntimeException();
		}
		return dailServicesList;
	}

	public List<AdditionalService> printAdditionalServicesSortedByName()
			throws IOException {
		List<AdditionalService> additionalServicesList =contAdditionalService
				.getListAddService();
		additionalServicesList = contAdditionalService.printServicesSortedByName(additionalServicesList);

		if (additionalServicesList == null) {
			throw new RuntimeException();
		}
		return additionalServicesList;
	}

	public List<Service> printDailServicesSortedByPrice() throws IOException {
		List<Service> dailServicesList = contDailService.getListDailService();
		contDailService.printServicesSortedByPrice(dailServicesList);

		if (dailServicesList == null) {
			throw new RuntimeException();
		}
		return dailServicesList;
	}

	public List<AdditionalService> printAdditionalServicesSortedByPrice()
			throws IOException {
		List<AdditionalService> additionalServicesList = contAdditionalService
				.getListAddService();
		
		contAdditionalService
				.printServicesSortedByPrice(additionalServicesList);

		if (additionalServicesList == null) {
			throw new RuntimeException();
		}
		return additionalServicesList;
	}

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
