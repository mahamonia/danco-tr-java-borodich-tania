package com.danco.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.model.entity.Check;

public class CheckDao implements BaseDao<Check> {

	private static final Logger LOGGER = LogManager.getLogger(CheckDao.class);

	@Override
	public void create(DataSource source, Check model) {
		try {
			Statement statement = source.openConnection().createStatement();

			statement
					.executeUpdate("INSERT INTO `Hotel_service`.`Check` "
							+ "(`idCheck`, `date_in_settle`, `date_out_settle`, `status`, `idRoom`, `idGuest`) "
							+ "VALUES (" + getIdForNewModel(source) + ", '"
							+ model.getDateInSettle() + "', '"
							+ model.getDateOutSettle() + "', '"
							+ model.isStatus() + "', " + model.getIdGuest()
							+ ", " + model.getIdRoom() + ";");

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
					.executeQuery("SELECT * FROM `Check` order by idCheck");

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
		Check check = getById(source, idModel);
		try {
			Statement statement = source.openConnection().createStatement();

			statement.executeUpdate("UPDATE  `Check` SET idCheck = "
					+ check.getId() + ", date_in_settle = '"
					+ check.getDateInSettle() + "', date_out_settle = '"
					+ check.getDateOutSettle() + "', status = "
					+ check.isStatus() + ", idRoom = " + check.getIdRoom()
					+ ", idGuest = " + check.getIdGuest() + ";");

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

			statement.executeUpdate("DELETE FROM `Check` WHERE idCheck = "
					+ idModel + ";");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}

	}

	@Override
	public Check getById(DataSource source, int idModel) {
		Statement statement = null;
		ResultSet result = null;
		try {
			statement = source.openConnection().createStatement();

			result = statement
					.executeQuery("SELECT * FROM Check WHERE idCheck ="
							+ idModel + ";");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}
		return parseResultSet(result);
	}

	@Override
	public List<Check> getList(DataSource source) {
		Statement statement = null;
		ResultSet result = null;
		List<Check> checkList = new ArrayList<Check>();
		try {
			statement = source.openConnection().createStatement();
			result = statement.executeQuery("SELECT * FROM `Check`;");

			while (result.next()) {
				checkList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}
		return checkList;
	}

	@Override
	public Check parseResultSet(ResultSet result) {

		Check check = null;
		try {

			int idCheck = result.getInt("idCheck");
			LocalDateTime dateInSettle = LocalDateTime.parse(result
					.getString("date_in_settle"));
			LocalDateTime dateOutSettle = LocalDateTime.parse(result
					.getString("date_out_settle"));

			boolean status = result.getBoolean("status");

			int idGuest = result.getInt("idGuest");
			int idRoom = result.getInt("idRoom");

			check = new Check(dateInSettle, dateOutSettle, status, idGuest,
					idRoom);
			check.setId(idCheck);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return check;
	}

	public int getIdCheckForIdGuest(DataSource source, int idGuest) {
		Statement statement = null;
		ResultSet result = null;
		int idCheck = 0;
		try {
			statement = source.openConnection().createStatement();
			result = statement.executeQuery("SELECT idCheck	FROM `Check` "
					+ "WHERE Check.idGuest = " + idGuest + ";");

			idCheck = result.getInt("idCheck");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}

		return idCheck;

	}
}
