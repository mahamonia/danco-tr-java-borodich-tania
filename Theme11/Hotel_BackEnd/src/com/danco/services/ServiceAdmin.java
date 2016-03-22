package com.danco.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerCheck;
import com.danco.api.backend.IControllerService;
import com.danco.api.backend.IControllerGuest;
import com.danco.api.backend.IControllerRoom;
import com.danco.api.backend.IProcessorAnnotation;
import com.danco.api.backend.IServiceAdmin;
import com.danco.controller.ControllerCheck;
import com.danco.controller.ControllerGuest;
import com.danco.controller.ControllerRoom;
import com.danco.controller.ControllerService;
import com.danco.dependency.DependencyInjection;
import com.danco.model.dao.CheckDao;
import com.danco.model.dao.DataSource;
import com.danco.model.dao.GuestDao;
import com.danco.model.dao.RoomDao;
import com.danco.model.dao.ServiceDao;
import com.danco.model.entity.Check;
import com.danco.model.entity.Guest;
import com.danco.model.entity.Room;
import com.danco.model.entity.Service;
import com.danco.model.entity.Status;

public class ServiceAdmin implements IServiceAdmin {

	private IControllerRoom contRoom;
	private IControllerGuest contGuest;
	private IControllerCheck contCheck;
	private IControllerService contService;

	@Injection
	private IProcessorAnnotation annotation;

	private static final Logger LOGGER = LogManager
			.getLogger(ServiceAdmin.class);

	@Injection
	private DataSource source;

	public ServiceAdmin() {
	}

	@Override
	public void initData() {

		GuestDao guestDao = new GuestDao();
		RoomDao roomDao = new RoomDao();
		CheckDao checkDao = new CheckDao();
		ServiceDao serviceDao = new ServiceDao();
		
		annotation.processAnnotation(source);
		
		IControllerGuest contGuest = new ControllerGuest(source, guestDao);
		IControllerRoom contRoom = new ControllerRoom(source, roomDao);
		IControllerCheck contCheck = new ControllerCheck(source, checkDao);
		IControllerService contService = new ControllerService(source,
				serviceDao);

		DependencyInjection.getInstance().getDI(contGuest, contRoom,
				contService);

		annotation.processAnnotation(contGuest);
		annotation.processAnnotation(contRoom);
		
		

		this.contGuest = contGuest;
		this.contRoom = contRoom;
		this.contCheck = contCheck;
		this.contService = contService;

	}

	@Override
	public void saveData() {

		// ----- NOTHING ------

	}

	// ==== GUEST =====

	@Override
	public void createGuest(Guest guest) {
		contGuest.createGuest(guest);
	}

	@Override
	public Guest getGuestById(int id) {
		return contGuest.getGuest(id);
	}

	@Override
	public List<Guest> getListGuest() {
		return contGuest.getListGuest();

	}

	@Override
	public void deleteGuest(int idGuest) {
		contGuest.deleteGuest(idGuest);
	}

	@Override
	public void updateGuest(int idGuest) {
		contGuest.updateGuest(idGuest);

	}

	@Override
	public void settleGuestInRoom(int idGuest, int idRoom, String dateInSettle,
			String dateOutSettle) {

		synchronized (contRoom) {
			Connection connection = null;
			try {
				connection = source.openConnection();
				connection.setAutoCommit(false);
 
				Room room = contRoom.getRoom(idRoom);

				Service service = new Service("Settle guest in room",
						room.getPrice());
				contService.createService(service);

				Check check = new Check(LocalDateTime.parse(dateInSettle),
						LocalDateTime.parse(dateOutSettle), false, idGuest,
						idRoom);
				contCheck.createCheck(check);

				service.setIdCheck(check.getId());
				contService.updateService(service.getId());

				contRoom.changeRoomStatus(idRoom, Status.NOTFREE);
				contRoom.updateRoom(idRoom);

				connection.commit();

			} catch (SQLException e) {
				if (connection != null) {
					try {
						System.err.print("Transaction is being rolled back");
						connection.rollback();
					} catch (SQLException excep) {
						LOGGER.error(e.getMessage());
					}
				}
				LOGGER.error(e.getMessage());
			} finally {
				source.closeConnection();
			}
		}
	}

	@Override
	public void addServiceForGuest(int idGuest, int idService) {
		// Guest guest = contGuest.getGuestById(idGuest);

		// DailService service = contDailService.getService(idService);
		// contGuest.addServiceForGuest(guest, idService);

	}

	@Override
	public void settleGuestOutRoom(int idGuest) {
		synchronized (contRoom) {
			Connection connection = null;
			try {
				connection = source.openConnection();
				connection.setAutoCommit(false);

				// 1. guest pay the check
				Check check = contCheck.getIdCheckForIdGuest(idGuest);
				check.setStatus(true);

				// 2. change room status

				contRoom.changeRoomStatus(check.getIdRoom(), Status.FREE);
				contRoom.updateRoom(check.getIdRoom());
				connection.commit();

			} catch (SQLException e) {
				if (connection != null) {
					try {
						System.err.print("Transaction is being rolled back");
						connection.rollback();
					} catch (SQLException excep) {
						LOGGER.error(e.getMessage());
					}
				}
				LOGGER.error(e.getMessage());
			} finally {
				source.closeConnection();
			}
		}
	}

