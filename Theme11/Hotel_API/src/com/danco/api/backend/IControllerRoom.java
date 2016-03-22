package com.danco.api.backend;

import java.sql.Connection;
import java.util.List;

import com.danco.model.entity.Room;
import com.danco.model.entity.Status;

public interface IControllerRoom {

	public void createRoom(Connection connect, Room room);

	public void updateRoom(Connection connect, int idRoom);

	public void deleteRoom(Connection connect, int idRoom);

	public Room getRoom(Connection connect, int idRoom);

	public List<Room> getListRoom(Connection connect);

	public List<Room> getListRoomSortedByContetn(Connection connect);

	public List<Room> getListRoomSortedByNumber(Connection connect);

	public List<Room> getListRoomSortedByPrice(Connection connect);

	public List<Room> getListRoomSortedByStars(Connection connect);

	public List<Room> getListRoomFree(Connection connect);
	
	public List<Room> getListRoomFreeSortedByContetn(Connection connect);

	public List<Room> getListRoomFreeSortedByNumber(Connection connect);

	public List<Room> getListRoomFreeSortedByPrice(Connection connect);

	public List<Room> getListRoomFreeSortedByStars(Connection connect);
	
	public int getAmountRoomFree(Connection connect);

	public void changeRoomStatus (Connection connect, int idRoom, Status status);

	public void changeRoomPrice(Connection connect, int idRoom, int price);

	public Room cloneRoom(Connection connect, int idRoom);

	public List<Room> importRoomsList(Connection connect);

	public void exportRoomsList(Connection connect);

}
