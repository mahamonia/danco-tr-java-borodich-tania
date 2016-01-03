package com.danco.training.controller;

import com.danco.training.entity.Guest;
import com.danco.training.entity.Room;

public interface IPrintRoom {
	public Room [] printRoomSortedByContetn( Room [] roomsList);
	public Room [] printRoomSortedByNumber( Room [] roomsList);
	public Room [] printRoomSortedByPrice( Room [] roomsList);
	public Room [] printRoomSortedByStars( Room [] roomsList);

	public Room [] printRoomFree(Room [] roomsList) ;
	public int printAmountRoomFree (Room [] roomsList);
	public Guest[] printRoomThemGuestsAndDateInSettle (Room  room, Guest [] guestsList);

}
