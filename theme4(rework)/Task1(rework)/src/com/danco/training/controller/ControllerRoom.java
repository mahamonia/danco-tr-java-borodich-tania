package com.danco.training.controller;

import java.util.Arrays;

import com.danco.training.comparator.Comparator;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Room;
import com.danco.training.entity.Status;

public class ControllerRoom implements IPrintRoom {

	private Room[] roomsList;
	private final int MAX_ROOMS;

	public ControllerRoom(Room[] roomsList) {
		MAX_ROOMS = roomsList.length;
		this.roomsList = roomsList;
	}

	public void createRoom(Room room) {

		int i = getNumberForNewRoom();
		roomsList[i] = room;
	}

	private int getNumberForNewRoom() {

		for (int i = 0; i < roomsList.length; i++) {
			if (roomsList[i] == null) {
				return i;
			}
		}
		return -1;
	}

	public void deleteRoom(Room room) {
		int i = getNumberRoom(room);
		if (i != -1) {
			roomsList[i] = null;
		}
	}

	private int getNumberRoom(Room room) {

		int numberRoom = getNumberRoomById(room.getNumber());
		return numberRoom;
	}

	private int getNumberRoomById(int Id) {
		for (int i = 0; i < this.roomsList.length; i++) {
			if (roomsList[i] != null && roomsList[i].getNumber() == Id) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public Room[] printRoomSortedByContetn(Room[] roomsList) {

		Arrays.sort(roomsList, Comparator.ROOM_BY_CONTENT);
		return roomsList;
	}

	@Override
	public Room[] printRoomSortedByNumber(Room[] roomsList) {
		Arrays.sort(roomsList, Comparator.ROOM_BY_NUMBER);
		return roomsList;
	}

	@Override
	public Room[] printRoomSortedByPrice(Room[] roomsList) {
		Arrays.sort(roomsList, Comparator.ROOM_BY_PRICE);
		return roomsList;
	}

	@Override
	public Room[] printRoomSortedByStars(Room[] roomsList) {
		Arrays.sort(roomsList, Comparator.ROOM_BY_STARS);
		return roomsList;
	}

	@Override
	public Room[] printRoomFree(Room[] roomsList) {

		Room[] newRoomList = new Room[MAX_ROOMS];
		for (int i = 0; i < roomsList.length; i++) {
			if (roomsList[i].getStatus() == Status.FREE) {
				newRoomList[i] = roomsList[i];
			}
		}
		return newRoomList;
	}

	@Override
	public int printAmountRoomFree(Room[] roomsList) {
		int amountFree = 0;
		for (int i = 0; i < roomsList.length; i++) {
			if (roomsList[i].getStatus() == Status.FREE) {
				amountFree++;
			}
		}
		return amountFree;
	}

	@Override
	public Guest[] printRoomThemGuestsAndDateInSettle(Room room,
			Guest[] guestsList) {
		int k = 0; // index for newGuestList which lin in room
		int[] idGuest = room.getIdGuest();
		Guest[] newGuestList = new Guest[idGuest .length];

		for (int i = 0; i < guestsList.length; i++) {
			for (int j = 0; j < idGuest.length; j++) {
				if (guestsList[i].getId() == idGuest[j]) {
					newGuestList[k] = guestsList[i];
					k++;
				}
			}
		}
		return newGuestList;
	}

	public void changeRoomStatus(Room room, Status status) {
		room.setStatus(status);
	}

	public void changeRoomPrice(Room room, int price) {
		room.setPrice(price);

	}

	public int getMAX_ROOMS() {
		return MAX_ROOMS;
	}

}
