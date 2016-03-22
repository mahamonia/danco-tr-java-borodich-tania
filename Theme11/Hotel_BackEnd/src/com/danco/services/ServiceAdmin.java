package com.danco.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.ConfigProperty;
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
import com.danco.model.dao.GuestDao;
import com.danco.model.dao.RoomDao;
import com.danco.model.dao.ServiceDao;
import com.danco.model.entity.Check;
import com.danco.model.entity.Guest;
import com.danco.model.entity.Room;
import com.danco.model.entity.Service;
import com.danco.model.entity.Status;

public class ServiceAdmin implements IServiceAdmin {

	@ConfigProperty(configName = "jdbc_config.properties", propertyName = "driver", type = "String")
	private String driver = "com.mysql.jdbc.Driver";

	@ConfigProperty(configName = "jdbc_config.properties", propertyName = "url", type = "String")
	private String url = "jdbc:mysql://localhost:3306/Hotel_service";

	@ConfigProperty(configName = "jdbc_config.properties", propertyName = "userName", type = "String")
	private String userName = "root";

	@ConfigProperty(configName = "jdbc_config.properties", propertyName = "userPassvord", type = "String")
	private String userPassvord = "root";

	private IControllerRoom contRoom;
	private IControllerGuest contGuest;
	private IControllerCheck contCheck;
	private IControllerService contService;

	@Injection
	private IProcessorAnnotation annotation;

	private static final Logger LOGGER = LogManager
			.getLogger(ServiceAdmin.class);

	private Connection connection;

	public ServiceAdmin() {
	}

