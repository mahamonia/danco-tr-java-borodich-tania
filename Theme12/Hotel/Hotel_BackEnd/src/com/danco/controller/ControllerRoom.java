package com.danco.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.danco.annotation.ConfigProperty;
import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerRoom;
import com.danco.api.backend.IParseUtilityCSVForRoom;
import com.danco.api.dao.IRoomDao;
import com.danco.model.entity.Room;
import com.danco.model.entity.Status;

public class ControllerRoom implements IControllerRoom {

	private static Logger LOGGER = LogManager.getLogger(ControllerRoom.class);

	@ConfigProperty(configName = "property.properties", propertyName = "status", type = "boolean")
	private boolean statusRoom;
	private final String MESSAGE_1 = "Prohibited privacy settings";
	@Injection
	private IParseUtilityCSVForRoom utility;
	@Injection
	private IRoomDao roomDao;

	public ControllerRoom() {
	}

	@Override
	public void createRoom(Session session, Room room) {
		try {
			roomDao.create(session, room);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void updateRoom(Session session, Room room) {
		try {
			roomDao.update(session, room);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void deleteRoom(Session session, int idRoom) {
		try {
			roomDao.delete(session, roomDao.getById(session, idRoom));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public Room getRoom(Session session, int idRoom) {
		Room room = null;
		try {
			room = roomDao.getById(session, idRoom);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return room;
	}

	@Override
	public List<Room> getListRoom(Session session, String status, String param) {
		List<Room> list = null;
		try {
			list = roomDao.getList(session, status, param);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}

	@Override
	public int getAmountRoomFree(Session session) {
		int amount = 0;
		try {
			amount = roomDao.getList(session, "FREE", "id").size()+1;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return amount;
	}

	@Override
	public void changeRoomStatus(Session session, Room room, Status status) {
		try {
			if (statusRoom == true) {
				room.setStatus(status);
			} else {
				System.out.println(MESSAGE_1);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void changeRoomPrice(Session session, Room room, int price) {

		try {
			room.setPrice(price);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public Room cloneRoom(Session session, int idRoom) {
		Room clon = null;
		try {
			clon = roomDao.getById(session, idRoom).clone();
			clon.setId(0);
			roomDao.create(session, clon);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return clon;
	}

	@Override
	public List<Room> importRoomsList() {
		return utility.importData();
	}

	@Override
	public void exportRoomsList(Session session) {
		try {
			utility.exportData(roomDao.getList(session, "", "id"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
