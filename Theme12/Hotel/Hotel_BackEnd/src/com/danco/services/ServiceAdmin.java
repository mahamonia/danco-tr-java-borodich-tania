package com.danco.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

	private static final String SETTING_IN_ROOM = "Settle guest in room";

	public ServiceAdmin() {
	}

	@Override
	public void initData() {
		annotation.processAnnotation(this);

		DependencyInjection.getInstance().getDI(contGuest, contRoom,
				contService, contChek, source);
		annotation.processAnnotation(sessionUtil, contGuest, contRoom, source);

		source.loadDriver();
		this.sessionFactory = sessionUtil.getSessionFactory();

	}

	// ==== GUEST =====

	@Override
	public String createGuest(Guest guest) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		contGuest.createGuest(session, guest);
		session.getTransaction().commit();
		session.close();
		return "Guest successfully created ";
	}

	@Override
	public Guest getGuestById(int id) {
		Guest guest = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		guest = contGuest.getGuest(session, id);
		session.getTransaction().commit();
		session.close();
		return guest;
	}

	@Override
	public List<Guest> getListGuest() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Guest> list = contGuest.getListGuest(session, "id");
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public String settleGuestInRoom(int idGuest, int idRoom,
			String dateInSettle, String dateOutSettle) {
		String message = new String();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		synchronized (contRoom) {
			Room room = contRoom.getRoom(session, idRoom);
			if (room.getStatus() != Status.FREE) {
				message = "Sorry, room not free!";
			} else {
				contRoom.changeRoomStatus(session, room, Status.NOTFREE);
				contRoom.updateRoom(session, room);
				Guest guest = contGuest.getGuest(session, idGuest);
				Service service = new Service(SETTING_IN_ROOM, room.getPrice());

				// change date
				SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
				
				Chek chek = null;
				try {
					chek = new Chek(format.parse(dateInSettle),
							format.parse(dateOutSettle), guest, room);
				} catch (ParseException e) {
					message = e.getMessage();
				}

				service.setChek(chek);
				contService.createService(session, service);
				contChek.createChek(session, chek);
				message = "Settle was successful";
			}
		}
		session.getTransaction().commit();
		session.close();
		return message;
	}

	@Override
	public String addServiceForGuest(int idGuest, int idService) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Chek chek = contChek.getChekForIdGuest(session, idGuest);
		Service service = contService.getService(session, idService);
		service.setChek(chek);
		contService.updateService(session, service);

		session.getTransaction().commit();
		session.close();
		return "Added service";
	}

	@Override
	public String settleGuestOutRoom(int idGuest) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		synchronized (contRoom) {
			// 1. guest pay the check
			Chek chek = contChek.getChekForIdGuest(session, idGuest);
			chek.setStatus(true);
			contChek.updateChek(session, chek);
			// 2. change room status
			contRoom.changeRoomStatus(session, chek.getRoom(), Status.FREE);
			contRoom.updateRoom(session, chek.getRoom());
		}
		session.getTransaction().commit();
		session.close();
		return null;
	}

	@Override
	public Room getRoomInLiveGuest(int idGuest) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Room room = null;

		int idRoom = contChek.getRoomInLiveGuest(session, idGuest);
		room = contRoom.getRoom(session, idRoom);
		session.getTransaction().commit();
		session.close();
		return room;
	}

	@Override
	public List<Guest> printGuestsSortedByName() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Guest> list = contGuest.getListGuest(session, "name");
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public int getAmountGuest() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int amount = contGuest.getAmountGuest(session);
		session.getTransaction().commit();
		session.close();
		return amount;
	}

	@Override
	public String importGuestsList() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		synchronized (contGuest) {
			List<Guest> listFromDB = contGuest.getListGuest(session, "id");
			List<Guest> listImport = contGuest.importGuestsList();
			for (int i = 0; i < listImport.size(); i++) {
				for (int j = 0; j < listFromDB.size(); j++) {
					if (listImport.get(i).getId() == listFromDB.get(j).getId()) {
						contGuest.updateGuest(session, listImport.get(i));
					}
				}
			}
		}
		session.getTransaction().commit();
		session.close();
		return "Data imported";
	}

	@Override
	public String exportGuestsList() {// write in CSV
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		contGuest.exportGuestsList(session);
		session.getTransaction().commit();
		session.close();
		return "Data exported";
	}

	// ======= ROOM ======

	@Override
	public String createRoom(Room room) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		synchronized (contRoom) {
			contRoom.createRoom(session, room);
		}
		session.getTransaction().commit();
		session.close();
		return "Room successfully created";

	}

	@Override
	public List<Room> getListRoom() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Room> list = contRoom.getListRoom(session, "", "id");
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<Room> getListRoomSortedByContent() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Room> list = contRoom.getListRoom(session, "", "content");
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<Room> getListRoomSortedByNumber() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Room> list = contRoom.getListRoom(session, "", "number");
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<Room> getListRoomSortedByPrice() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Room> list = contRoom.getListRoom(session, "", "price");
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<Room> getListRoomSortedByStars() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Room> list = contRoom.getListRoom(session, "", "stars");
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<Room> getListRoomFree() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Room> list = contRoom.getListRoom(session, "1", "id");
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<Room> getListRoomFreeSortedByContent() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Room> list = contRoom.getListRoom(session, "1", "content");
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<Room> getListRoomFreeSortedByNumber() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Room> list = contRoom.getListRoom(session, "1", "number");
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<Room> getListRoomFreeSortedByPrice() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Room> list = contRoom.getListRoom(session, "1", "price");
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<Room> getListRoomFreeSortedByStars() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Room> list = contRoom.getListRoom(session, "1", "stars");
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public int getAmountRoomFree() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int amount = contRoom.getAmountRoomFree(session);
		session.getTransaction().commit();
		session.close();
		return amount;
	}

	@Override
	public String changeRoomStatus(int idRoom, String status) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		synchronized (contRoom) {
			Room room = contRoom.getRoom(session, idRoom);
			Status stat = Status.valueOf(status);
			contRoom.changeRoomStatus(session, room, stat);
			contRoom.updateRoom(session, room);
		}
		session.getTransaction().commit();
		session.close();
		return "Status changed";
	}

	@Override
	public String changeRoomPrice(int idRoom, int price) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		synchronized (contRoom) {
			Room room = contRoom.getRoom(session, idRoom);
			contRoom.changeRoomPrice(session, room, price);
			contRoom.updateRoom(session, room);
		}

		session.getTransaction().commit();
		session.close();
		return "Price changed";
	}

	@Override
	public String cloneRoom(int idRoom) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.getTransaction().commit();
		session.close();
		return "Cloning was successful";
	}

	@Override
	public String importRoomsList() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		synchronized (contRoom) {
			List<Room> listFromDB = contRoom.getListRoom(session, "", "id");
			List<Room> listImport = contRoom.importRoomsList();

			for (int i = 0; i < listImport.size(); i++) {
				for (int j = 0; j < listFromDB.size(); j++) {
					if (listImport.get(i).getId() == listFromDB.get(j).getId()) {
						contRoom.updateRoom(session, listImport.get(i));
					}
				}
			}
		}
		session.getTransaction().commit();
		session.close();
		return "Data imported";
	}

	@Override
	public String exportRoomsList() {// write in CSV
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		contRoom.exportRoomsList(session);
		session.getTransaction().commit();
		session.close();
		return "Data exported";
	}

	// ========= SERVICE ===============

	@Override
	public String createService(Service service) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		contService.createService(session, service);
		session.getTransaction().commit();
		session.close();
		return "Service successfully created";
	}

	@Override
	public Service getServiceById(int idService) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Service service = contService.getService(session, idService);
		session.getTransaction().commit();
		session.close();
		return service;
	}

	@Override
	public List<Service> getListService() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Service> list = contService.getListService(session, "id");
		session.getTransaction().commit();
		session.close();
		return list;

	}

	@Override
	public List<Service> printServicesSortedByName() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Service> list = contService.getListService(session, "nameServcie");
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<Service> printServicesSortedByPrice() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Service> list = contService
				.getListService(session, "priceService");
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<Service> getGuestThemServices(int idGuest) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Service> list = contService.getGuestThemServices(session, idGuest);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public String changeServicePrice(int idService, int price) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		contService.changePrice(session, idService, price);
		session.getTransaction().commit();
		session.close();
		return "Price changed";
	}

	@Override
	public String importServicesList() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		synchronized (contService) {
			List<Service> listFromDB = contService
					.getListService(session, "id");
			List<Service> listImport = contService.importServicesList();

			for (int i = 0; i < listImport.size(); i++) {
				for (int j = 0; j < listFromDB.size(); j++) {
					if (listImport.get(i).getId() == listFromDB.get(j).getId()) {
						contService.updateService(session, listImport.get(i));
					}
				}
			}
		}

		session.getTransaction().commit();
		session.close();
		return "Data imported";
	}

	@Override
	public String exportServicesList() {// write in CSV
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		contService.exportServicesList(session);
		session.getTransaction().commit();
		session.close();
		return "Data exported";
	}

	// ========= CHECK =============

	@Override
	public String createChek(Chek chek) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		contChek.createChek(session, chek);
		session.getTransaction().commit();
		session.close();
		return "Chek successfully created";
	}

	@Override
	public Chek getChekById(int idChek) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Chek chek = contChek.getChek(session, idChek);
		session.getTransaction().commit();
		session.close();
		return chek;
	}

	@Override
	public List<Chek> getListChek() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Chek> list = contChek.getListChek(session, "id");
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public List<Chek> getListChekSortedByDateOutSettle() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Chek> list = contChek.getListChek(session, "date_out_settle");
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public int getSumChek(int idGuest) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int sum = contService.getServiceSumPrice(session, idGuest);
		session.getTransaction().commit();
		session.close();
		return sum;
	}
}