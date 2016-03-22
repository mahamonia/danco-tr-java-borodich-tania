package com.danco.api.backend;

import java.util.List;

import com.danco.model.entity.Room;
import com.danco.model.entity.Status;

public interface IControllerRoom {

	public void createRoom(Room room);

	public void updateRoom(int idRoom);

	public void deleteRoom(int idRoom);

	public Room getRoom(int idRoom);

	public List<Room> getListRoom();

	public List<Room> getListRoomSortedByContetn();

	public List<Room> getListRoomSortedByNumber();

	public List<Room> getListRoomSortedByPrice();

	public List<Room> getListRoomSortedByStars();

	public List<Room> getListRoomFree();
	
	public List<Room> getListRoomFreeSortedByContetn();

	public List<Room> getListRoomFreeSortedByNumber();

	public List<Room> getListRoomFreeSortedByPrice();

	public List<Room> getListRoomFreeSortedByStars();
	
	public int getAmountRoomFree();

	public void changeRoomStatus(int idRoom, Status status);

	public void changeRoomPrice(int idRoom, int price);

	public Room cloneRoom(int idRoom);

	public List<Room> importRoomsList();

	public void exportRoomsList();

}
