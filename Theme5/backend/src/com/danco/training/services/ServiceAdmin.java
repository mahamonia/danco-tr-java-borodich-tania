package com.danco.training.services;

import java.util.List;

import com.danco.config.PropertyProgramm;
import com.danco.training.controller.ControllerAdditionalService;
import com.danco.training.controller.ControllerDailService;
import com.danco.training.controller.ControllerGuest;
import com.danco.training.controller.ControllerRoom;
import com.danco.training.entity.AdditionalService;
import com.danco.training.entity.DailService;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Order;
import com.danco.training.entity.Room;
import com.danco.training.entity.Service;
import com.danco.training.entity.Status;
import com.danco.training.utility.ParseUtilitySerealize;

public class ServiceAdmin {

	private static ServiceAdmin admin;

	private List<Guest> guestsList;
	private List<Room> roomsList;
	private List<Order> ordersList;
	private List<Service> dailServicesList;
	private List<AdditionalService> additionalServicesList;

	private ControllerRoom contRoom;
	private ControllerGuest contGuest;
	private ControllerDailService contDailService;
	private ControllerAdditionalService contAdditionalService;

	private ParseUtilitySerealize utility = ParseUtilitySerealize.getInstance();
	private PropertyProgramm config = PropertyProgramm.getInstance();
	
	private final int RESIDENTCE_IN_ROOM = 1;
	private final String MESSAGE_1 = "Prohibited privacy settings";

	private ServiceAdmin() {

	}

	public static ServiceAdmin getInstance() {

		if (admin == null) {
			admin = new ServiceAdmin();
		}
		return admin;
	}

	public void initData() {

		guestsList = utility.getGuestsList();
		roomsList = utility.getRoomsList();
		ordersList = utility.getOrdersList();
		dailServicesList = utility.getDailServiceList();
		additionalServicesList = utility.getAdditionalServiceList();

		ControllerGuest contGuest = new ControllerGuest(guestsList, ordersList);
		ControllerRoom contRoom = new ControllerRoom(roomsList);
		ControllerDailService contDailService = new ControllerDailService(
				dailServicesList);
		ControllerAdditionalService contAdditionalService = new ControllerAdditionalService(
				additionalServicesList);

		this.contGuest = contGuest;
		this.contRoom = contRoom;
		this.contDailService = contDailService;
		this.contAdditionalService = contAdditionalService;

	}

	public void saveData() {

		utility.setSerializeData(guestsList, roomsList, ordersList,
				dailServicesList, additionalServicesList);

	}

	public void createGuest(Guest guest) throws Exception {
		contGuest.createGuest(guest);
	}

	public Guest getGuestById(int id) throws Exception {
		return contGuest.getGuestById(id);

	}

	public int getIdForNewGuest() throws Exception {
		int newId = contGuest.getIdForNewGuest();
		return newId;

	}

	public int getIdForNewOrder() throws Exception {
		int newId = contGuest.getIdForNewOrder();
		return newId;

	}

	public List<Guest> getListGuest() throws Exception {

		List<Guest> guestsList = contGuest.getListGuest();
		return guestsList;

	}

	public void updateGuest(Guest guest) throws Exception {
		contGuest.updateGuest(guest);
		throw new Exception();

	}

	public void settleGuestInRoom(Guest guest, Room room, String dateInSettle,
			String dateOutSettle) throws Exception {

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

		throw new Exception();

	}

	public void addServiceForGuest(Guest guest, Service service)
			throws Exception {

		contGuest.addServiceForGuest(guest, service);
		throw new Exception();
	}

	public void settleGuestOutRoom(Guest guest) throws Exception {

		// 1. guest pay the order
		// 2. change room status
		List<Room> roomsList = contRoom.getListRoom();
		Room room = contGuest.getRoomInLiveGuest(guest, roomsList);
		contRoom.changeRoomStatus(room, Status.FREE);
		throw new Exception();
	}

