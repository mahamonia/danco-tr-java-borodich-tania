package com.danco.controller;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.ConfigProperty;
import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerRoom;
import com.danco.api.backend.IParseUtilityCSVForRoom;
import com.danco.model.dao.RoomDao;
import com.danco.model.entity.Room;
import com.danco.model.entity.Status;

public class ControllerRoom implements IControllerRoom {

	private static Logger LOGGER = LogManager.getLogger(ControllerRoom.class);

	@ConfigProperty(configName = "property.properties", propertyName = "status", type = "boolean")
	private boolean statusRoom;

	private final String MESSAGE_1 = "Prohibited privacy settings";

	@Injection
	private IParseUtilityCSVForRoom utility;

	private RoomDao roomDao;

	public ControllerRoom(RoomDao roomDao) {
		this.roomDao = roomDao;
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
	public void updateRoom(Connection connect, int idRoom) {
		try {
			roomDao.update(connect, idRoom);
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
		return roomDao.getById(connect, idRoom);
	}

	@Override
	public List<Room> getListRoom(Connection connect) {
		return roomDao.getList(connect);
	}

	@Override
	public List<Room> getListRoomSortedByContetn(Connection connect) {
		return roomDao.getListRoomSortedByContetn(connect);
	}

	@Override
	public List<Room> getListRoomSortedByNumber(Connection connect) {
		return roomDao.getListRoomSortedByNumber(connect);
	}

	@Override
	public List<Room> getListRoomSortedByPrice(Connection connect) {
		return roomDao.getListRoomSortedByPrice(connect);
	}

	@Override
	public List<Room> getListRoomSortedByStars(Connection connect) {
		return roomDao.getListRoomSortedByStars(connect);
	}

	@Override
	public List<Room> getListRoomFree(Connection connect) {
		return roomDao.getListFreeRoom(connect);
	}

	@Override
	public List<Room> getListRoomFreeSortedByContetn(Connection connect) {
		return roomDao.getListRoomFreeSortedByContetn(connect);
	}

	@Override
	public List<Room> getListRoomFreeSortedByNumber(Connection connect) {
		return roomDao.getListRoomFreeSortedByNumber(connect);
	}

	@Override
	public List<Room> getListRoomFreeSortedByPrice(Connection connect) {
		return roomDao.getListRoomFreeSortedByPrice(connect);
	}

	@Override
	public List<Room> getListRoomFreeSortedByStars(Connection connect) {
		return roomDao.getListRoomFreeSortedByStars(connect);
	}

	@Override
	public int getAmountRoomFree(Connection connect) {
		return roomDao.getListFreeRoom(connect).size();
	}

	@Override
	public void changeRoomStatus(Connection connect, int idRoom, Status status) {
		try {
			if (statusRoom == true) {
				roomDao.getById(connect, idRoom).setStatus(status);
			} else {
				System.out.println(MESSAGE_1);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void changeRoomPrice(Connection connect, int idRoom, int price) {

		try {
			roomDao.getById(connect, idRoom).setPrice(price);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public Room cloneRoom(Connection connect, int idRoom) {
		Room clon = null;
		try {

			clon = roomDao.getById(connect, idRoom).clone();
			int idForNewRoom = roomDao.getIdForNewModel(connect);
			clon.setId(idForNewRoom);

		} catch (CloneNotSupportedException e) {
			LOGGER.error(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return clon;
	}

	@Override
	public List<Room> importRoomsList(Connection connect) {
		return utility.importData();
	}

	@Override
	public void exportRoomsList(Connection connect) {
		utility.exportData(roomDao.getList(connect));
	}
}



// public List<Guest> printRoomThemGuests() {
//
// List<Guest> newGuestList = new ArrayList<>();
//
// // try {
// //
// // //List<Guest> guestList = room.getGuestList();
// // for (int i = guestList.size(); i > guestList.size() - 3; i--) {
// // newGuestList.add(guestList.get(i));
// // }
// // } catch (Exception e) {
// // LOGGER.error(e.getMessage());
// // }
// return newGuestList;
// }


