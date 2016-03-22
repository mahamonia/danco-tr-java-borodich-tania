package com.danco.model.dao;

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
	public void create(DataSource source, Guest model) {
		try {
			Statement statement = source.openConnection().createStatement();
			int q = getIdForNewModel(source);
			statement.executeUpdate("INSERT INTO `Hotel_service`.`Guest` "
					+ "(`idGuest`, `name`, `pasport`) " + "VALUES ("
					+ q + ", '" + model.getName() + "', '"
					+ model.getPasport() + "' );");
			
			

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}

	}

	public int getIdForNewModel(DataSource source) {
		int id = 0;
		Statement statement = null;
		try {
			statement = source.openConnection().createStatement();
			ResultSet result = statement
					.executeQuery("SELECT * FROM Guest order by idGuest");

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
		Guest guest = getById(source, idModel);
		try {
			Statement statement = source.openConnection().createStatement();

			statement.executeUpdate("UPDATE  Guest SET idGuest = "
					+ guest.getId() + ", name = " + guest.getName()
					+ ", pasport = " + guest.getPasport() + ";");

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

			statement.executeUpdate("DELETE FROM Guest WHERE idGuest = "
					+ idModel + ";");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}
	}

	@Override
	public Guest getById(DataSource source, int idModel) {
		Statement statement = null;
		ResultSet result = null;
		try {
			statement = source.openConnection().createStatement();
			result = statement
					.executeQuery("SELECT * FROM Guest WHERE idGuest ="
							+ idModel + ";");

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}
		return parseResultSet(result);
	}

	@Override
	public List<Guest> getList(DataSource source) {
		Statement statement = null;
		ResultSet result = null;
		List<Guest> guestList = new ArrayList<Guest>();
		try {
			statement = source.openConnection().createStatement();
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
		}
		return guest;
	}

	public List<Guest> getListGuestSortedByName(DataSource source) {
		Statement statement = null;
		ResultSet result = null;
		List<Guest> guestList = new ArrayList<Guest>();
		try {
			statement = source.openConnection().createStatement();
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

	public List<Guest> getListGuestSortedByDateOutSettle(DataSource source) {
		Statement statement = null;
		ResultSet result = null;
		List<Guest> guestList = new ArrayList<Guest>();
		try {
			statement = source.openConnection().createStatement();
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
