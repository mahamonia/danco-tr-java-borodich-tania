package com.danco.api.backend;

import java.util.List;

import com.danco.training.entity.Guest;
import com.danco.training.entity.Room;
import com.danco.training.entity.Status;

public interface IControllerRoom {

	public void createRoom(Room room);

	public void updateRoom(Room room);

	public void deleteRoom(Room room);

	public Room getRoomByNumber(int number);

	public String[] getListRoom();

	public String[] getListThreeLastGuestsOfRoom(int idRoom);

	public String[] printRoomSortedByContetn();

	public List<Room> printRoomSortedByNumber(List<Room> roomsList);

	public List<Room> printRoomSortedByPrice(List<Room> roomsList);

	public List<Room> printRoomSortedByStars(List<Room> roomsList);

	public List<Room> getRoomListFree();

	public int printAmountRoomFree();

	public List<Guest> printRoomThemGuests(Room room, List<Guest> guestsList);

	public void changeRoomStatus(Room room, Status status);

	public void changeRoomPrice(Room room, int price);

	public Room cloneRoom(Room room);
	public List<Room> importRoomsList() ;
	public void exportRoomsList(List<Room> roomsList);

}
