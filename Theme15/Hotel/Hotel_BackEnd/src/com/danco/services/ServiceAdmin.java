package com.danco.services;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerAudit;
import com.danco.api.backend.IControllerChek;
import com.danco.api.backend.IControllerService;
import com.danco.api.backend.IControllerGuest;
import com.danco.api.backend.IControllerRoom;
import com.danco.api.backend.IControllerUser;
import com.danco.api.backend.IProcessorAnnotation;
import com.danco.api.backend.IServiceAdmin;
import com.danco.api.backend.IUtilityForHibernate;
import com.danco.dependency.DependencyInjection;
import com.danco.model.entity.Audit;
import com.danco.model.entity.Chek;
import com.danco.model.entity.Guest;
import com.danco.model.entity.Room;
import com.danco.model.entity.Service;
import com.danco.model.entity.Status;
import com.danco.model.entity.User;

public class ServiceAdmin implements IServiceAdmin {

	private static final Logger LOGGER = LogManager
			.getLogger(ServiceAdmin.class);

	@Injection
	private IControllerRoom contRoom;
	@Injection
	private IControllerGuest contGuest;
	@Injection
	private IControllerChek contChek;
	@Injection
	private IControllerService contService;
	@Injection
	private IControllerUser contUser;
	@Injection
	private IControllerAudit contAudit;
	@Injection
	private IProcessorAnnotation annotation;
	@Injection
	private IUtilityForHibernate sessionUtil;

	private SessionFactory sessionFactory;

	private static final String SETTING_IN_ROOM = "Settle guest in room";
	private Boolean isPerformed = true;

	public ServiceAdmin() {
	}

	@Override
	public void initData() {
		annotation.processAnnotation(this);
		DependencyInjection.getInstance().getDI(contGuest, contRoom,
				contService, contChek, contUser, contAudit);
		annotation.processAnnotation(sessionUtil, contGuest, contRoom);

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.sessionFactory = sessionUtil.getSessionFactory();

	}

	@Override
	public void saveData() {
		System.out.println("Client exit");
		sessionFactory.close();
	}

	// ==== GUEST =====

