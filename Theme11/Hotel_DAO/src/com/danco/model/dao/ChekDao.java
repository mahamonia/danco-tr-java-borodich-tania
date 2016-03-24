package com.danco.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.dao.IChekDao;
import com.danco.model.entity.Chek;
import com.danco.model.entity.Guest;
import com.danco.model.entity.Room;
import com.danco.model.entity.Status;

public class ChekDao implements IChekDao {

	private static final Logger LOGGER = LogManager.getLogger(ChekDao.class);

	@Override
	public void create(Connection connect, Chek model) {
		try {
			Statement statement = connect.createStatement();
			int id = getIdForNewModel(connect);
			statement
					.executeUpdate("INSERT INTO `Hotel_service`.`Chek` "
							+ "(`idChek`, `date_in_settle`, `date_out_settle`, `status`, `Room_idRoom`, `Guest_idGuest`) "
							+ "VALUES (" + id + ", '" + model.getDateInSettle()
							+ "', '" + model.getDateOutSettle() + "', "
							+ model.isStatus() + ", " + model.getRoom().getId()
							+ ", " + model.getGuest().getId() + ");");

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
					.executeQuery("SELECT * FROM Chek ORDER BY idChek");

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
	public void update(Connection connect, Chek model) {
		try {
			Statement statement = connect.createStatement();
			statement.executeUpdate("UPDATE  Chek SET date_in_settle = '"
					+ model.getDateInSettle() + "', date_out_settle = '"
					+ model.getDateOutSettle() + "', status = "
					+ model.isStatus() + ", Room_idRoom = "
					+ model.getRoom().getId() + ", Guest_idGuest = "
					+ model.getGuest().getId() + " WHERE idChek = "
					+ model.getId() + ";");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void delete(Connection connect, int idModel) {
		try {
			Statement statement = connect.createStatement();
			statement.executeUpdate("DELETE FROM Chek WHERE idChek = "
					+ idModel + ";");
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public Chek getById(Connection connect, int idModel) {
		Statement statement = null;
		ResultSet result = null;
		Chek chek = null;
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT Chek.idChek, Chek.date_in_settle, Chek.date_out_settle,"
							+ " Chek.status, Chek.Guest_idGuest, Guest.name, Guest.pasport,"
							+ " Chek.Room_idRoom, Room.number, Room.content,"
							+ " Room.price, Room.stars, Room.idStatus"
							+ " FROM Chek JOIN Guest"
							+ " ON Chek.Guest_idGuest = Guest.idGuest"
							+ " LEFT JOIN Room"
							+ " ON Chek.Room_idRoom = Room.idRoom"
							+ " WHERE idChek = " + idModel + ";");
			if (result.next()) {
				chek = parseResultSet(result);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return chek;
	}

	@Override
	public List<Chek> getList(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Chek> chekList = new ArrayList<Chek>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT Chek.idChek, Chek.date_in_settle, Chek.date_out_settle,"
							+ " Chek.status, Chek.Guest_idGuest, Guest.name, Guest.pasport,"
							+ " Chek.Room_idRoom, Room.number, Room.content,"
							+ " Room.price, Room.stars, Room.idStatus"
							+ " FROM Chek JOIN Guest"
							+ " ON Chek.Guest_idGuest = Guest.idGuest"
							+ " LEFT JOIN Room"
							+ " ON Chek.Room_idRoom = Room.idRoom;");

			while (result.next()) {
				chekList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return chekList;
	}

	@Override
	public List<Chek> getListChekSortedByDateOutSettle(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Chek> chekList = new ArrayList<Chek>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT Chek.idChek, Chek.date_in_settle, Chek.date_out_settle,"
							+ " Chek.status, Chek.Guest_idGuest, Guest.name, Guest.pasport,"
							+ " Chek.Room_idRoom, Room.number, Room.content,"
							+ " Room.price, Room.stars, Room.idStatus"
							+ " FROM Chek JOIN Guest"
							+ " ON Chek.Guest_idGuest = Guest.idGuest"
							+ " LEFT JOIN Room"
							+ " ON Chek.Room_idRoom = Room.idRoom"
							+ " ORDER BY date_out_settle;");

			while (result.next()) {
				chekList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return chekList;
	}

	@Override
	public Chek parseResultSet(ResultSet result) {

		Chek chek = null;
		try {
			int idCheck = result.getInt("idChek");
			Date dateInSettle = result.getDate("date_in_settle");
			Date dateOutSettle = result.getDate("date_out_settle");
			boolean status = result.getBoolean("status");

			int idGuest = result.getInt("Guest_idGuest");
			String name = result.getString("name");
			String pasport = result.getString("pasport");
			Guest guest = new Guest(name, pasport);
			guest.setId(idGuest);

			int idRoom = result.getInt("Room_idRoom");
			int number = result.getInt("number");
			int content = result.getInt("content");
			int stars = result.getInt("stars");
			int price = result.getInt("price");
			Status statusRoom = Status.values()[result.getInt("idStatus") - 1];
			Room room = new Room(number, content, statusRoom, stars, price);
			room.setId(idRoom);

			chek = new Chek(dateInSettle, dateOutSettle, status, guest, room);
			chek.setId(idCheck);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return chek;
	}

	@Override
	public Chek getChekForIdGuest(Connection connect, int idGuest) {
		Statement statement = null;
		ResultSet result = null;
		Chek chek = null;
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT Chek.idChek, Chek.date_in_settle, Chek.date_out_settle,"
							+ " Chek.status, Chek.Guest_idGuest, Guest.name, Guest.pasport,"
							+ " Chek.Room_idRoom, Room.number, Room.content,"
							+ " Room.price, Room.stars, Room.idStatus"
							+ " FROM Chek JOIN Guest"
							+ " ON Chek.Guest_idGuest = Guest.idGuest"
							+ " LEFT JOIN Room"
							+ " ON Chek.Room_idRoom = Room.idRoom"
							+ " WHERE Chek.Guest_idGuest = " + idGuest + ";");

			if (result.next()) {
				chek = parseResultSet(result);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return chek;
	}
}
