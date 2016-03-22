package com.danco.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.model.entity.Room;
import com.danco.model.entity.Status;

public class RoomDao implements BaseDao<Room> {

	private static final Logger LOGGER = LogManager.getLogger(RoomDao.class);

	public RoomDao() {

	}

	@Override
	public void create(DataSource source, Room model) {

		try {
			Statement statement = source.openConnection().createStatement();

			statement
					.executeUpdate("INSERT INTO `Hotel_service`.`Room` "
							+ "(`idRoom`, `number`, `content`, `stars`, `price`, `idStatus`) "
							+ "VALUES (" + getIdForNewModel(source) + ", "
							+ model.getNumber() + ", " + model.getContent()
							+ ", " + model.getStars() + ", " + model.getPrice()
							+ ", 1);");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}

	}
	
	@Override
	public int getIdForNewModel(DataSource source) {
		int id = 0;
		Statement statement = null;
		try {
			statement = source.openConnection().createStatement();
			ResultSet result = statement
					.executeQuery("SELECT * FROM Room order by idRoom");

			result.last();
			id = result.getInt(1);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}
		return id + 1;

	}

	@Override
	public void update(DataSource source, int idModel) {
		Room room = getById(source, idModel);
		try {
			Statement statement = source.openConnection().createStatement();

			statement.executeUpdate("UPDATE  Room SET idRoom = " + room.getId()
					+ ", number = " + room.getNumber() + ", content = "
					+ room.getContent() + ", stars = " + room.getStars()
					+ ", price = " + room.getPrice() + ", idStatus = "
					+ room.getStatus().ordinal() + ";");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}

	}

	@Override
	public void delete(DataSource source, int idModel) {
		try {
			Statement statement = source.openConnection().createStatement();

			statement.executeUpdate("DELETE FROM Room WHERE idRoom = "
					+ idModel + ";");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}

	}

	@Override
	public Room getById(DataSource source, int idModel) {

		Statement statement = null;
		ResultSet result = null;
		try {
			statement = source.openConnection().createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room WHERE idRoom =" + idModel
							+ ";");
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} 
		return parseResultSet(result);
	}

	@Override
	public List<Room> getList(DataSource source) {

		Statement statement = null;
		ResultSet result = null;

		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = source.openConnection().createStatement();
			result = statement.executeQuery("SELECT * FROM Room;");
			while (result.next()) {

				roomList.add(parseResultSet(result));
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}finally{
			source.closeConnection();
		}

		return roomList;
	}

	@Override
	public Room parseResultSet(ResultSet result) {

		Room room = null;
		try {
			if (result.next()){
				int id = result.getInt("idRoom");
				int number = result.getInt("number");
				int content = result.getInt("content");
				int stars = result.getInt("stars");
				int price = result.getInt("price");
				Status status = Status.values()[result.getInt("idStatus")];

				room = new Room(number, content, status, stars, price);
				room.setId(id);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		
		return room;
	}

	public List<Room> getListRoomSortedByContetn(DataSource source) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = source.openConnection().createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room ORDER BY content;");

			while (result.next()) {

				roomList.add(parseResultSet(result));
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}finally{
			source.closeConnection();
		}

		return roomList;
	}

	public List<Room> getListRoomSortedByNumber(DataSource source) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = source.openConnection().createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room ORDER BY number;");
			while (result.next()) {

				roomList.add(parseResultSet(result));
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}finally{
			source.closeConnection();
		}

		return roomList;
	}

	public List<Room> getListRoomSortedByPrice(DataSource source) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = source.openConnection().createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room ORDER BY price;");
			while (result.next()) {

				roomList.add(parseResultSet(result));
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}finally{
			source.closeConnection();
		}

		return roomList;
	}

	public List<Room> getListRoomSortedByStars(DataSource source) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = source.openConnection().createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room ORDER BY stars;");
			while (result.next()) {

				roomList.add(parseResultSet(result));
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}finally{
			source.closeConnection();
		}

		return roomList;
	}

	public List<Room> getListFreeRoom(DataSource source) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = source.openConnection().createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room WHERE idStatus = 1;");
			while (result.next()) {

				roomList.add(parseResultSet(result));
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}finally{
			source.closeConnection();
		}

		return roomList;
	}

	public List<Room> getListRoomFreeSortedByContetn(DataSource source) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = source.openConnection().createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room WHERE idStatus = 1 ORDER BY content;");
			while (result.next()) {

				roomList.add(parseResultSet(result));
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}finally{
			source.closeConnection();
		}

		return roomList;
	}

	public List<Room> getListRoomFreeSortedByNumber(DataSource source) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = source.openConnection().createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room WHERE idStatus = 1 ORDER BY number;");
			while (result.next()) {

				roomList.add(parseResultSet(result));
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}finally{
			source.closeConnection();
		}

		return roomList;
	}

	public List<Room> getListRoomFreeSortedByPrice(DataSource source) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = source.openConnection().createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room WHERE idStatus = 1 ORDER BY price;");
			while (result.next()) {

				roomList.add(parseResultSet(result));
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}finally{
			source.closeConnection();
		}

		return roomList;
	}

	public List<Room> getListRoomFreeSortedByStars(DataSource source) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = source.openConnection().createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room WHERE idStatus = 1 ORDER BY stars;");
			while (result.next()) {

				roomList.add(parseResultSet(result));
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}finally{
			source.closeConnection();
		}

		return roomList;
	}
}