	public Room getRoomInLiveGuest(Guest guest) throws Exception {
		List<Room> roomsList = contRoom.getListRoom();
		Room room = contGuest.getRoomInLiveGuest(guest, roomsList);

		return room;

	}

	public int getSumOrderGuest(Guest guest) throws Exception {
		int[] services = contGuest.getGuestThemServices(guest);
		int sumOrder = contDailService.getServicesSumPriceById(services);
		if (sumOrder == 0) {
			throw new Exception();
		}

		return sumOrder += contAdditionalService
				.getServicesSumPriceById(services);

	}

	public void printGuestsThemRoom() throws Exception {
		List<Guest> guestsList = contGuest.getListGuest();
		List<Room> roomsList = contRoom.getListRoom();
		contGuest.printGuestsThemRoom(guestsList, roomsList);
		throw new Exception();
	}
	
	public List<Guest> printGuestList() {
		List<Guest> guestsList = contGuest.getListGuest();	
		return guestsList;
		
	}

	public int getAmountGuest() throws Exception {
		List<Guest> guestsList = contGuest.getListGuest();
		if (contGuest.getAmountGuests(guestsList) == 0) {
			throw new Exception();
		}
		return contGuest.getAmountGuests(guestsList);

	}

	// ======= room ======

	public void createRoom(Room room) throws Exception {
		contRoom.createRoom(room);
		throw new Exception();
	}

	public Room getRoomByNumber(int number) throws Exception {
		return contRoom.getRoomByNumber(number);

	}

	public List<Room> getListRoom() throws Exception {

		List<Room> roomsList = contRoom.getListRoom();
		return roomsList;

	}

	public void updateRoom(Room room) throws Exception {

		contRoom.updateRoom(room);
		throw new Exception();

	}

	public List<Room> printSortedRoomsByContent() throws Exception {
		List<Room> roomsList = contRoom.getListRoom();
		if (contRoom.printRoomSortedByContetn(roomsList) == null) {
			throw new Exception();
		}
		return contRoom.printRoomSortedByContetn(roomsList);

	}

	public List<Room> printSortedRoomsByNumber() throws Exception {
		List<Room> roomsList = contRoom.getListRoom();
		if (contRoom.printRoomSortedByNumber(roomsList) == null) {
			throw new Exception();
		}
		return contRoom.printRoomSortedByNumber(roomsList);

	}

	public List<Room> printSortedRoomsByPrice() throws Exception {
		List<Room> roomsList = contRoom.getListRoom();
		if (contRoom.printRoomSortedByPrice(roomsList) == null) {
			throw new Exception();
		}
		return contRoom.printRoomSortedByPrice(roomsList);

	}

	public List<Room> printSortedRoomByStars() throws Exception {
		List<Room> roomsList = contRoom.getListRoom();
		if (contRoom.printRoomSortedByStars(roomsList) == null) {
			throw new Exception();
		}
		return contRoom.printRoomSortedByStars(roomsList);

	}

	public List<Room> printRoomFreeSortetdByContent() throws Exception {
		List<Room> roomsList = contRoom.getListRoom();
		roomsList = contRoom.printRoomFree(roomsList);
		if (roomsList == null) {
			throw new Exception();
		}
		return contRoom.printRoomSortedByContetn(roomsList);

	}

	public List<Room> printRoomFreeSortetdByPrice() throws Exception {
		List<Room> roomsList = contRoom.getListRoom();
		roomsList = contRoom.printRoomFree(roomsList);
		if (roomsList == null) {
			throw new Exception();
		}
		return contRoom.printRoomSortedByPrice(roomsList);

	}

	public List<Room> printRoomFreeSortetdByNumber() throws Exception {
		List<Room> roomsList = contRoom.getListRoom();
		roomsList = contRoom.printRoomFree(roomsList);
		if (roomsList == null) {
			throw new Exception();
		}
		return contRoom.printRoomSortedByNumber(roomsList);

	}

