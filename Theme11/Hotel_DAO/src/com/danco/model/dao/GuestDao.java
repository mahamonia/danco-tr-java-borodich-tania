package com.danco.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.model.entity.Guest;

public class GuestDao implements BaseDao<Guest> {

	private static final Logger LOGGER = LogManager.getLogger(GuestDao.class);

	@Override
	public void create(Connection connect, Guest model) {
		try {
			Statement statement = connect.createStatement();
			int q = getIdForNewModel(connect);
			statement.executeUpdate("INSERT INTO `Hotel_service`.`Guest` "
					+ "(`idGuest`, `name`, `pasport`) " + "VALUES ("
					+ q + ", '" + model.getName() + "', '"
					+ model.getPasport() + "' );");
			
			

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} 
	}

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
	public void update(Connection connect, int idModel) {
		Guest guest = getById(connect, idModel);
		try {
			Statement statement = connect.createStatement();

			statement.executeUpdate("UPDATE  Guest SET idGuest = "
					+ guest.getId() + ", name = " + guest.getName()
					+ ", pasport = " + guest.getPasport() + ";");

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
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT * FROM Guest WHERE idGuest ="
							+ idModel + ";");

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} 
		return parseResultSet(result);
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
			if (result.next()){
			int id = result.getInt("idGuest");
			String name = result.getString("name");
			String pasport = result.getString("pasport");

			guest = new Guest(name, pasport);
			guest.setId(id);
			}
		} catch (Exception e) {
		}
		return guest;
	}

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

	public List<Guest> getListGuestSortedByDateOutSettle(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Guest> guestList = new ArrayList<Guest>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT guest.idGuest, guest.name, guest.pasport "
							+ "FROM `check` join guest ON check.idGuest=guest.idGuest "
							+ "ORDER BY date_out_settle;");

			while (result.next()) {
				guestList.add(parseResultSet(result));
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return guestList;

	}

}
