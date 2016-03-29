package com.danco.controller;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	public void createRoom(Connection connect, Room room) {
		try {
			roomDao.create(connect, room);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void updateRoom(Connection connect, Room room) {
		try {
			roomDao.update(connect, room);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void deleteRoom(Connection connect, int idRoom) {
		try {
			roomDao.delete(connect, idRoom);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public Room getRoom(Connection connect, int idRoom) {
		Room room = null;
		try {
			room = roomDao.getById(connect, idRoom);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return room;
	}

	@Override
	public List<Room> getListRoom(Connection connect) {
		List<Room> list = null;
		try {
			list = roomDao.getList(connect);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}

	@Override
	public List<Room> getListRoomSortedBy(Connection connect, String status,
			String param) {
		List<Room> list = null;
		try {
			list = roomDao.getListRoomSorted(connect, status, param);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}

	@Override
	public int getAmountRoomFree(Connection connect) {
		int amount = 0;
		try {
			amount = roomDao.getListRoomSorted(connect, "1", "id").size()+1;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return amount;
	}

	@Override
	public void changeRoomStatus(Connection connect, Room room, Status status) {
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
	public void changeRoomPrice(Connection connect, Room room, int price) {

		try {
			room.setPrice(price);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public Room cloneRoom(Connection connect, int idRoom) {
		Room clon = null;
		try {
			clon = roomDao.getById(connect, idRoom).clone();
			clon.setId(0);
			roomDao.create(connect, clon);
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
	public void exportRoomsList(Connection connect) {
		try {
			utility.exportData(roomDao.getList(connect));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
