package com.danco.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerChek;
import com.danco.api.backend.IControllerService;
import com.danco.api.backend.IControllerGuest;
import com.danco.api.backend.IControllerRoom;
import com.danco.api.backend.IProcessorAnnotation;
import com.danco.api.backend.IServiceAdmin;
import com.danco.api.backend.IUtilityForConnectDB;
import com.danco.api.backend.IUtilityForHibernate;
import com.danco.dependency.DependencyInjection;
import com.danco.model.entity.Chek;
import com.danco.model.entity.Guest;
import com.danco.model.entity.Room;
import com.danco.model.entity.Service;
import com.danco.model.entity.Status;

public class ServiceAdmin implements IServiceAdmin {

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
	@Injection
	private IUtilityForConnectDB source;
	@Injection
	private IUtilityForHibernate sessionUtil;
	
	private SessionFactory sessionFactory;

	private static final Logger LOGGER = LogManager
			.getLogger(ServiceAdmin.class);

	public ServiceAdmin() {
	}

	@Override
	public void initData() {
		annotation.processAnnotation(this);

		DependencyInjection.getInstance().getDI(contGuest, contRoom,
				contService, contChek, source);
		annotation.processAnnotation( sessionUtil, contGuest, contRoom, source);
		
		source.loadDriver();
		this.sessionFactory = sessionUtil.getSessionFactory();
		
	}

	// ==== GUEST =====

	@Override
	public void createGuest(Guest guest) {
//		Connection connection = source.getConnection();
//		contGuest.createGuest(connection, guest);
//		source.closeConnection(connection);
	}

	@Override
	public Guest getGuestById(int id) {
		Guest guest = null;
		//Connection connection = source.getConnection();
		Session session = sessionFactory.openSession();	
		guest = contGuest.getGuest(session, id);
		sessionFactory.close();
		return guest;
	}