	public List<Room> printRoomFreeSortetdByStars() throws Exception {
		List<Room> roomsList = contRoom.getListRoom();
		roomsList = contRoom.printRoomFree(roomsList);
		if (roomsList == null) {
			throw new Exception();
		}
		return contRoom.printRoomSortedByStars(roomsList);

	}

	public int getAmountFreeRoom() throws Exception {
		List<Room> roomsList = contRoom.getListRoom();
		if (contRoom.printAmountRoomFree(roomsList) == 0) {
			throw new Exception();
		}
		return contRoom.printAmountRoomFree(roomsList);

	}

	public List<Guest> printRoomThemGuestsAndDateInSettle(Room room)
			throws Exception {
		List<Guest> guestsList = contGuest.getListGuest();
		if (contRoom.printRoomThemGuestsAndDateInSettle(room, guestsList) == null) {
			throw new Exception();
		}
		return contRoom.printRoomThemGuestsAndDateInSettle(room, guestsList);

	}

	public void changeRoomStatus(Room room, Status status) throws Exception {

		// Room room = contRoom.getRoomByNumber(number);
		if (config.getConfigStatusRoom("status") == true) {
			contRoom.changeRoomStatus(room, status);
		} else
			System.out.println(MESSAGE_1);

		throw new Exception();
	}

	public void changeRoomPrice(Room room, int price) throws Exception {
		contRoom.changeRoomPrice(room, price);
		throw new Exception();
	}

	public void cloneRoom(Room room) throws Exception {
		contRoom.cloneRoom(room);
		throw new Exception();

	}

	// ====== service =====

	public Service getServiceById(int id) throws Exception {

		if (contDailService.getService(id) != null) {
			return contDailService.getService(id);
		} else if (contAdditionalService.getService(id) != null) {
			return contAdditionalService.getService(id);
		} else
			return null;

	}

	public List<Service> getListDailService() throws Exception {

		List<Service> servicesList = contDailService.getListDailService();
		return servicesList;
	}

	public List<AdditionalService> getListAdditionalService() throws Exception {

		List<AdditionalService> servicesList = contAdditionalService
				.getListAddService();
		return servicesList;
	}

	public void updateService(Service service) throws Exception {

		if (service instanceof DailService) {
			contDailService.updateService(service);
		} else
			contAdditionalService.updateService((AdditionalService) service);
		throw new Exception();

	}

	public List<Service> printDailServicesSortedByName() throws Exception {
		List<Service> dailServicesList = contDailService.getListDailService();
		contDailService.printServicesSortedByName(dailServicesList);

		if (dailServicesList == null) {
			throw new Exception();
		}
		return dailServicesList;
	}

	public List<AdditionalService> printAdditionalServicesSortedByName()
			throws Exception {
		List<AdditionalService> additionalServicesList = contAdditionalService
				.getListAddService();
		additionalServicesList = contAdditionalService
				.printServicesSortedByName(additionalServicesList);

		if (additionalServicesList == null) {
			throw new Exception();
		}
		return additionalServicesList;
	}

	public List<Service> printDailServicesSortedByPrice() throws Exception {
		List<Service> dailServicesList = contDailService.getListDailService();
		contDailService.printServicesSortedByPrice(dailServicesList);

		if (dailServicesList == null) {
			throw new Exception();
		}
		return dailServicesList;
	}

	public List<AdditionalService> printAdditionalServicesSortedByPrice()
			throws Exception {
		List<AdditionalService> additionalServicesList = contAdditionalService
				.getListAddService();

		contAdditionalService
				.printServicesSortedByPrice(additionalServicesList);

		if (additionalServicesList == null) {
			throw new Exception();
		}
		return additionalServicesList;
	}

	public void changeServicePrice(Service service, int price) throws Exception {

		if (service instanceof DailService) {
			contDailService.changePrice(service, price);
		} else if (service instanceof AdditionalService) {
			contAdditionalService.changePrice(service, price);
		}
		throw new Exception();
	}

}
