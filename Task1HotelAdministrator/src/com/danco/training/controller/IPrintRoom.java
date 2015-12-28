package com.danco.training.controller;

import com.danco.training.entity.Room;

public interface IPrintRoom {
	public void printRoom( Room [] Rooms);
	public int printRoomFree (Room [] Rooms);
	public void printRoomFreeDate (Room [] Rooms, String Date);
	public void printRoomThemGuestAndDateInSettle (Room [] Rooms);
	public void printRoomPrice(Room [] Rooms);

}