	@Override
	public void initData() {

		try {
			Class.forName(driver).newInstance();

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		GuestDao guestDao = new GuestDao();
		RoomDao roomDao = new RoomDao();
		CheckDao checkDao = new CheckDao();
		ServiceDao serviceDao = new ServiceDao();

		IControllerGuest contGuest = new ControllerGuest(guestDao);
		IControllerRoom contRoom = new ControllerRoom(roomDao);
		IControllerCheck contCheck = new ControllerCheck(checkDao);
		IControllerService contService = new ControllerService(serviceDao);

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
		try {
			connection = DriverManager.getConnection(url, userName,
					userPassvord);
			contGuest.createGuest(connection, guest);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

	@Override
	public Guest getGuestById(int id) {
		Guest guest = null;
		try {
			connection = DriverManager.getConnection(url, userName,
					userPassvord);
			guest = contGuest.getGuest(connection, id);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}
		return guest;
	}

	@Override
	public List<Guest> getListGuest() {
		return contGuest.getListGuest(connection);

	}

	@Override
	public void deleteGuest(int idGuest) {
		try {
			connection = DriverManager.getConnection(url, userName,
					userPassvord);
			contGuest.deleteGuest(connection, idGuest);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

	@Override
	public void updateGuest(int idGuest) {
		try {
			connection = DriverManager.getConnection(url, userName,
					userPassvord);
			contGuest.updateGuest(connection, idGuest);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}

	}

	@Override
	public void settleGuestInRoom(int idGuest, int idRoom, String dateInSettle,
			String dateOutSettle) {

		synchronized (contRoom) {
			try {

				connection.setAutoCommit(false);

				Room room = contRoom.getRoom(connection, idRoom);

				Check check = new Check(LocalDateTime.parse(dateInSettle),
						LocalDateTime.parse(dateOutSettle), false, idGuest,
						idRoom);

				contCheck.createCheck(connection, check);
				Service service = new Service("Settle guest in room",
						room.getPrice());
				service.setIdCheck(check.getId());
				contService.createService(connection, service);

				// System.out.println(LocalDateTime.parse("2003-03-01"));

				contService.updateService(connection, service.getId());

				contRoom.changeRoomStatus(connection, idRoom, Status.NOTFREE);
				contRoom.updateRoom(connection, idRoom);

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
			try {
				connection.setAutoCommit(false);

				// 1. guest pay the check
				Check check = contCheck.getIdCheckForIdGuest(connection,
						idGuest);
				check.setStatus(true);

				// 2. change room status

				contRoom.changeRoomStatus(connection, check.getIdRoom(),
						Status.FREE);
				contRoom.updateRoom(connection, check.getIdRoom());
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
			}
		}
	}

	@Override
	public Room getRoomInLiveGuest(int idGuest) {
		int idRoom = contCheck.getRoomInLiveGuest(connection, idGuest);
		return contRoom.getRoom(connection, idRoom);
	}

	@Override
	public List<Guest> printGuestsSortedByName() {
		return contGuest.getListGuestSortedByName(connection);
	}

	@Override
	public List<Guest> printGuestsSortedByDateOutSettle() {
		return contGuest.getListGuestSortedByDateOutSettle(connection);
	}

	@Override
	public int getAmountGuest() {
		return contGuest.getAmountGuest(connection);
	}

	@Override
	public void importGuestsList() {
		try {
			connection = DriverManager.getConnection(url, userName,
					userPassvord);
			contGuest.importGuestsList(connection);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}

	}

	@Override
	public void exportGuestsList() {// write in CSV
		try {
			connection = DriverManager.getConnection(url, userName,
					userPassvord);
			contGuest.exportGuestsList(connection);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}

	}

	// ======= ROOM ======

	@Override
	public void createRoom(Room room) {
		synchronized (contRoom) {
			contRoom.createRoom(connection, room);
		}
	}

	@Override
	public List<Room> getListRoom() {
		return contRoom.getListRoom(connection);
	}

	@Override
	public void updateRoom(int idRoom) {
		synchronized (contRoom) {
			contRoom.updateRoom(connection, idRoom);
		}
	}

	@Override
	public List<Room> getListRoomSortedByContent() {
		return contRoom.getListRoomSortedByContetn(connection);
	}

	@Override
	public List<Room> getListRoomSortedByNumber() {
		return contRoom.getListRoomSortedByNumber(connection);
	}

	@Override
	public List<Room> getListRoomSortedByPrice() {
		return contRoom.getListRoomSortedByPrice(connection);
	}

	@Override
	public List<Room> getListRoomSortedByStars() {
		return contRoom.getListRoomFreeSortedByStars(connection);
	}

	@Override
	public List<Room> getListRoomFree() {
		return contRoom.getListRoomFree(connection);
	}

	@Override
	public List<Room> getListRoomFreeSortedByContent() {
		return contRoom.getListRoomFreeSortedByContetn(connection);
	}

	@Override
	public List<Room> getListRoomFreeSortedByNumber() {
		return contRoom.getListRoomFreeSortedByNumber(connection);
	}

	@Override
	public List<Room> getListRoomFreeSortedByPrice() {
		return contRoom.getListRoomFreeSortedByPrice(connection);
	}

	@Override
	public List<Room> getListRoomFreeSortedByStars() {
		return contRoom.getListRoomFreeSortedByStars(connection);
	}

	@Override
	public int getAmountRoomFree() {
		int amount = 0;
		try {
			connection = DriverManager.getConnection(url, userName,
					userPassvord);
			amount = contRoom.getAmountRoomFree(connection);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}
		
		
		return amount;
	}

	@Override
	public void changeRoomStatus(int idRoom, String status) {
		synchronized (contRoom) {
			contRoom.changeRoomStatus(connection, idRoom,
					Status.valueOf(status));
		}

	}

	@Override
	public void changeRoomPrice(int idRoom, int price) {
		synchronized (contRoom) {
			contRoom.changeRoomPrice(connection, idRoom, price);
		}
	}

	@Override
	public void cloneRoom(int idRoom) {
		contRoom.cloneRoom(connection, idRoom);
	}

	@Override
	public void importRoomsList() {
		contRoom.importRoomsList(connection);

	}

	@Override
	public void exportRoomsList() {// write in CSV
		try {
			connection = DriverManager.getConnection(url, userName,
					userPassvord);
			contRoom.exportRoomsList(connection);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}
		

	}

	// ========= SERVICE ===============

	@Override
	public void createService(Service service) {
		contService.createService(connection, service);

	}

	@Override
	public void updateService(int idService) {
		contService.updateService(connection, idService);

	}

	@Override
	public Service getServiceById(int idService) {
		return contService.getService(connection, idService);
	}

	@Override
	public List<Service> getListService() {
		return contService.getListService(connection);

	}

	@Override
	public List<Service> printServicesSortedByName() {
		return contService.getServiceSortedByName(connection);
	}

	@Override
	public List<Service> printServicesSortedByPrice() {
		return contService.getServiceSortedByPrice(connection);
	}

	@Override
	public List<Service> getGuestThemServices(int idGuest) {
		return contService.getGuestThemServices(connection, idGuest);
	}

	@Override
	public void changeServicePrice(int idService, int price) {
		contService.changePrice(connection, idService, price);
	}

	@Override
	public void importServicesList() {
		contService.importServicesList(connection);

	}

	@Override
	public void exportServicesList() {// write in CSV
		contService.exportServicesList(connection);

	}

	// ========= CHECK =============

	@Override
	public void createCheck(Check check) {
		contCheck.createCheck(connection, check);

	}

	@Override
	public void updateCheck(int idCheck) {
		contCheck.updateCheck(connection, idCheck);

	}

	@Override
	public Check getCheckById(int idCheck) {
		return contCheck.getCheck(connection, idCheck);
	}

	@Override
	public List<Check> getListCheck() {
		return contCheck.getListCheck(connection);
	}

	@Override
	public int getSumCheck(int idGuest) {
		return contService.getServiceSumPrice(connection, idGuest);
	}

}