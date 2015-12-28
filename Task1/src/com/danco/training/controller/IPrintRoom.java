package com.danco.training.controller;

import com.danco.training.comparators.TypeSort;
import com.danco.training.entity.Room;

public interface IPrintRoom {
	public void printRoom( Room [] roomsList, TypeSort type);
	public void printRoomFree(Room [] roomsList, TypeSort type) ;
	public int printAmountRoomFree (Room [] roomsList);
	public void printRoomFreeDate (Room [] roomsList, String date);
	public void printRoomThemGuestAndDateInSettle (Room [] roomsList);
	public void printRoomPrice(Room [] roomsList, TypeSort type);

}
