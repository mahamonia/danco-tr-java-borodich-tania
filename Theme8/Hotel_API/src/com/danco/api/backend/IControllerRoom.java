package com.danco.api.backend;

import java.util.List;

import com.danco.training.entity.Guest;
import com.danco.training.entity.Room;
import com.danco.training.entity.Status;

public interface IControllerRoom {

	public void createRoom(Room room);

	public int getNumberForNewRoom();

	public void updateRoom(Room room);

	public void deleteRoom(Room room);

	public Room getRoomByNumber(int number);

	public List<Room> getListRoom();

	public List<Guest> getListGuestRoom(Room room);

	public List<Room> printRoomSortedByContetn(List<Room> roomsList);

	public List<Room> printRoomSortedByNumber(List<Room> roomsList);

	public List<Room> printRoomSortedByPrice(List<Room> roomsList);

	public List<Room> printRoomSortedByStars(List<Room> roomsList);

	public List<Room> getRoomListFree();
	public void setListRoom(List<Room> roomsList);

	public int printAmountRoomFree();

	public List<Guest> printRoomThemGuests(Room room, List<Guest> guestsList);

	public void changeRoomStatus(Room room, Status status);

	public void changeRoomPrice(Room room, int price);

	public Room cloneRoom(Room room);
	public List<Room> importRoomsList() ;
	public void exportRoomsList(List<Room> roomsList);

}
