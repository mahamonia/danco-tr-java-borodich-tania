package com.danco.services;

import java.util.List;

import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerAdditionalService;
import com.danco.api.backend.IControllerDailService;
import com.danco.api.backend.IControllerGuest;
import com.danco.api.backend.IControllerRoom;
import com.danco.api.backend.IParseUtilitySerealize;
import com.danco.api.backend.IProcessorAnnotation;
import com.danco.api.backend.IServiceAdmin;
import com.danco.controller.ControllerAdditionalService;
import com.danco.controller.ControllerDailService;
import com.danco.controller.ControllerGuest;
import com.danco.controller.ControllerRoom;
import com.danco.dependency.DependencyInjection;
import com.danco.training.entity.AdditionalService;
import com.danco.training.entity.DailService;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Order;
import com.danco.training.entity.Room;
import com.danco.training.entity.Service;
import com.danco.training.entity.Status;

public class ServiceAdmin implements IServiceAdmin {

	private IControllerRoom contRoom;
	private IControllerGuest contGuest;
	private IControllerDailService contDailService;
	private IControllerAdditionalService contAdditionalService;
	@Injection
	private IParseUtilitySerealize utilitySerealize;
	@Injection
	private IProcessorAnnotation annotation;

	private final int RESIDENTCE_IN_ROOM = 1;

	public ServiceAdmin() {
	}

	@Override
	public void initData() {

		List<Guest> guestsList = utilitySerealize.getGuestsList();
		List<Room> roomsList = utilitySerealize.getRoomsList();
		List<Order> ordersList = utilitySerealize.getOrdersList();
		List<DailService> dailServicesList = utilitySerealize
				.getDailServiceList();
		List<AdditionalService> additionalServicesList = utilitySerealize
				.getAdditionalServiceList();

		IControllerGuest contGuest = new ControllerGuest(guestsList, ordersList);
		IControllerRoom contRoom = new ControllerRoom(roomsList);
		IControllerDailService contDailService = new ControllerDailService(
				dailServicesList);
		IControllerAdditionalService contAdditionalService = new ControllerAdditionalService(
				additionalServicesList);

		DependencyInjection.getInstance().getDI(contGuest, contRoom,
				contDailService, contAdditionalService);

		annotation.processAnnotation(contGuest);
		annotation.processAnnotation(contRoom);

		this.contGuest = contGuest;
		this.contRoom = contRoom;
		this.contDailService = contDailService;
		this.contAdditionalService = contAdditionalService;

	}

	@Override
	public void saveData() {

		List<Guest> guestsList = contGuest.getListGuest();
		List<Room> roomsList = contRoom.getListRoom();
		List<Order> ordersList = contGuest.getListOrder();
		List<DailService> dailServicesList = contDailService
				.getListDailService();
		List<AdditionalService> additionalServicesList = contAdditionalService
				.getListAdditionalService();

		utilitySerealize.setSerializeData(guestsList, roomsList, ordersList,
				dailServicesList, additionalServicesList);

	}

	// ==== GUEST =====

	@Override
	public void createGuest(Guest guest) {

		guest.setId(contGuest.getIdForNewGuest());
		contGuest.createGuest(guest);
	}

	@Override
	public Guest getGuestById(int id) {
		return contGuest.getGuestById(id);
	}

	@Override
	public List<Guest> getListGuest() {
		return contGuest.getListGuest();

	}

	@Override
	public void deleteGuest(int idGuest) {
		contGuest.deleteGuest(contGuest.getGuestById(idGuest));
	}

	@Override
	public void updateGuest(int idGuest) {
		Guest guest = contGuest.getGuestById(idGuest);
		contGuest.updateGuest(guest);

	}

	@Override
	public Order getOrderById(int id) {
		return contGuest.getOrderById(id);
	}