	@Override
	public Boolean createGuest(Guest guest) {

		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			contGuest.createGuest(session, guest);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			isPerformed = false;
		} finally {
			session.close();
		}
		return isPerformed;
	}

	@Override
	public Guest getGuestById(int id) {
		Guest guest = null;
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			guest = contGuest.getGuest(session, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return guest;
	}

	@Override
	public List<Guest> getListGuest() {
		Session session = sessionFactory.openSession();
		List<Guest> list = null;
		try {
			session.beginTransaction();
			list = contGuest.getListGuest(session, "id");
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Boolean settleGuestInRoom(int idGuest, int idRoom,
			String dateInSettle, String dateOutSettle) {

		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			synchronized (contRoom) {
				Room room = contRoom.getRoom(session, idRoom);
				if (room.getStatus() != Status.FREE) {
					isPerformed = false;
				} else {
					contRoom.changeRoomStatus(session, room, Status.NOTFREE);
					contRoom.updateRoom(session, room);
					Guest guest = contGuest.getGuest(session, idGuest);
					Service service = new Service(SETTING_IN_ROOM,
							room.getPrice());

					// change date
					SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

					Chek chek = null;
					chek = new Chek(format.parse(dateInSettle),
							format.parse(dateOutSettle), guest, room);
					service.setChek(chek);
					contService.createService(session, service);
					contChek.createChek(session, chek);
				}
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			isPerformed = false;
		} finally {
			session.close();
		}
		return isPerformed;
	}

	@Override
	public Boolean addServiceForGuest(int idGuest, int idService) {

		Session session = sessionFactory.openSession();
		List<Chek> checkList = null;
		Chek chek = null;
		try {
			session.beginTransaction();

			checkList = contChek.getChekListForIdGuest(session, idGuest);
			for (Chek chekCurrent : checkList) {
				if (!chekCurrent.isStatus()) {
					chek = chekCurrent;
				}
			}
			Service service = contService.getService(session, idService);
			service.setChek(chek);
			contService.updateService(session, service);

			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			isPerformed = false;
		} finally {
			session.close();
		}
		return isPerformed;
	}

	@Override
	public Boolean settleGuestOutRoom(int idGuest) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			synchronized (contRoom) {
				// 1. guest pay the check
				List<Chek> checkList = contChek.getChekListForIdGuest(session,
						idGuest);
				Chek chek = null;
				for (Chek chekCurrent : checkList) {
					if (!chekCurrent.isStatus()) {
						chek = chekCurrent;
					}
				}
				chek.setStatus(true);
				contChek.updateChek(session, chek);
				// 2. change room status
				contRoom.changeRoomStatus(session, chek.getRoom(), Status.FREE);
				contRoom.updateRoom(session, chek.getRoom());
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			isPerformed = false;
		} finally {
			session.close();
		}
		return isPerformed;
	}

	@Override
	public Room getRoomInLiveGuest(int idGuest) {
		Session session = sessionFactory.openSession();
		Room room = null;
		try {
			session.beginTransaction();
			int idRoom = contChek.getRoomInLiveGuest(session, idGuest);
			room = contRoom.getRoom(session, idRoom);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return room;
	}

	@Override
	public List<Guest> printGuestsSortedByName() {
		Session session = sessionFactory.openSession();
		List<Guest> list = null;
		try {
			session.beginTransaction();
			list = contGuest.getListGuest(session, "name");
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public int getAmountGuest() {
		Session session = sessionFactory.openSession();
		int amount = 0;
		try {
			session.beginTransaction();
			amount = contGuest.getAmountGuest(session);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return amount;
	}

	@Override
	public Boolean importGuestsList(String nameFile) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			synchronized (contGuest) {
				List<Guest> listFromDB = contGuest.getListGuest(session, "id");
				List<Guest> listImport = contGuest.importGuestsList(nameFile);
				for (int i = 0; i < listImport.size(); i++) {
					for (int j = 0; j < listFromDB.size(); j++) {
						if (listImport.get(i).getId() == listFromDB.get(j)
								.getId()) {
							contGuest.updateGuest(session, listImport.get(i));
						}
					}
				}
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			isPerformed = false;
		} finally {
			session.close();
		}
		return isPerformed;
	}

	@Override
	public Boolean exportGuestsList(String nameFile) {// write in CSV
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			contGuest.exportGuestsList(session, nameFile);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			isPerformed = false;
		} finally {
			session.close();
		}
		return isPerformed;
	}

	// ======= ROOM ======

	@Override
	public Boolean createRoom(Room room) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			synchronized (contRoom) {
				contRoom.createRoom(session, room);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			isPerformed = false;
		} finally {
			session.close();
		}
		return isPerformed;

	}

	@Override
	public List<Room> getListRoom(String status, String param) {
		Session session = sessionFactory.openSession();
		List<Room> list = null;
		try {
			session.beginTransaction();
			list = contRoom.getListRoom(session, status, param);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public int getAmountRoomFree() {
		Session session = sessionFactory.openSession();
		int amount = 0;
		try {
			session.beginTransaction();
			amount = contRoom.getAmountRoomFree(session);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return amount;
	}

	@Override
	public Boolean changeRoomStatus(int idRoom, String status) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			synchronized (contRoom) {
				Room room = contRoom.getRoom(session, idRoom);
				Status stat = Status.valueOf(status);
				contRoom.changeRoomStatus(session, room, stat);
				contRoom.updateRoom(session, room);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			isPerformed = false;
		} finally {
			session.close();
		}
		return isPerformed;
	}

	@Override
	public Boolean changeRoomPrice(int idRoom, int price) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			synchronized (contRoom) {
				Room room = contRoom.getRoom(session, idRoom);
				contRoom.changeRoomPrice(session, room, price);
				contRoom.updateRoom(session, room);
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			isPerformed = false;
		} finally {
			session.close();
		}
		return isPerformed;
	}

	@Override
	public Boolean cloneRoom(int idRoom) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			isPerformed = false;
		} finally {
			session.close();
		}
		return isPerformed;
	}

	@Override
	public Boolean importRoomsList(String nameFile) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			synchronized (contRoom) {
				List<Room> listFromDB = contRoom.getListRoom(session, "", "id");
				List<Room> listImport = contRoom.importRoomsList(nameFile);

				for (int i = 0; i < listImport.size(); i++) {
					for (int j = 0; j < listFromDB.size(); j++) {
						if (listImport.get(i).getId() == listFromDB.get(j)
								.getId()) {
							contRoom.updateRoom(session, listImport.get(i));
						}
					}
				}
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			isPerformed = false;
		} finally {
			session.close();
		}
		return isPerformed;
	}

	@Override
	public Boolean exportRoomsList(String nameFile) {// write in CSV
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			contRoom.exportRoomsList(session, nameFile);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			isPerformed = false;
		} finally {
			session.close();
		}
		return isPerformed;
	}

	// ========= SERVICE ===============

	@Override
	public Boolean createService(Service service) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			contService.createService(session, service);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			isPerformed = false;
		} finally {
			session.close();
		}
		return isPerformed;
	}

	@Override
	public Service getServiceById(int idService) {
		Session session = sessionFactory.openSession();
		Service service = null;
		try {
			session.beginTransaction();
			service = contService.getService(session, idService);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return service;
	}

	@Override
	public List<Service> getListService(String param) {
		Session session = sessionFactory.openSession();
		List<Service> list = null;
		try {
			session.beginTransaction();
			list = contService.getListService(session, param);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return list;

	}

	@Override
	public List<Service> getGuestThemServices(int idGuest) {
		Session session = sessionFactory.openSession();
		List<Service> list = null;
		try {
			session.beginTransaction();
			list = contService.getGuestThemServices(session, idGuest);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Boolean changeServicePrice(int idService, int price) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			contService.changePrice(session, idService, price);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			isPerformed = false;
		} finally {
			session.close();
		}
		return isPerformed;
	}

	@Override
	public Boolean importServicesList(String nameFile) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			synchronized (contService) {
				List<Service> listFromDB = contService.getListService(session,
						"id");
				List<Service> listImport = contService
						.importServicesList(nameFile);

				for (int i = 0; i < listImport.size(); i++) {
					for (int j = 0; j < listFromDB.size(); j++) {
						if (listImport.get(i).getId() == listFromDB.get(j)
								.getId()) {
							contService.updateService(session,
									listImport.get(i));
						}
					}
				}
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			isPerformed = false;
		} finally {
			session.close();
		}
		return isPerformed;
	}

	@Override
	public Boolean exportServicesList(String nameFile) {// write in CSV
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			contService.exportServicesList(session, nameFile);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			isPerformed = false;
		} finally {
			session.close();
		}
		return isPerformed;
	}

	// ========= CHECK =============

	@Override
	public Boolean createChek(Chek chek) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			contChek.createChek(session, chek);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			isPerformed = false;
		} finally {
			session.close();
		}
		return isPerformed;
	}

	@Override
	public Chek getChekById(int idChek) {
		Session session = sessionFactory.openSession();
		Chek chek = null;
		try {
			session.beginTransaction();
			chek = contChek.getChek(session, idChek);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return chek;
	}

	@Override
	public List<Chek> getListChek() {
		Session session = sessionFactory.openSession();
		List<Chek> list = null;
		try {
			session.beginTransaction();
			list = contChek.getListChek(session, "id");
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Chek> getChekListForIdGuest(int idGuest) {
		Session session = sessionFactory.openSession();
		List<Chek> list = null;
		try {
			session.beginTransaction();
			list = contChek.getChekListForIdGuest(session, idGuest);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Chek> getListChekSortedByDateOutSettle() {
		Session session = sessionFactory.openSession();
		List<Chek> list = null;
		try {
			session.beginTransaction();
			list = contChek.getListChek(session, "date_out_settle");
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public int getSumChek(int idGuest) {
		Session session = sessionFactory.openSession();
		int sum = 0;
		try {
			session.beginTransaction();
			sum = contService.getServiceSumPrice(session, idGuest);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return sum;
	}

	@Override
	public User getUserByLogin(String login) {
		Session session = sessionFactory.openSession();
		User user = null;
		try {
			session.beginTransaction();
			user = contUser.getUserByLogin(session, login);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public void save(Audit audit) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			contAudit.save(session, audit);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
	}

}