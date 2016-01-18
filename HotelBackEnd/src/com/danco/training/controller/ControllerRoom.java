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
		try {
			
			roomsList.add(room);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void updateRoom(Room room) {
		try {
			int i = getIndexRoom(room);
			roomsList.set(i, room);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void deleteRoom(Room room) {
		try {
			int i = getIndexRoom(room);
			if (i != -1) {
				roomsList.remove(i);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	private int getIndexRoom(Room room) {
		int indexRoom = 0;
		try {
			indexRoom = getIndexRoomByNumber(room.getNumber());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return indexRoom;
	}

	private int getIndexRoomByNumber(int number) {
		try {
			for (int i = 0; i < this.roomsList.size() - 1; i++) {
				if (roomsList.get(i) != null
						&& roomsList.get(i).getNumber() == number) {
					return i;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return -1;
	}

	public Room getRoomByNumber(int number) {
		try {
			for (int i = 0; i < this.roomsList.size() - 1; i++) {
				if (roomsList.get(i) != null
						&& roomsList.get(i).getNumber() == number) {
					return roomsList.get(i);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public List<Room> getListRoom() {

		return roomsList;
	}

	@Override
	public List<Room> printRoomSortedByContetn(List<Room> roomsList) {

		try {
			Collections.sort(roomsList, Comparator.ROOM_BY_CONTENT);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return roomsList;
	}

	@Override
	public List<Room> printRoomSortedByNumber(List<Room> roomsList) {
		try {
			Collections.sort(roomsList, Comparator.ROOM_BY_NUMBER);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return roomsList;
	}

	@Override
	public List<Room> printRoomSortedByPrice(List<Room> roomsList) {

		try {
			Collections.sort(roomsList, Comparator.ROOM_BY_PRICE);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return roomsList;
	}

	@Override
	public List<Room> printRoomSortedByStars(List<Room> roomsList) {

		try {
			Collections.sort(roomsList, Comparator.ROOM_BY_STARS);
		} catch (Exception e) {
			logger.error(e.getMessage());
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
		} catch (Exception e) {
			logger.error(e.getMessage());
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
		} catch (Exception e) {
			logger.error(e.getMessage());
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
					if ((guestsList.get(i).getId() == idGuest[j])
							&& (room.getStatus() == Status.NOTFREE)) {
						newGuestList.add(guestsList.get(i));

					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return newGuestList;
	}

	public void changeRoomStatus(Room room, Status status) {
		try {
			room.setStatus(status);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	public void changeRoomPrice(Room room, int price) {

		try {
			room.setPrice(price);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public Room cloneRoom(Room room) {
		Room clon = room;
		try {
			clon = room.clone();
			int number = getNumberForNewRoom();
			clon.setNumber(number);
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage());
		}		
		return clon;		
	}
	
	private int getNumberForNewRoom() {

		for (int i = 0; i < roomsList.size(); i++) {
			if (roomsList.get(i) == null) {
				return i+1;
			}
		}
		return -1;
	}

}
