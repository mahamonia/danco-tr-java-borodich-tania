package com.danco.training.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.entity.Room;

public class RoomDao implements BaseDao<Room> {

	private static final Logger LOGGER = LogManager.getLogger(RoomDao.class);

	private DataSource source = DataSource.getInstance();

	public RoomDao() {

	}

	@Override
	public void create(Room baseModel) {

		try {
			Statement statement = source.openConnection();

			statement
					.executeUpdate("INSERT INTO `Hotel_DB`.`Room` "
							+ "(`idRoom`, `number`, `content`, `stars`, `price`, `Status_idStatus`) "
							+ "VALUES (" + getIdForNewRoom() + ", "
							+ baseModel.getNumber() + ", "
							+ baseModel.getContent() + ", "
							+ baseModel.getStars() + ", "
							+ baseModel.getPrice() + ", 1);");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}

	}

	private int getIdForNewRoom() {
		int id = 0;
		Statement statement = source.openConnection();
		try (ResultSet result = statement
				.executeQuery("SELECT * FROM Room order by idRoom")) {

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
	public void update(Room baseModel) {
		try {
			Statement statement = source.openConnection();

			statement.executeUpdate("UPDATE  Room SET Room_idRoom = "
					+ baseModel.getId() + ", number = " + baseModel.getNumber()
					+ ", content = " + baseModel.getContent() + ", stars = "
					+ baseModel.getStars() + ", price = "
					+ baseModel.getPrice() + ", Status_idStatus = "
					+ baseModel.getStatus().ordinal() + ";");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}

	}

	@Override
	public void delete(Room baseModel) {
		try {
			Statement statement = source.openConnection();

			statement.executeUpdate("DELETE FROM Room WHERE Room_idRoom = "
					+ baseModel.getId() + ";");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}

	}

	@Override
	public Room getById() {
		// TODO Auto-generated method stub
		return null;
	}

}
