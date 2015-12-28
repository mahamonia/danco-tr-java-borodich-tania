package com.danco.training.controller;

import com.danco.training.entity.Room;
import com.danco.training.entity.Status;

public class ControllerRoom implements IPrintRoom {

	private Room[] Rooms;
	private final int MAX_ROOMS;

	public ControllerRoom(Room[] Rooms) {
		MAX_ROOMS = Rooms.length;
		this.Rooms = Rooms;
	}

	public void createRooms(Room Room) {

		int i = getNumberForNewRoom();
		Rooms[i] = Room;

	}

	private int getNumberForNewRoom() {

		for (int i = 0; i < Rooms.length; i++) {
			if (Rooms[i] == null) {
				return i;
			}
		}
		return -1;
	}

	public void deleteRoom(Room Room) {
		int i = getNumberRoom(Room);
		if (i != -1) {
			Rooms[i] = null;
		}
	}

	private int getNumberRoom(Room Room) {

		int NumberRoom = getNumberRoomById(Room.getNumber());
		return NumberRoom;
	}

	private int getNumberRoomById(int Id) {
		for (int i = 0; i < this.Rooms.length; i++) {
			if (Rooms[i] != null && Rooms[i].getNumber() == Id) {
				return i;
			}
		}
		return -1;

	}

	public void changePrice(Room Room, int Price) {
		System.out.println("changePrice");

	}

	public void changeStatus(Room Room, Status Status) {
		System.out.println("changeStatus");

	}

	@Override
	public void printRoom(Room[] Rooms) {
		for (int i = 0; i < Rooms.length; i++) {
			System.out.println(Rooms[i].getNumber());
		}

	}

	@Override
	public int printRoomFree(Room[] Rooms) {
		int amountFree = 0;
		for (int i = 0; i < Rooms.length; i++) {
			if (Rooms[i].getStatus() == Status.FREE) {
				amountFree++;
			}
		}
		return amountFree;

	}

	@Override
	public void printRoomFreeDate(Room[] Rooms, String Date) {

	}

	@Override
	public void printRoomThemGuestAndDateInSettle(Room[] Rooms) {

	}

	@Override
	public void printRoomPrice(Room[] Rooms) {

		for (int i = 0; i < Rooms.length; i++) {
			System.out.println("Room " + Rooms[i].getNumber() + " price "
					+ Rooms[i].getPrice());
		}
	}

	public void changeRoomStatus(Room Room, Status Status) {
		Room.setStatus(Status);
		System.out.println("change Room Status");
	}

	public int getMAX_ROOMS() {
		return MAX_ROOMS;
	}

}