	@Override
	public List<Guest> getListGuest() {
//		Session session = sessionFactory.openSession();	
//		List<Guest> list = contGuest.getListGuest(connection);
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public void deleteGuest(int idGuest) {
//		Session session = sessionFactory.openSession();	
//		contGuest.deleteGuest(connection, idGuest);
//		source.closeConnection(connection);
	}

	@Override
	public void settleGuestInRoom(int idGuest, int idRoom, String dateInSettle,
			String dateOutSettle) {
//		Session session = sessionFactory.openSession();	
//		try {
//			connection = source.getConnection();
//			connection.setAutoCommit(false);
//			synchronized (contRoom) {
//				Room room = contRoom.getRoom(connection, idRoom);
//				if (room.getStatus() != Status.FREE) {
//					System.out.println("Sorry, room not free!");
//				} else {
//					Guest guest = contGuest.getGuest(connection, idGuest);
//					Chek chek = new Chek(Date.valueOf(dateInSettle),
//							Date.valueOf(dateOutSettle), guest, room);
//					contChek.createChek(connection, chek);
//					Service service = new Service("Settle guest in room",
//							room.getPrice());
//					service.setChek(chek);
//					contService.createService(connection, service);
//					contRoom.changeRoomStatus(connection, room, Status.NOTFREE);
//					contRoom.updateRoom(connection, room);
//				}
//				connection.commit();
//			}
//		} catch (Exception e) {
//			if (connection != null) {
//				try {
//					System.err.print("Transaction is being rolled back");
//					connection.rollback();
//					source.closeConnection(connection);
//				} catch (SQLException ex) {
//					LOGGER.error(ex.getMessage());
//				}
//			}
//			LOGGER.error(e.getMessage());
//		}
	}

	@Override
	public void addServiceForGuest(int idGuest, int idService) {

//		Connection connection = null;
//		try {
//			connection = source.getConnection();
//			connection.setAutoCommit(false);
//			Chek chek = contChek.getChekForIdGuest(connection, idGuest);
//			Service service = contService.getService(connection, idService);
//			service.setChek(chek);
//			contService.updateService(connection, service);
//			connection.commit();
//		} catch (Exception e) {
//			if (connection != null) {
//				try {
//					System.err.print("Transaction is being rolled back");
//					connection.rollback();
//					source.closeConnection(connection);
//				} catch (SQLException ex) {
//					LOGGER.error(ex.getMessage());
//				}
//			}
//			LOGGER.error(e.getMessage());
//		}
	}

	@Override
	public void settleGuestOutRoom(int idGuest) {
//		Connection connection = null;
//		try {
//			connection = source.getConnection();
//			connection.setAutoCommit(false);
//			synchronized (contRoom) {
//				// 1. guest pay the check
//				Chek chek = contChek.getChekForIdGuest(connection, idGuest);
//				chek.setStatus(true);
//				contChek.updateChek(connection, chek);
//				// 2. change room status
//				contRoom.changeRoomStatus(connection, chek.getRoom(),
//						Status.FREE);
//				contRoom.updateRoom(connection, chek.getRoom());
//				connection.commit();
//			}
//		} catch (Exception e) {
//			if (connection != null) {
//				try {
//					System.err.print("Transaction is being rolled back");
//					connection.rollback();
//					source.closeConnection(connection);
//				} catch (SQLException ex) {
//					LOGGER.error(ex.getMessage());
//				}
//			}
//			LOGGER.error(e.getMessage());
//		}
	}

	@Override
	public Room getRoomInLiveGuest(int idGuest) {
		Room room = null;
//		Connection connection = null;
//		try {
//			connection = source.getConnection();
//			connection.setAutoCommit(false);
//			int idRoom = contChek.getRoomInLiveGuest(connection, idGuest);
//			room = contRoom.getRoom(connection, idRoom);
//			connection.commit();
//		} catch (Exception e) {
//			if (connection != null) {
//				try {
//					System.err.print("Transaction is being rolled back");
//					connection.rollback();
//					source.closeConnection(connection);
//				} catch (SQLException ex) {
//					LOGGER.error(ex.getMessage());
//				}
//			}
//			LOGGER.error(e.getMessage());
//		}
		return room;
	}

	@Override
	public List<Guest> printGuestsSortedByName() {
//		Connection connection = source.getConnection();
		List<Guest> list = null;//contGuest.getListGuestSorted(connection, "name");
//		source.closeConnection(connection);
		return list;
	}

	@Override
	public int getAmountGuest() {
//		Connection connection = source.getConnection();
//		int amount = contGuest.getAmountGuest(connection);
//		source.closeConnection(connection);
		return 0;
	}

	@Override
	public void importGuestsList() {
//		synchronized (contGuest) {
//			Connection connection = null;
//			try {
//				connection = source.getConnection();
//				connection.setAutoCommit(false);
//				List<Guest> listFromDB = contGuest.getListGuest(connection);
//				List<Guest> listImport = contGuest.importGuestsList();
//				for (int i = 0; i < listImport.size(); i++) {
//					for (int j = 0; j < listFromDB.size(); j++) {
//						if (listImport.get(i).getId() == listFromDB.get(j)
//								.getId()) {
//							contGuest
//									.updateGuest(connection, listImport.get(i));
//						}
//					}
//				}
//				connection.commit();
//			} catch (Exception e) {
//				if (connection != null) {
//					try {
//						System.err.print("Transaction is being rolled back");
//						connection.rollback();
//						source.closeConnection(connection);
//					} catch (SQLException ex) {
//						LOGGER.error(ex.getMessage());
//					}
//				}
//				LOGGER.error(e.getMessage());
//			}
	//	}
	}

	@Override
	public void exportGuestsList() {// write in CSV
//		Connection connection = source.getConnection();
//		contGuest.exportGuestsList(connection);
//		source.closeConnection(connection);
	}

	// ======= ROOM ======

	@Override
	public void createRoom(Room room) {
//		Connection connection = source.getConnection();
//		synchronized (contRoom) {
//			contRoom.createRoom(connection, room);
//		}
//		source.closeConnection(connection);

	}

	@Override
	public List<Room> getListRoom() {
//		Connection connection = source.getConnection();
//		List<Room> list = contRoom.getListRoom(connection);
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public List<Room> getListRoomSortedByContent() {
//		Connection connection = source.getConnection();
//		List<Room> list = contRoom.getListRoomSortedBy(connection, "",
//				"content");
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public List<Room> getListRoomSortedByNumber() {
//		Connection connection = source.getConnection();
//		List<Room> list = contRoom
//				.getListRoomSortedBy(connection, "", "number");
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public List<Room> getListRoomSortedByPrice() {
//		Connection connection = source.getConnection();
//		List<Room> list = contRoom.getListRoomSortedBy(connection, "", "price");
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public List<Room> getListRoomSortedByStars() {
//		Connection connection = source.getConnection();
//		List<Room> list = contRoom.getListRoomSortedBy(connection, "", "stars");
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public List<Room> getListRoomFree() {
//		Connection connection = source.getConnection();
//		List<Room> list = contRoom.getListRoomSortedBy(connection, "1", "id");
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public List<Room> getListRoomFreeSortedByContent() {
//		Connection connection = source.getConnection();
//		List<Room> list = contRoom.getListRoomSortedBy(connection, "1",
//				"content");
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public List<Room> getListRoomFreeSortedByNumber() {
//		Connection connection = source.getConnection();
//		List<Room> list = contRoom.getListRoomSortedBy(connection, "1",
//				"number");
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public List<Room> getListRoomFreeSortedByPrice() {
//		Connection connection = source.getConnection();
//		List<Room> list = contRoom
//				.getListRoomSortedBy(connection, "1", "price");
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public List<Room> getListRoomFreeSortedByStars() {
//		Connection connection = source.getConnection();
//		List<Room> list = contRoom
//				.getListRoomSortedBy(connection, "1", "stars");
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public int getAmountRoomFree() {
//		Connection connection = source.getConnection();
//		int amount = contRoom.getAmountRoomFree(connection);
//		source.closeConnection(connection);
		return 0;
	}

	@Override
	public void changeRoomStatus(int idRoom, String status) {
//		Connection connection = null;
//		try {
//			connection = source.getConnection();
//			connection.setAutoCommit(false);
//			synchronized (contRoom) {
//				Room room = contRoom.getRoom(connection, idRoom);
//				Status stat = Status.valueOf(status);
//				contRoom.changeRoomStatus(connection, room, stat);
//				contRoom.updateRoom(connection, room);
//			}
//			connection.commit();
//		} catch (Exception e) {
//			if (connection != null) {
//				try {
//					System.err.print("Transaction is being rolled back");
//					connection.rollback();
//					source.closeConnection(connection);
//				} catch (SQLException ex) {
//					LOGGER.error(ex.getMessage());
//				}
//			}
//			LOGGER.error(e.getMessage());
//		}
	}

	@Override
	public void changeRoomPrice(int idRoom, int price) {
//		Connection connection = null;
//		try {
//			connection = source.getConnection();
//			connection.setAutoCommit(false);
//			synchronized (contRoom) {
//				Room room = contRoom.getRoom(connection, idRoom);
//				contRoom.changeRoomPrice(connection, room, price);
//				contRoom.updateRoom(connection, room);
//			}
//			connection.commit();
//		} catch (Exception e) {
//			if (connection != null) {
//				try {
//					System.err.print("Transaction is being rolled back");
//					connection.rollback();
//					source.closeConnection(connection);
//				} catch (SQLException ex) {
//					LOGGER.error(ex.getMessage());
//				}
//			}
//			LOGGER.error(e.getMessage());
//		}
	}

	@Override
	public void cloneRoom(int idRoom) {
//		Connection connection = source.getConnection();
//		contRoom.cloneRoom(connection, idRoom);
//		source.closeConnection(connection);
	}

	@Override
	public void importRoomsList() {
//		Connection connection = null;
//		try {
//			connection = source.getConnection();
//			connection.setAutoCommit(false);
//			synchronized (contRoom) {
//				List<Room> listFromDB = contRoom.getListRoom(connection);
//				List<Room> listImport = contRoom.importRoomsList();
//
//				for (int i = 0; i < listImport.size(); i++) {
//					for (int j = 0; j < listFromDB.size(); j++) {
//						if (listImport.get(i).getId() == listFromDB.get(j)
//								.getId()) {
//							contRoom.updateRoom(connection, listImport.get(i));
//						}
//					}
//				}
//			}
//			connection.commit();
//		} catch (Exception e) {
//			if (connection != null) {
//				try {
//					System.err.print("Transaction is being rolled back");
//					connection.rollback();
//					source.closeConnection(connection);
//				} catch (SQLException ex) {
//					LOGGER.error(ex.getMessage());
//				}
//			}
//			LOGGER.error(e.getMessage());
//		}
	}

	@Override
	public void exportRoomsList() {// write in CSV
//		Connection connection = source.getConnection();
//		contRoom.exportRoomsList(connection);
//		source.closeConnection(connection);
	}

	// ========= SERVICE ===============

	@Override
	public void createService(Service service) {
//		Connection connection = source.getConnection();
//		contService.createService(connection, service);
//		source.closeConnection(connection);
	}

	@Override
	public Service getServiceById(int idService) {
//		Connection connection = source.getConnection();
//		Service service = contService.getService(connection, idService);
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public List<Service> getListService() {
//		Connection connection = source.getConnection();
//		List<Service> list = contService.getListService(connection);
//		source.closeConnection(connection);
		return null;

	}

	@Override
	public List<Service> printServicesSortedByName() {
//		Connection connection = source.getConnection();
//		List<Service> list = contService.getServiceSortedBy(connection,
//				"nameServcie");
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public List<Service> printServicesSortedByPrice() {
//		Connection connection = source.getConnection();
//		List<Service> list = contService.getServiceSortedBy(connection,
//				"priceService");
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public List<Service> getGuestThemServices(int idGuest) {
//		Connection connection = source.getConnection();
//		List<Service> list = contService.getGuestThemServices(connection,
//				idGuest);
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public void changeServicePrice(int idService, int price) {
//		Connection connection = source.getConnection();
//		contService.changePrice(connection, idService, price);
//		source.closeConnection(connection);
	}

	@Override
	public void importServicesList() {
//		Connection connection = null;
//		try {
//			connection = source.getConnection();
//			connection.setAutoCommit(false);
//			synchronized (contService) {
//				List<Service> listFromDB = contService
//						.getListService(connection);
//				List<Service> listImport = contService.importServicesList();
//
//				for (int i = 0; i < listImport.size(); i++) {
//					for (int j = 0; j < listFromDB.size(); j++) {
//						if (listImport.get(i).getId() == listFromDB.get(j)
//								.getId()) {
//							contService.updateService(connection,
//									listImport.get(i));
//						}
//					}
//				}
//			}
//			connection.commit();
//		} catch (Exception e) {
//			if (connection != null) {
//				try {
//					System.err.print("Transaction is being rolled back");
//					connection.rollback();
//					source.closeConnection(connection);
//				} catch (SQLException ex) {
//					LOGGER.error(ex.getMessage());
//				}
//			}
//			LOGGER.error(e.getMessage());
//		}
	}

	@Override
	public void exportServicesList() {// write in CSV
//		Connection connection = source.getConnection();
//		contService.exportServicesList(connection);
//		source.closeConnection(connection);
	}

	// ========= CHECK =============

	@Override
	public void createChek(Chek chek) {
//		Connection connection = source.getConnection();
//		contChek.createChek(connection, chek);
//		source.closeConnection(connection);
	}

	@Override
	public Chek getChekById(int idChek) {
//		Connection connection = source.getConnection();
//		Chek chek = contChek.getChek(connection, idChek);
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public List<Chek> getListChek() {
//		Connection connection = source.getConnection();
//		List<Chek> list = contChek.getListChek(connection);
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public List<Chek> getListChekSortedByDateOutSettle() {
//		Connection connection = source.getConnection();
//		List<Chek> list = contChek.getListChekSortedBy(connection,
//				"date_out_settle");
//		source.closeConnection(connection);
		return null;
	}

	@Override
	public int getSumChek(int idGuest) {
//		Connection connection = source.getConnection();
//		int sum = contService.getServiceSumPrice(connection, idGuest);
//		source.closeConnection(connection);
		return 0;
	}
}