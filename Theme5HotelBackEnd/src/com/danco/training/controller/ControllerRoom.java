package com.danco.training.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.comparator.Comparator;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Room;
import com.danco.training.entity.Status;

public class ControllerRoom implements IPrintRoom {

	private static Logger logger = LogManager.getLogger(ControllerRoom.class);

	private List<Room> roomsList;

	public ControllerRoom(List<Room> roomsList) {
		this.roomsList = roomsList;
	}

	public void createRoom(Room room) {

		roomsList.add(room);
	}

	public void deleteRoom(Room room) {
		int i = getIndexRoom(room);
		if (i != -1) {
			roomsList.remove(i);
		}
	}

	private int getIndexRoom(Room room) {

		int indexRoom = getIndexRoomByNumber(room.getNumber());
		return indexRoom;
	}

	private int getIndexRoomByNumber(int number) {
		for (int i = 0; i < this.roomsList.size() - 1; i++) {
			if (roomsList.get(i) != null
					&& roomsList.get(i).getNumber() == number) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public List<Room> printRoomSortedByContetn(List<Room> roomsList) {

		try {
			Collections.sort(roomsList, Comparator.ROOM_BY_CONTENT);
		} catch (RuntimeException e) {
			logger.info("RuntimeException");
		}

		return roomsList;
	}

	@Override
	public List<Room> printRoomSortedByNumber(List<Room> roomsList) {
		try {
			Collections.sort(roomsList, Comparator.ROOM_BY_NUMBER);
		} catch (RuntimeException e) {
			logger.info("RuntimeException");
		}

		return roomsList;
	}

	@Override
	public List<Room> printRoomSortedByPrice(List<Room> roomsList) {

		try {
			Collections.sort(roomsList, Comparator.ROOM_BY_PRICE);
		} catch (RuntimeException e) {
			logger.info("RuntimeException");
		}

		return roomsList;
	}

	@Override
	public List<Room> printRoomSortedByStars(List<Room> roomsList) {

		try {
			Collections.sort(roomsList, Comparator.ROOM_BY_STARS);
		} catch (RuntimeException e) {
			logger.info("RuntimeException");
		}

		return roomsList;
	}

	@Override
	public List<Room> printRoomFree(List<Room> roomsList) {

		List<Room> newRoomList = new ArrayList<Room>();

		try {
			for (int i = 0; i < roomsList.size(); i++) {
				if (roomsList.get(i).getStatus() == Status.FREE) {
					newRoomList.add(roomsList.get(i));
				}
			}
		} catch (RuntimeException e) {
			logger.info("RuntimeException");
		}

		return newRoomList;
	}

	@Override
	public int printAmountRoomFree(List<Room> roomsList) {
		int amountFree = 0;

		try {
			for (int i = 0; i < roomsList.size(); i++) {
				if (roomsList.get(i).getStatus() == Status.FREE) {
					amountFree++;
				}
			}
		} catch (RuntimeException e) {
			logger.info("RuntimeException");
		}

		return amountFree;
	}

	@Override
	public List<Guest> printRoomThemGuestsAndDateInSettle(Room room,
			List<Guest> guestsList) {

		int[] idGuest = room.getIdGuest();
		List<Guest> newGuestList = new ArrayList<>();

		try {
			for (int i = 0; i < guestsList.size(); i++) {
				for (int j = 0; j < idGuest.length; j++) {
					if ((guestsList.get(i).getId() == idGuest[j])&& (room.getStatus()==Status.NOTFREE)) {
						newGuestList.add(guestsList.get(i));

					}
				}
			}
		} catch (RuntimeException e) {
			logger.info("RuntimeException");
		}

		return newGuestList;
	}

	public void changeRoomStatus(Room room, Status status) {
		try {
			room.setStatus(status);
		} catch (RuntimeException e) {
			logger.info("RuntimeException");
		}

	}

	public void changeRoomPrice(Room room, int price) {

		try {
			room.setPrice(price);
		} catch (RuntimeException e) {
			logger.info("RuntimeException");
		}
	}

}
