package com.danco.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.danco.api.dao.IRoomDao;
import com.danco.model.entity.Room;
import com.danco.model.entity.Status;

public class RoomDao implements IRoomDao {

	public RoomDao() {
	}

	@Override
	public void create(Session session, Room model) throws SQLException {
//		Statement statement = connect.createStatement();
//		statement.executeUpdate("INSERT INTO `Hotel_service`.`Room` "
//				+ "(`number`, `content`, `stars`, `price`, `idStatus`) "
//				+ "VALUES (" + model.getNumber() + ", " + model.getContent()
//				+ ", " + model.getStars() + ", " + model.getPrice() + ", 1);");
	}

	@Override
	public void update(Session session, Room model) throws SQLException {

//		Statement statement = connect.createStatement();
//		statement.executeUpdate("UPDATE  Room SET number = "
//				+ model.getNumber() + ", content = " + model.getContent()
//				+ ", stars = " + model.getStars() + ", price = "
//				+ model.getPrice() + ", idStatus = "
//				+ (model.getStatus().ordinal() + 1) + " WHERE idRoom = "
//				+ model.getId() + " ;");
	}

	@Override
	public void delete(Session session, int idModel) throws SQLException {
//		Statement statement = connect.createStatement();
//
//		statement.executeUpdate("DELETE FROM Room WHERE idRoom = " + idModel
//				+ ";");

	}

	@Override
	public Room getById(Session session, int idModel) throws SQLException {
		Statement statement = null;
		ResultSet result = null;
		Room room = null;
	//	statement = connect.createStatement();
		result = statement.executeQuery("SELECT * FROM Room WHERE idRoom ="
				+ idModel + ";");
		if (result.next()) {
			room = parseResultForRoom(result);
		}
		return room;
	}
	
	private Room parseResultForRoom(ResultSet result) throws SQLException {
		Room room = null;
		int id = result.getInt("idRoom");
		int number = result.getInt("number");
		int content = result.getInt("content");
		int stars = result.getInt("stars");
		int price = result.getInt("price");
		Status status = Status.values()[result.getInt("idStatus") - 1];

		room = new Room(number, content, status, stars, price);
		room.setId(id);

		return room;
	}
	
	@Override
	public List<Room> getList(Session session) throws SQLException {

		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
	//	statement = connect.createStatement();
		result = statement.executeQuery("SELECT * FROM Room;");
		while (result.next()) {
			roomList.add(parseResultForRoom(result));
		}
		return roomList;
	}
	
	@Override
	public List<Room> getListRoomSorted(Session session, String status,
			String param) throws SQLException {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
	//	statement = connect.createStatement();
		if (status.isEmpty()){
			result = statement.executeQuery("SELECT * FROM Room ORDER BY '"
					+ param + "';");
		}else{
		result = statement.executeQuery("SELECT * FROM Room  WHERE idStatus = "
				+ Status.valueOf(status).ordinal() + "ORDER BY '"
				+ param + "';");
		}
		while (result.next()) {
			roomList.add(parseResultForRoom(result));
		}
		return roomList;
	}
}
