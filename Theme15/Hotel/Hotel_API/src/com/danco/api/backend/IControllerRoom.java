package com.danco.api.backend;

import java.util.List;

import org.hibernate.Session;

import com.danco.model.entity.Room;
import com.danco.model.entity.Status;

public interface IControllerRoom {

	public void createRoom(Session session, Room room);

	public void updateRoom(Session session, Room room);

	public void deleteRoom(Session session, int idRoom);

	public Room getRoom(Session session, int idRoom);

	public List<Room> getListRoom(Session session, String status, String param);

	public int getAmountRoomFree(Session session);

	public void changeRoomStatus (Session session, Room room, Status status);

	public void changeRoomPrice(Session session, Room room, int price);

	public Room cloneRoom(Session session, int idRoom);

	public List<Room> importRoomsList(String nameFile);

	public void exportRoomsList(Session session, String nameFile);

}
