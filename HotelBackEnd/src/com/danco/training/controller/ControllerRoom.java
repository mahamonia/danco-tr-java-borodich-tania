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
import com.danco.training.utility.ParseUtilityCSVForRoom;

public class ControllerRoom implements IControllerRoom {

	private static Logger logger = LogManager.getLogger(ControllerRoom.class);

	private List<Room> roomsList;
	
	private ParseUtilityCSVForRoom utility = ParseUtilityCSVForRoom.getInstance();

	public ControllerRoom(List<Room> roomsList) {
		this.roomsList = roomsList;
	}

	@Override
	public void createRoom(Room room) {
		try {
			this.roomsList.add(room);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Override
	public int getNumberForNewRoom() {
		return this.roomsList.size() + 1;

	}

	@Override
	public void updateRoom(Room room) {
		try {
			int i = getIndexRoom(room);
			this.roomsList.set(i, room);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void deleteRoom(Room room) {
		try {
			int i = getIndexRoom(room);
			if (i != -1) {
				this.roomsList.remove(i);
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
			for (int i = 0; i < this.roomsList.size(); i++) {
				if (this.roomsList.get(i).getNumber() == number) {
					return i;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return -1;
	}

	@Override
	public Room getRoomByNumber(int number) {
		try {
			for (int i = 0; i < this.roomsList.size(); i++) {
				if (this.roomsList.get(i).getNumber() == number) {
					return this.roomsList.get(i);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Room> getListRoom() {

		return roomsList;
	}
	@Override
	public void setListRoom(List<Room> roomsList) {
		this.roomsList = roomsList;
	}
	@Override
	public List<Guest> getListGuestRoom(Room room) {
		
		try {
			return room.getGuestList();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}		
		return null;
		
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
	public List<Room> getRoomListFree() {

		List<Room> roomFreeList = new ArrayList<Room>();

		try {
			for (int i = 0; i < this.roomsList.size(); i++) {
				if (this.roomsList.get(i).getStatus() == Status.FREE) {
					roomFreeList.add(this.roomsList.get(i));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return roomFreeList;
	}

	@Override
	public int printAmountRoomFree() {
		int amountFree = 0;

		try {
			for (int i = 0; i < this.roomsList.size(); i++) {
				if (this.roomsList.get(i).getStatus() == Status.FREE) {
					amountFree++;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return amountFree;
	}

	@Override
	public List<Guest> printRoomThemGuests(Room room, List<Guest> guestsList) {

		List<Guest> newGuestList = new ArrayList<>();

		try {

			List<Guest> guestList = room.getGuestList();
			for (int i = guestList.size(); i > guestList.size() - 3; i--) {
				newGuestList.add(guestList.get(i));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return newGuestList;
	}

	@Override
	public void changeRoomStatus(Room room, Status status) {
		try {
			room.setStatus(status);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void changeRoomPrice(Room room, int price) {

		try {
			room.setPrice(price);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public Room cloneRoom(Room room) {
		Room clon = room;
		try {
			clon = room.clone();
			int number = getNumberForNewRoom();
			clon.setNumber(number);
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return clon;
	}
	@Override
	public List<Room> importRoomsList() {
		
		return utility.importData();
	}
	@Override
	public void exportRoomsList(List<Room> roomsList) {
		utility.exportData(roomsList);
	}

}
