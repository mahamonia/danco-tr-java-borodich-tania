package com.danco.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.ConfigProperty;
import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerRoom;
import com.danco.api.backend.IParseUtilityCSVForRoom;
import com.danco.model.dao.DataSource;
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
	private DataSource source;

	public ControllerRoom(DataSource source, RoomDao roomDao) {
		this.source = source;
		this.roomDao = roomDao;
	}

	@Override
	public void createRoom(Room room) {
		try {
			roomDao.create(source, room);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void updateRoom(int idRoom) {
		try {
			roomDao.update(source, idRoom);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void deleteRoom(int idRoom) {
		try {
			roomDao.delete(source, idRoom);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public Room getRoom(int idRoom) {
		return roomDao.getById(source, idRoom);
	}

	@Override
	public List<Room> getListRoom() {
		return roomDao.getList(source);
	}

	@Override
	public List<Room> getListRoomSortedByContetn() {
		return roomDao.getListRoomSortedByContetn(source);
	}

	@Override
	public List<Room> getListRoomSortedByNumber() {
		return roomDao.getListRoomSortedByNumber(source);
	}

	@Override
	public List<Room> getListRoomSortedByPrice() {
		return roomDao.getListRoomSortedByPrice(source);
	}

	@Override
	public List<Room> getListRoomSortedByStars() {
		return roomDao.getListRoomSortedByStars(source);
	}

	@Override
	public List<Room> getListRoomFree() {
		return roomDao.getListFreeRoom(source);
	}

	@Override
	public List<Room> getListRoomFreeSortedByContetn() {
		return roomDao.getListRoomFreeSortedByContetn(source);
	}

	@Override
	public List<Room> getListRoomFreeSortedByNumber() {
		return roomDao.getListRoomFreeSortedByNumber(source);
	}

	@Override
	public List<Room> getListRoomFreeSortedByPrice() {
		return roomDao.getListRoomFreeSortedByPrice(source);
	}

	@Override
	public List<Room> getListRoomFreeSortedByStars() {
		return roomDao.getListRoomFreeSortedByStars(source);
	}

	@Override
	public int getAmountRoomFree() {
		return roomDao.getListFreeRoom(source).size();
	}

	@Override
	public void changeRoomStatus(int idRoom, Status status) {
		try {
			if (statusRoom == true) {
				roomDao.getById(source, idRoom).setStatus(status);
			} else {
				System.out.println(MESSAGE_1);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void changeRoomPrice(int idRoom, int price) {

		try {
			roomDao.getById(source, idRoom).setPrice(price);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public Room cloneRoom(int idRoom) {
		Room clon = null;
		try {

			clon = roomDao.getById(source, idRoom).clone();
			int idForNewRoom = roomDao.getIdForNewModel(source);
			clon.setId(idForNewRoom);

		} catch (CloneNotSupportedException e) {
			LOGGER.error(e.getMessage());
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
	public void exportRoomsList() {
		utility.exportData(roomDao.getList(source));
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


