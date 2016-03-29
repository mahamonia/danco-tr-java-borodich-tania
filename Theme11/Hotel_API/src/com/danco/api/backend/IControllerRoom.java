package com.danco.api.backend;

import java.sql.Connection;
import java.util.List;

import com.danco.model.entity.Room;
import com.danco.model.entity.Status;

public interface IControllerRoom {

	public void createRoom(Connection connect, Room room);

	public void updateRoom(Connection connect, Room room);

	public void deleteRoom(Connection connect, int idRoom);

	public Room getRoom(Connection connect, int idRoom);

	public List<Room> getListRoom(Connection connect);

	public List<Room> getListRoomSortedBy(Connection connect, String status, String param);

	public int getAmountRoomFree(Connection connect);

	public void changeRoomStatus (Connection connect, Room room, Status status);

	public void changeRoomPrice(Connection connect, Room room, int price);

	public Room cloneRoom(Connection connect, int idRoom);

	public List<Room> importRoomsList();

	public void exportRoomsList(Connection connect);

}
