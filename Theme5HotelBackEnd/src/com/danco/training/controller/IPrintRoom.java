package com.danco.training.controller;

import java.util.List;


import com.danco.training.entity.Guest;
import com.danco.training.entity.Room;

public interface IPrintRoom {
	public List<Room> printRoomSortedByContetn(List<Room>  roomsList);
	public List<Room>  printRoomSortedByNumber( List<Room>  roomsList);
	public List<Room>  printRoomSortedByPrice( List<Room>  roomsList);
	public List<Room>  printRoomSortedByStars(List<Room>  roomsList);

	public List<Room>  printRoomFree(List<Room>  roomsList) ;
	public int printAmountRoomFree (List<Room>  roomsList);
	public List<Guest> printRoomThemGuestsAndDateInSettle (Room  room, List<Guest> guestsList);

}
