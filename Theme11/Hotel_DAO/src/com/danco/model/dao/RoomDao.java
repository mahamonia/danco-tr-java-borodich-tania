package com.danco.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.dao.IRoomDao;
import com.danco.model.entity.Room;
import com.danco.model.entity.Status;

public class RoomDao implements IRoomDao {

	private static final Logger LOGGER = LogManager.getLogger(RoomDao.class);

	public RoomDao() {
	}

	@Override
	public void create(Connection connect, Room model) {
		try {
			Statement statement = connect.createStatement();
			int id = getIdForNewModel(connect);
			statement
					.executeUpdate("INSERT INTO `Hotel_service`.`Room` "
							+ "(`idRoom`, `number`, `content`, `stars`, `price`, `idStatus`) "
							+ "VALUES (" + id + ", " + model.getNumber() + ", "
							+ model.getContent() + ", " + model.getStars()
							+ ", " + model.getPrice() + ", 1);");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public int getIdForNewModel(Connection connect) {
		int id = 0;
		Statement statement = null;
		try {
			statement = connect.createStatement();
			ResultSet result = statement
					.executeQuery("SELECT * FROM Room order by idRoom;");

			if (result.next()) {
				result.last();
				id = result.getInt(1);
			} else
				id = 1;

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return id + 1;

	}

	@Override
	public void update(Connection connect, Room model) {

		try {
			Statement statement = connect.createStatement();
			statement.executeUpdate("UPDATE  Room SET number = "
					+ model.getNumber() + ", content = " + model.getContent()
					+ ", stars = " + model.getStars() + ", price = "
					+ model.getPrice() + ", idStatus = "
					+ (model.getStatus().ordinal() + 1) + " WHERE idRoom = "
					+ model.getId() + " ;");
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void delete(Connection connect, int idModel) {
		try {
			Statement statement = connect.createStatement();

			statement.executeUpdate("DELETE FROM Room WHERE idRoom = "
					+ idModel + ";");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}

	}

	@Override
	public Room getById(Connection connect, int idModel) {
		Statement statement = null;
		ResultSet result = null;
		Room room = null;
		try {
			statement = connect.createStatement();
			result = statement.executeQuery("SELECT * FROM Room WHERE idRoom ="
					+ idModel + ";");
			if (result.next()) {
				room = parseResultSet(result);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return room;
	}

	@Override
	public List<Room> getList(Connection connect) {

		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = connect.createStatement();
			result = statement.executeQuery("SELECT * FROM Room;");
			while (result.next()) {

				roomList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return roomList;
	}

	@Override
	public Room parseResultSet(ResultSet result) {
		Room room = null;
		try {
			int id = result.getInt("idRoom");
			int number = result.getInt("number");
			int content = result.getInt("content");
			int stars = result.getInt("stars");
			int price = result.getInt("price");
			Status status = Status.values()[result.getInt("idStatus") - 1];

			room = new Room(number, content, status, stars, price);
			room.setId(id);

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return room;
	}

	@Override
	public List<Room> getListRoomSortedByContetn(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room ORDER BY content;");

			while (result.next()) {
				roomList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return roomList;
	}

	@Override
	public List<Room> getListRoomSortedByNumber(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room ORDER BY number;");
			while (result.next()) {
				roomList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return roomList;
	}

	@Override
	public List<Room> getListRoomSortedByPrice(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room ORDER BY price;");
			while (result.next()) {
				roomList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return roomList;
	}

	@Override
	public List<Room> getListRoomSortedByStars(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room ORDER BY stars;");
			while (result.next()) {
				roomList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return roomList;
	}

	@Override
	public List<Room> getListFreeRoom(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room WHERE idStatus = 1;");
			while (result.next()) {
				roomList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return roomList;
	}

	@Override
	public List<Room> getListRoomFreeSortedByContetn(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room WHERE idStatus = 1 ORDER BY content;");
			while (result.next()) {
				roomList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return roomList;
	}

	@Override
	public List<Room> getListRoomFreeSortedByNumber(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room WHERE idStatus = 1 ORDER BY number;");
			while (result.next()) {
				roomList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return roomList;
	}

	@Override
	public List<Room> getListRoomFreeSortedByPrice(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room WHERE idStatus = 1 ORDER BY price;");
			while (result.next()) {
				roomList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return roomList;
	}

	@Override
	public List<Room> getListRoomFreeSortedByStars(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT * FROM Room WHERE idStatus = 1 ORDER BY stars;");
			while (result.next()) {
				roomList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return roomList;
	}
}