	@Override
	public Room getRoomInLiveGuest(int idGuest) {
		int idRoom = contCheck.getRoomInLiveGuest(idGuest);
		return contRoom.getRoom(idRoom);
	}


	@Override
	public List<Guest> printGuestsSortedByName() {
		return contGuest.getListGuestSortedByName();
	}

	@Override
	public List<Guest> printGuestsSortedByDateOutSettle() {
		return contGuest.getListGuestSortedByDateOutSettle();
	}

	@Override
	public int getAmountGuest() {
		return contGuest.getAmountGuest();
	}

	@Override
	public void importGuestsList() {
		contGuest.importGuestsList();

	}

	@Override
	public void exportGuestsList() {// write in CSV
		contGuest.exportGuestsList();

	}

	// ======= ROOM ======

	@Override
	public void createRoom(Room room) {
		synchronized (contRoom) {
			contRoom.createRoom(room);
		}
	}

	@Override
	public List<Room> getListRoom() {
		return contRoom.getListRoom();
	}

	@Override
	public void updateRoom(int idRoom) {
		synchronized (contRoom) {
			contRoom.updateRoom(idRoom);
		}
	}

	@Override
	public List<Room> getListRoomSortedByContent() {
		return contRoom.getListRoomSortedByContetn();
	}

	@Override
	public List<Room> getListRoomSortedByNumber() {
		return contRoom.getListRoomSortedByNumber();
	}

	@Override
	public List<Room> getListRoomSortedByPrice() {
		return contRoom.getListRoomSortedByPrice();
	}

	@Override
	public List<Room> getListRoomSortedByStars() {
		return contRoom.getListRoomFreeSortedByStars();
	}

	@Override
	public List<Room> getListRoomFree() {
		return contRoom.getListRoomFree();
	}

	@Override
	public List<Room> getListRoomFreeSortedByContent() {
		return contRoom.getListRoomFreeSortedByContetn();
	}

	@Override
	public List<Room> getListRoomFreeSortedByNumber() {
		return contRoom.getListRoomFreeSortedByNumber();
	}

	@Override
	public List<Room> getListRoomFreeSortedByPrice() {
		return contRoom.getListRoomFreeSortedByPrice();
	}

	@Override
	public List<Room> getListRoomFreeSortedByStars() {
		return contRoom.getListRoomFreeSortedByStars();
	}

	@Override
	public int getAmountRoomFree() {
		return contRoom.getAmountRoomFree();
	}

	@Override
	public void changeRoomStatus(int idRoom, String status) {
		synchronized (contRoom) {
			contRoom.changeRoomStatus(idRoom, Status.valueOf(status));
		}

	}

	@Override
	public void changeRoomPrice(int idRoom, int price) {
		synchronized (contRoom) {
			contRoom.changeRoomPrice(idRoom, price);
		}
	}

	@Override
	public void cloneRoom(int idRoom) {
		contRoom.cloneRoom(idRoom);
	}

	@Override
	public void importRoomsList() {
		contRoom.importRoomsList();

	}

	@Override
	public void exportRoomsList() {// write in CSV
		contRoom.exportRoomsList();

	}

	// ========= SERVICE ===============

	@Override
	public void createService(Service service) {
		contService.createService(service);

	}

	@Override
	public void updateService(int idService) {
		contService.updateService(idService);

	}

	@Override
	public Service getServiceById(int idService) {
		return contService.getService(idService);
	}

	@Override
	public List<Service> getListService() {
		return contService.getListService();

	}

	@Override
	public List<Service> printServicesSortedByName() {
		return contService.getServiceSortedByName();
	}

	@Override
	public List<Service> printServicesSortedByPrice() {
		return contService.getServiceSortedByPrice();
	}

	@Override
	public List<Service> getGuestThemServices(int idGuest) {
		return contService.getGuestThemServices(idGuest);
	}

	@Override
	public void changeServicePrice(int idService, int price) {
		contService.changePrice(idService, price);
	}

	@Override
	public void importServicesList() {
		contService.importServicesList();

	}

	@Override
	public void exportServicesList() {// write in CSV
		contService.exportServicesList();

	}

	// ========= CHECK =============

	@Override
	public void createCheck(Check check) {
		contCheck.createCheck(check);

	}

	@Override
	public void updateCheck(int idCheck) {
		contCheck.updateCheck(idCheck);

	}

	@Override
	public Check getCheckById(int idCheck) {
		return contCheck.getCheck(idCheck);
	}

	@Override
	public List<Check> getListCheck() {
		return contCheck.getListCheck();
	}

	@Override
	public int getSumCheck(int idGuest) {
		return contService.getServiceSumPrice(idGuest);
	}

}