package com.danco.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.dao.IGuestDao;
import com.danco.model.entity.Guest;

public class GuestDao implements IGuestDao {

	private static final Logger LOGGER = LogManager.getLogger(GuestDao.class);

	@Override
	public void create(Connection connect, Guest model) {
		try {
			Statement statement = connect.createStatement();
			int id = getIdForNewModel(connect);
			statement.executeUpdate("INSERT INTO `Hotel_service`.`Guest` "
					+ "(`idGuest`, `name`, `pasport`) " + "VALUES (" + id
					+ ", '" + model.getName() + "', '" + model.getPasport()
					+ "' );");
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
					.executeQuery("SELECT * FROM Guest order by idGuest");

			result.last();
			id = result.getInt(1);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return id + 1;
	}

	@Override
	public void update(Connection connect, Guest model) {
		try {
			Statement statement = connect.createStatement();
			statement.executeUpdate("UPDATE  Guest SET name = "
					+ model.getName() + ", pasport = " + model.getPasport()
					+ "WHERE idGuest = " + model.getId() + ";");
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void delete(Connection connect, int idModel) {
		try {
			Statement statement = connect.createStatement();
			statement.executeUpdate("DELETE FROM Guest WHERE idGuest = "
					+ idModel + ";");
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public Guest getById(Connection connect, int idModel) {
		Statement statement = null;
		ResultSet result = null;
		Guest guest = null;
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT * FROM Guest WHERE idGuest ="
							+ idModel + ";");
			if (result.next()) {
				guest = parseResultSet(result);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return guest;
	}

	@Override
	public List<Guest> getList(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Guest> guestList = new ArrayList<Guest>();
		try {
			statement = connect.createStatement();
			result = statement.executeQuery("SELECT * FROM Guest;");
			while (result.next()) {
				guestList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return guestList;
	}

	@Override
	public Guest parseResultSet(ResultSet result) {

		Guest guest = null;
		try {
			int id = result.getInt("idGuest");
			String name = result.getString("name");
			String pasport = result.getString("pasport");

			guest = new Guest(name, pasport);
			guest.setId(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return guest;
	}

	@Override
	public List<Guest> getListGuestSortedByName(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Guest> guestList = new ArrayList<Guest>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT * FROM Guest ORDER BY name;");
			while (result.next()) {
				guestList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return guestList;
	}

}
