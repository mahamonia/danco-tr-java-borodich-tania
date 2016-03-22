package com.danco.model.dao;

import java.sql.Connection;
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
	public void create(Connection connect, Check model) {
		try {
			Statement statement = connect.createStatement();

			statement
					.executeUpdate("INSERT INTO `Hotel_service`.`Check` "
							+ "(`idCheck`, `date_in_settle`, `date_out_settle`, `status`, `idRoom`, `idGuest`) "
							+ "VALUES (" + getIdForNewModel(connect) + ", '"
							+ model.getDateInSettle() + "', '"
							+ model.getDateOutSettle() + "', '"
							+ model.isStatus() + "', " + model.getIdGuest()
							+ ", " + model.getIdRoom() + ";");

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
					.executeQuery("SELECT * FROM `Check` order by idCheck");

			result.last();
			id = result.getInt(1);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} 
		return id + 1;

	}

	@Override
	public void update(Connection connect, int idModel) {
		Check check = getById(connect, idModel);
		try {
			Statement statement = connect.createStatement();

			statement.executeUpdate("UPDATE  `Check` SET idCheck = "
					+ check.getId() + ", date_in_settle = '"
					+ check.getDateInSettle() + "', date_out_settle = '"
					+ check.getDateOutSettle() + "', status = "
					+ check.isStatus() + ", idRoom = " + check.getIdRoom()
					+ ", idGuest = " + check.getIdGuest() + ";");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} 

	}

	@Override
	public void delete(Connection connect, int idModel) {
		try {
			Statement statement = connect.createStatement();

			statement.executeUpdate("DELETE FROM `Check` WHERE idCheck = "
					+ idModel + ";");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} 

	}

	@Override
	public Check getById(Connection connect, int idModel) {
		Statement statement = null;
		ResultSet result = null;
		try {
			statement = connect.createStatement();

			result = statement
					.executeQuery("SELECT * FROM Check WHERE idCheck ="
							+ idModel + ";");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} 
		return parseResultSet(result);
	}

	@Override
	public List<Check> getList(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Check> checkList = new ArrayList<Check>();
		try {
			statement = connect.createStatement();
			result = statement.executeQuery("SELECT * FROM `Check`;");

			while (result.next()) {
				checkList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} 
		return checkList;
	}

	@Override
	public Check parseResultSet(ResultSet result) {

		Check check = null;
		try {
			if (result.next()){
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
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return check;
	}

	public int getIdCheckForIdGuest(Connection connect, int idGuest) {
		Statement statement = null;
		ResultSet result = null;
		int idCheck = 0;
		try {
			statement = connect.createStatement();
			result = statement.executeQuery("SELECT idCheck	FROM `Check` "
					+ "WHERE Check.idGuest = " + idGuest + ";");

			idCheck = result.getInt("idCheck");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} 

		return idCheck;

	}
}
