package com.danco.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.ConfigProperty;
import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerChek;
import com.danco.api.backend.IControllerService;
import com.danco.api.backend.IControllerGuest;
import com.danco.api.backend.IControllerRoom;
import com.danco.api.backend.IProcessorAnnotation;
import com.danco.api.backend.IServiceAdmin;
import com.danco.dependency.DependencyInjection;
import com.danco.model.entity.Chek;
import com.danco.model.entity.Guest;
import com.danco.model.entity.Room;
import com.danco.model.entity.Service;
import com.danco.model.entity.Status;

public class ServiceAdmin implements IServiceAdmin {

	@ConfigProperty(configName = "jdbc_config.properties", propertyName = "driver", type = "String")
	private String driver;

	@ConfigProperty(configName = "jdbc_config.properties", propertyName = "url", type = "String")
	private String url;

	@ConfigProperty(configName = "jdbc_config.properties", propertyName = "userName", type = "String")
	private String userName;

	@ConfigProperty(configName = "jdbc_config.properties", propertyName = "userPassvord", type = "String")
	private String userPassvord;

	@Injection
	private IControllerRoom contRoom;
	@Injection
	private IControllerGuest contGuest;
	@Injection
	private IControllerChek contChek;
	@Injection
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
		
		annotation.processAnnotation(this);
		try {
			Class.forName(driver).newInstance();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		DependencyInjection.getInstance().getDI(contGuest, contRoom,
				contService, contChek);

		annotation.processAnnotation(contGuest);
		annotation.processAnnotation(contRoom);
		

	}

	@Override
	public void saveData() {
		// ----- NOTHING ------
	}

	// ==== GUEST =====

