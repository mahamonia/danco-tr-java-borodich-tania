package com.danco.training.controller;

import java.util.Arrays;

import com.danco.training.comparators.SortRoomByContent;
import com.danco.training.comparators.SortRoomByNumber;
import com.danco.training.comparators.SortRoomByPrice;
import com.danco.training.comparators.SortRoomByStars;
import com.danco.training.comparators.TypeSort;
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
	public void printRoom(Room[] roomsList, TypeSort type) {

		sortedRoom(roomsList, type);

		for (int i = 0; i < roomsList.length - 1; i++) {
			System.out.println(roomsList[i].getNumber() + " price "
					+ roomsList[i].getPrice());
		}
	}

	@Override
	public void printRoomFree(Room[] roomsList, TypeSort type) {

		sortedRoom(roomsList, type);

		for (int i = 0; i < roomsList.length; i++) {
			if (roomsList[i].getStatus() == Status.FREE) {
				System.out.println(roomsList[i].getNumber());
			}
		}
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
	public void printRoomFreeDate(Room[] rooms, String date) {

	}

	@Override
	public void printRoomThemGuestAndDateInSettle(Room room, Guest[] guestsList) {

		int[] idGuest = room.getIdGuest();

		for (int i = 0; i < guestsList.length; i++) {
			for (int j = 0; j < idGuest.length; j++) {
				if (guestsList[i].getId() == idGuest[j]) {
					System.out.println(guestsList[i].getName() + " come "
							+ guestsList[i].getDateInSettle());
				}
			}
		}
	}

	@Override
	public void printRoomPrice(Room[] rooms, TypeSort type) {
		sortedRoom(roomsList, type);

		for (int i = 0; i < rooms.length; i++) {
			System.out.println("Room " + rooms[i].getNumber() + " price "
					+ rooms[i].getPrice());
		}
	}

	public void sortedRoom(Room[] roomsList, TypeSort type) {

		switch (type) {
		case BY_PRICE:
			Arrays.sort(roomsList, new SortRoomByPrice());
			break;
		case BY_CONTENT:
			Arrays.sort(roomsList, new SortRoomByContent());
			break;
		case BY_STARS:
			Arrays.sort(roomsList, new SortRoomByStars());
			break;
		default:
			Arrays.sort(roomsList, new SortRoomByNumber());
			break;
		}
	}

	public void changeRoomStatus(Room room, Status status) {
		room.setStatus(status);
		System.out.println("change Status of room on " + status);
	}

	public void changeRoomPrice(Room room, int price) {
		room.setPrice(price);

		System.out.println("change Price");

	}

	public int getMAX_ROOMS() {
		return MAX_ROOMS;
	}
}