	@Override
	public void settleGuestInRoom(int idGuest, int idRoom, String dateInSettle,
			String dateOutSettle) {
		synchronized (contRoom) {

			Guest guest = contGuest.getGuestById(idGuest);
			Room room = contRoom.getRoomByNumber(idRoom);
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
	}

	@Override
	public void addServiceForGuest(int idGuest, int idService) {
		Guest guest = contGuest.getGuestById(idGuest);

		Service service = contDailService.getService(idService);
		contGuest.addServiceForGuest(guest, service);

	}

	@Override
	public void settleGuestOutRoom(int idGuest) {
		synchronized (contRoom) {
			Guest guest = contGuest.getGuestById(idGuest);
			// 1. guest pay the order
			// 2. change room status
			List<Room> roomsList = contRoom.getListRoom();
			Room room = contGuest.getRoomInLiveGuest(guest, roomsList);
			contRoom.changeRoomStatus(room, Status.FREE);
			contRoom.updateRoom(room);
		}
	}

	@Override
	public Room getRoomInLiveGuest(int idGuest) {
		Guest guest = contGuest.getGuestById(idGuest);
		List<Room> roomsList = contRoom.getListRoom();
		return contGuest.getRoomInLiveGuest(guest, roomsList);
	}

	@Override
	public int getSumOrderGuest(int idGuest) {
		Guest guest = contGuest.getGuestById(idGuest);
		return contGuest.getSumOrderGuest(guest);

	}

	@Override
	public List<Guest> printListGuestRoom(int idRoom) {
		Room room = contRoom.getRoomByNumber(idRoom);
		return contRoom.getListGuestRoom(room);
	}

	@Override
	public List<Guest> printGuestsSortedByName() {
		List<Guest> guestsList = contGuest.getListGuest();
		return contGuest.printGuestsSortedByName(guestsList);
	}

	@Override
	public List<Guest> printGuestsSortedByDateOutSettle() {
		List<Guest> guestsList = contGuest.getListGuest();
		return contGuest.printGuestsSortedByDateOutSettle(guestsList);
	}

	@Override
	public int getAmountGuest() {
		return contGuest.getAmountGuest();
	}

	@Override
	public synchronized void importGuestsList() { // read from CSV

		List<Guest> importList = contGuest.importGuestsList();
		List<Guest> existList = contGuest.getListGuest();

		// replace the existing on imported
		for (int i = 0; i < existList.size(); i++) {
			for (int j = 0; j < importList.size(); j++) {
				if (existList.get(i).getId() == importList.get(j).getId()) {
					existList.set(i, importList.get(j));
				}
			}
		}
		// if imported is new add in existList
		for (int i = 0; i < importList.size(); i++) {
			for (int j = 0; j < existList.size(); j++) {
				if (importList.get(i).getId() != existList.get(j).getId()) {
					existList.add(importList.get(i));
				}
			}
		}
		contGuest.setListGuest(existList); // updata List
	}

	@Override
	public void exportGuestsList() { // write in CSV
		contGuest.exportGuestsList(contGuest.getListGuest());
	}

	// ======= room ======
	@Override
	public void createRoom(Room room) {
		synchronized (contRoom) {
			contRoom.createRoom(room);
		}

	}

	@Override
	public int getNumberForNewRoom() {
		return contRoom.getNumberForNewRoom();
	}

	@Override
	public Room getRoomByNumber(int number) {
		return contRoom.getRoomByNumber(number);
	}

	@Override
	public List<Room> getListRoom() {
		return contRoom.getListRoom();
	}

	@Override
	public List<Guest> getListGuestRoom(int idRoom) {
		Room room = contRoom.getRoomByNumber(idRoom);
		return contRoom.getListGuestRoom(room);
	}

	@Override
	public void updateRoom(int idRoom) {
		Room room = contRoom.getRoomByNumber(idRoom);
		contRoom.updateRoom(room);
	}

	@Override
	public List<Room> printSortedRoomsByContent() {
		List<Room> roomsList = contRoom.getListRoom();
		return contRoom.printRoomSortedByContetn(roomsList);
	}

	@Override
	public List<Room> printSortedRoomsByNumber() {
		List<Room> roomsList = contRoom.getListRoom();
		return contRoom.printRoomSortedByNumber(roomsList);

	}

	@Override
	public List<Room> printSortedRoomsByPrice() {
		List<Room> roomsList = contRoom.getListRoom();
		return contRoom.printRoomSortedByPrice(roomsList);
	}

	@Override
	public List<Room> printSortedRoomByStars() {
		List<Room> roomsList = contRoom.getListRoom();
		return contRoom.printRoomSortedByStars(roomsList);

	}

	@Override
	public List<Room> printRoomFreeSortetdByContent() {
		List<Room> roomsList = contRoom.getRoomListFree();
		return contRoom.printRoomSortedByContetn(roomsList);

	}

	@Override
	public List<Room> printRoomFreeSortetdByPrice() {
		List<Room> roomsList = contRoom.getRoomListFree();
		return contRoom.printRoomSortedByPrice(roomsList);

	}

	@Override
	public List<Room> printRoomFreeSortetdByNumber() {
		List<Room> roomsList = contRoom.getRoomListFree();
		return contRoom.printRoomSortedByNumber(roomsList);

	}

	@Override
	public List<Room> printRoomFreeSortetdByStars() {
		List<Room> roomsList = contRoom.getRoomListFree();
		return contRoom.printRoomSortedByStars(roomsList);

	}

	@Override
	public int getAmountFreeRoom() {
		return contRoom.printAmountRoomFree();
	}

	@Override
	public void changeRoomStatus(int idRoom, String str) {
		synchronized (contRoom) {
			Room room = contRoom.getRoomByNumber(idRoom);
			Status status = Status.valueOf(str);
			contRoom.changeRoomStatus(room, status);
		}

	}

	@Override
	public void changeRoomPrice(int idRoom, int price) {
		synchronized (contRoom) {
			Room room = contRoom.getRoomByNumber(idRoom);
			contRoom.changeRoomPrice(room, price);
		}

	}

	@Override
	public void cloneRoom(int idRoom) {
		Room room = contRoom.getRoomByNumber(idRoom);
		contRoom.createRoom(contRoom.cloneRoom(room));

	}

	@Override
	public void importRoomsList() { // read from CSV
		synchronized (contRoom) {
			List<Room> importList = contRoom.importRoomsList();
			List<Room> existList = contRoom.getListRoom();
			// replace the existing on imported
			for (int i = 0; i < existList.size(); i++) {
				for (int j = 0; j < importList.size(); j++) {
					if (existList.get(i).getNumber() == importList.get(j)
							.getNumber()) {
						existList.set(i, importList.get(j));
					}
				}
			}
			// if imported is new add in existList
			for (int i = 0; i < importList.size(); i++) {
				for (int j = 0; j < existList.size(); j++) {
					if (importList.get(i).getNumber() != existList.get(j)
							.getNumber()) {
						existList.add(importList.get(i));
					}
				}
			}
			contRoom.setListRoom(existList);
		}

	}

	@Override
	public void exportRoomsList() { // write in CSV
		contRoom.exportRoomsList(contRoom.getListRoom());
	}

	// ====== service =====
	@Override
	public void createService(Service service) {
		service.setId(contDailService.getIdForNewService());
		contDailService.createService((DailService) service);

	}

	@Override
	public void createAdditionalService(AdditionalService service) {
		service.setId(contAdditionalService.getIdForNewService());
		contAdditionalService.createService(service);
	}

	@Override
	public Service getServiceById(int id) {

		if (contDailService.getService(id) != null) {
			return contDailService.getService(id);
		} else if (contAdditionalService.getService(id) != null) {
			return contAdditionalService.getService(id);
		} else
			return null;
	}

	@Override
	public List<DailService> getListDailService() {
		return contDailService.getListDailService();
	}

	@Override
	public List<AdditionalService> getListAdditionalService() {
		return contAdditionalService.getListAdditionalService();
	}

	@Override
	public List<DailService> printDailServicesSortedByName() {
		List<DailService> dailServicesList = contDailService
				.getListDailService();
		return contDailService.printServicesSortedByName(dailServicesList);
	}

	@Override
	public List<AdditionalService> printAdditionalServicesSortedByName() {
		List<AdditionalService> additionalServicesList = contAdditionalService
				.getListAdditionalService();
		return contAdditionalService
				.printServicesSortedByName(additionalServicesList);
	}

	@Override
	public List<DailService> printDailServicesSortedByPrice() {
		List<DailService> dailServicesList = contDailService
				.getListDailService();
		contDailService.printServicesSortedByPrice(dailServicesList);
		return dailServicesList;
	}

	@Override
	public List<AdditionalService> printAdditionalServicesSortedByPrice() {
		List<AdditionalService> additionalServicesList = contAdditionalService
				.getListAdditionalService();
		return contAdditionalService
				.printServicesSortedByPrice(additionalServicesList);
	}

	@Override
	public synchronized void changeServicePrice(int idService, int price) {

		if (idService >= contDailService.getListDailService().size()) {
			contAdditionalService.changeAdditionalPrice(idService, price);
		} else
			contDailService.changePrice(idService, price);

	}

	@Override
	public void importDailServicesList() { // read from CSV
		synchronized (contDailService) {
			List<DailService> importList = contDailService.importServicesList();
			List<DailService> existList = contDailService.getListDailService();
			// replace the existing on imported
			for (int i = 0; i < existList.size(); i++) {
				for (int j = 0; j < importList.size(); j++) {
					if (existList.get(i).getId() == importList.get(j).getId()) {
						existList.set(i, importList.get(j));
					}
				}
			}
			// if imported is new add in existList
			for (int i = 0; i < importList.size(); i++) {
				for (int j = 0; j < existList.size(); j++) {
					if (importList.get(i).getId() != existList.get(j).getId()) {
						existList.add(importList.get(i));
					}
				}
			}
			contDailService.setListDailService(existList);
		}
	}

	@Override
	public void exportDailServicesList() { // write in CSV
		contDailService
				.exportServicesList(contDailService.getListDailService());
	}

	@Override
	public void importAdditionalServicesList() { // read from CSV
		synchronized (contAdditionalService) {
			List<AdditionalService> importList = contAdditionalService
					.importServicesList();
			List<AdditionalService> existList = contAdditionalService
					.getListAdditionalService();
			// replace the existing on imported
			for (int i = 0; i < existList.size(); i++) {
				for (int j = 0; j < importList.size(); j++) {
					if (existList.get(i).getId() == importList.get(j).getId()) {
						existList.set(i, importList.get(j));
					}
				}
			}
			// if imported is new add in existList
			for (int i = 0; i < importList.size(); i++) {
				for (int j = 0; j < existList.size(); j++) {
					if (importList.get(i).getId() != existList.get(j).getId()) {
						existList.add(importList.get(i));
					}
				}
			}
			contAdditionalService.setListAdditionalService(existList);
		}
	}

	@Override
	public void exportAdditionalServicesList() { // write in CSV
		contAdditionalService.exportServicesList(contAdditionalService
				.getListAdditionalService());
	}

}