	private Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, userName,
					userPassvord);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return connection;
	}

	private void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void createGuest(Guest guest) {
		connection = getConnection();
		contGuest.createGuest(connection, guest);
		closeConnection(connection);
	}

	@Override
	public Guest getGuestById(int id) {
		Guest guest = null;
		connection = getConnection();
		guest = contGuest.getGuest(connection, id);
		closeConnection(connection);
		return guest;
	}

	@Override
	public List<Guest> getListGuest() {
		connection = getConnection();
		List<Guest> list = contGuest.getListGuest(connection);
		closeConnection(connection);
		return list;

	}

	@Override
	public void deleteGuest(int idGuest) {
		connection = getConnection();
		contGuest.deleteGuest(connection, idGuest);
		closeConnection(connection);
	}

	@Override
	public void settleGuestInRoom(int idGuest, int idRoom, String dateInSettle,
			String dateOutSettle) {

		synchronized (contRoom) {
			try {
				connection = getConnection();
				connection.setAutoCommit(false);

				Room room = contRoom.getRoom(connection, idRoom);
				if (room.getStatus() != Status.FREE) {
					System.out.println("Sorry, room not free!");
				} else {
					Guest guest = contGuest.getGuest(connection, idGuest);

					Chek chek = new Chek(Date.valueOf(dateInSettle),
							Date.valueOf(dateOutSettle), false, guest, room);

					contChek.createChek(connection, chek);
					Service service = new Service("Settle guest in room",
							room.getPrice());
					service.setChek(chek);
					contService.createService(connection, service);

					contRoom.changeRoomStatus(connection, room, Status.NOTFREE);
					contRoom.updateRoom(connection, room);
				}
				connection.commit();

			} catch (SQLException e) {
				if (connection != null) {
					try {
						System.err.print("Transaction is being rolled back");
						connection.rollback();
						closeConnection(connection);
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

		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			Chek chek = contChek.getChekForIdGuest(connection, idGuest);

			Service service = contService.getService(connection, idService);
			service.setChek(chek);
			contService.updateService(connection, service);

			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					System.err.print("Transaction is being rolled back");
					connection.rollback();
					closeConnection(connection);
				} catch (SQLException excep) {
					LOGGER.error(e.getMessage());
				}
			}
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void settleGuestOutRoom(int idGuest) {
		synchronized (contRoom) {
			try {
				connection = getConnection();
				connection.setAutoCommit(false);

				// 1. guest pay the check
				Chek chek = contChek.getChekForIdGuest(connection, idGuest);
				chek.setStatus(true);
				contChek.updateChek(connection, chek);

				// 2. change room status
				contRoom.changeRoomStatus(connection, chek.getRoom(),
						Status.FREE);
				contRoom.updateRoom(connection, chek.getRoom());
				connection.commit();

			} catch (SQLException e) {
				if (connection != null) {
					try {
						System.err.print("Transaction is being rolled back");
						connection.rollback();
						closeConnection(connection);
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
		Room room = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			int idRoom = contChek.getRoomInLiveGuest(connection, idGuest);
			room = contRoom.getRoom(connection, idRoom);
			connection.commit();

		} catch (SQLException e) {
			if (connection != null) {
				try {
					System.err.print("Transaction is being rolled back");
					connection.rollback();
					closeConnection(connection);
				} catch (SQLException excep) {
					LOGGER.error(e.getMessage());
				}
			}
			LOGGER.error(e.getMessage());
		}
		return room;
	}

	@Override
	public List<Guest> printGuestsSortedByName() {
		connection = getConnection();
		List<Guest> list = contGuest.getListGuestSortedByName(connection);
		closeConnection(connection);
		return list;
	}

	@Override
	public int getAmountGuest() {
		connection = getConnection();
		int amount = contGuest.getAmountGuest(connection);
		closeConnection(connection);
		return amount;
	}

	@Override
	public void importGuestsList() {
		connection = getConnection();
		contGuest.importGuestsList(connection);
		closeConnection(connection);
	}

	@Override
	public void exportGuestsList() {// write in CSV
		connection = getConnection();
		contGuest.exportGuestsList(connection);
		closeConnection(connection);
	}

	// ======= ROOM ======

	@Override
	public void createRoom(Room room) {
		connection = getConnection();
		synchronized (contRoom) {
			contRoom.createRoom(connection, room);
		}
		closeConnection(connection);

	}

	@Override
	public List<Room> getListRoom() {
		connection = getConnection();
		List<Room> list = contRoom.getListRoom(connection);
		closeConnection(connection);
		return list;
	}

	@Override
	public List<Room> getListRoomSortedByContent() {
		connection = getConnection();
		List<Room> list = contRoom.getListRoomSortedByContetn(connection);
		closeConnection(connection);
		return list;
	}

	@Override
	public List<Room> getListRoomSortedByNumber() {
		connection = getConnection();
		List<Room> list = contRoom.getListRoomSortedByNumber(connection);
		closeConnection(connection);
		return list;
	}

	@Override
	public List<Room> getListRoomSortedByPrice() {
		connection = getConnection();
		List<Room> list = contRoom.getListRoomSortedByPrice(connection);
		closeConnection(connection);
		return list;
	}

	@Override
	public List<Room> getListRoomSortedByStars() {
		connection = getConnection();
		List<Room> list = contRoom.getListRoomFreeSortedByStars(connection);
		closeConnection(connection);
		return list;
	}

	@Override
	public List<Room> getListRoomFree() {
		connection = getConnection();
		List<Room> list = contRoom.getListRoomFree(connection);
		closeConnection(connection);
		return list;
	}

	@Override
	public List<Room> getListRoomFreeSortedByContent() {
		connection = getConnection();
		List<Room> list = contRoom.getListRoomFreeSortedByContetn(connection);
		closeConnection(connection);
		return list;
	}

	@Override
	public List<Room> getListRoomFreeSortedByNumber() {
		connection = getConnection();
		List<Room> list = contRoom.getListRoomFreeSortedByNumber(connection);
		closeConnection(connection);
		return list;
	}

	@Override
	public List<Room> getListRoomFreeSortedByPrice() {
		connection = getConnection();
		List<Room> list = contRoom.getListRoomFreeSortedByPrice(connection);
		closeConnection(connection);
		return list;
	}

	@Override
	public List<Room> getListRoomFreeSortedByStars() {
		connection = getConnection();
		List<Room> list = contRoom.getListRoomFreeSortedByStars(connection);
		closeConnection(connection);
		return list;
	}

	@Override
	public int getAmountRoomFree() {
		connection = getConnection();
		int amount = contRoom.getAmountRoomFree(connection);
		closeConnection(connection);
		return amount;
	}

	@Override
	public void changeRoomStatus(int idRoom, String status) {
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			synchronized (contRoom) {
				Room room = contRoom.getRoom(connection, idRoom);
				Status stat = Status.valueOf(status);
				contRoom.changeRoomStatus(connection, room, stat);
				contRoom.updateRoom(connection, room);
			}
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					System.err.print("Transaction is being rolled back");
					connection.rollback();
					closeConnection(connection);
				} catch (SQLException excep) {
					LOGGER.error(e.getMessage());
				}
			}
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void changeRoomPrice(int idRoom, int price) {
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			synchronized (contRoom) {
				Room room = contRoom.getRoom(connection, idRoom);
				contRoom.changeRoomPrice(connection, room, price);
				contRoom.updateRoom(connection, room);
			}
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					System.err.print("Transaction is being rolled back");
					connection.rollback();
					closeConnection(connection);
				} catch (SQLException excep) {
					LOGGER.error(e.getMessage());
				}
			}
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void cloneRoom(int idRoom) {
		connection = getConnection();
		contRoom.cloneRoom(connection, idRoom);
		closeConnection(connection);
	}

	@Override
	public void importRoomsList() {
		connection = getConnection();
		contRoom.importRoomsList(connection);
		closeConnection(connection);
	}

	@Override
	public void exportRoomsList() {// write in CSV
		connection = getConnection();
		contRoom.exportRoomsList(connection);
		closeConnection(connection);
	}

	// ========= SERVICE ===============

	@Override
	public void createService(Service service) {
		connection = getConnection();
		contService.createService(connection, service);
		closeConnection(connection);
	}

	@Override
	public Service getServiceById(int idService) {
		connection = getConnection();
		Service service = contService.getService(connection, idService);
		closeConnection(connection);
		return service;
	}

	@Override
	public List<Service> getListService() {
		connection = getConnection();
		List<Service> list = contService.getListService(connection);
		closeConnection(connection);
		return list;

	}

	@Override
	public List<Service> printServicesSortedByName() {
		connection = getConnection();
		List<Service> list = contService.getServiceSortedByName(connection);
		closeConnection(connection);
		return list;
	}

	@Override
	public List<Service> printServicesSortedByPrice() {
		connection = getConnection();
		List<Service> list = contService.getServiceSortedByPrice(connection);
		closeConnection(connection);
		return list;
	}

	@Override
	public List<Service> getGuestThemServices(int idGuest) {
		connection = getConnection();
		List<Service> list = contService.getGuestThemServices(connection,
				idGuest);
		closeConnection(connection);
		return list;
	}

	@Override
	public void changeServicePrice(int idService, int price) {
		connection = getConnection();
		contService.changePrice(connection, idService, price);
		closeConnection(connection);
	}

	@Override
	public void importServicesList() {
		connection = getConnection();
		contService.importServicesList(connection);
		closeConnection(connection);
	}

	@Override
	public void exportServicesList() {// write in CSV
		connection = getConnection();
		contService.exportServicesList(connection);
		closeConnection(connection);
	}

	// ========= CHECK =============

	@Override
	public void createChek(Chek chek) {
		connection = getConnection();
		contChek.createChek(connection, chek);
		closeConnection(connection);
	}

	@Override
	public Chek getChekById(int idChek) {
		connection = getConnection();
		Chek chek = contChek.getChek(connection, idChek);
		closeConnection(connection);
		return chek;
	}

	@Override
	public List<Chek> getListChek() {
		connection = getConnection();
		List<Chek> list = contChek.getListChek(connection);
		closeConnection(connection);
		return list;
	}

	@Override
	public List<Chek> getListChekSortedByDateOutSettle() {
		connection = getConnection();
		List<Chek> list = contChek.getListChekSortedByDateOutSettle(connection);
		closeConnection(connection);
		return list;
	}

	@Override
	public int getSumChek(int idGuest) {
		connection = getConnection();
		int sum = contService.getServiceSumPrice(connection, idGuest);
		closeConnection(connection);
		return sum;
	}
}