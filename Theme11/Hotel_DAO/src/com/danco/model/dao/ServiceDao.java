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

import com.danco.api.dao.IServiceDao;
import com.danco.model.entity.Chek;
import com.danco.model.entity.Guest;
import com.danco.model.entity.Room;
import com.danco.model.entity.Service;
import com.danco.model.entity.Status;

public class ServiceDao implements IServiceDao {

	private static final Logger LOGGER = LogManager.getLogger(ServiceDao.class);

	@Override
	public void create(Connection connect, Service model) {
		try {
			Statement statement = connect.createStatement();
			int id = getIdForNewModel(connect);
			statement.executeUpdate("INSERT INTO `Hotel_service`.`Service` "
					+ "(`idService`, `name`, `price`, `Chek_idChek`) "
					+ "VALUES (" + id + ", '" + model.getName() + "', "
					+ model.getPrice() + ", " + model.getChek().getId() + ");");
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
					.executeQuery("SELECT * FROM Service ORDER BY idService");

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
	public void update(Connection connect, Service model) {
		try {
			Statement statement = connect.createStatement();
			statement.executeUpdate("UPDATE  Service SET name = '"
					+ model.getName() + "', price = " + ", Chek_idChek = "
					+ model.getChek().getId() + " WHERE idService = "
					+ model.getId() + ";");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void delete(Connection connect, int idModel) {
		try {
			Statement statement = connect.createStatement();
			statement.executeUpdate("DELETE FROM Service WHERE idService = "
					+ idModel + ";");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public Service getById(Connection connect, int idModel) {
		Statement statement = null;
		ResultSet result = null;
		Service service = null;
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT Service.idService, Service.name, Service.price AS priceService,"
							+ " Service.Chek_idChek, Chek.date_in_settle, Chek.date_out_settle, Chek.status,"
							+ "Chek.Guest_idGuest, Guest.name AS nameGuest, Guest.name,"
							+ "Chek.Room_idRoom, Room.number, Room.content, Room.price AS priceRoom, Room.stars, Room.idStatus"
							+ "FROM Service JOIN Chek"
							+ "ON Service.Chek_idChek=Chek.idChek"
							+ "left JOIN Guest"
							+ "ON Chek.Guest_idGuest=Guest.idGuest"
							+ "LEFT JOIN Room"
							+ "ON Chek.Room_idRoom=Room.idRoom"
							+ "WHERE idService =" + idModel + ";");
			if (result.next()) {
				service = parseResultSet(result);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return service;
	}

	@Override
	public List<Service> getList(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Service> serviceList = new ArrayList<Service>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT Service.idService, Service.name, Service.price AS priceService,"
							+ " Service.Chek_idChek, Chek.date_in_settle, Chek.date_out_settle, Chek.status,"
							+ "Chek.Guest_idGuest, Guest.name AS nameGuest,"
							+ "Chek.Room_idRoom, Room.number, Room.content, Room.price AS priceRoom, Room.stars, Room.idStatus"
							+ "FROM Service JOIN Chek"
							+ "ON Service.Chek_idChek=Chek.idChek"
							+ "left JOIN Guest"
							+ "ON Chek.Guest_idGuest=Guest.idGuest"
							+ "LEFT JOIN Room"
							+ "ON Chek.Room_idRoom=Room.idRoom;");
			while (result.next()) {
				serviceList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return serviceList;
	}

	@Override
	public Service parseResultSet(ResultSet result) {
		Service service = null;
		try {
			int idService = result.getInt("idService");
			String name = result.getString("name");
			int priceService = result.getInt("priceService");

			int idChek = result.getInt("Chek_idChek");
			Date dateInSettle = result.getDate("date_in_settle");
			Date dateOutSettle = result.getDate("date_out_settle");
			boolean status = result.getBoolean("status");

			int idGuest = result.getInt("Guest_idGuest");
			String nameGuest = result.getString("nameGuest");
			String pasport = result.getString("pasport");
			Guest guest = new Guest(nameGuest, pasport);
			guest.setId(idGuest);

			int idRoom = result.getInt("idRoom");
			int number = result.getInt("number");
			int content = result.getInt("content");
			int stars = result.getInt("stars");
			int priceRoom = result.getInt("priceRoom");
			Status statusRoom = Status.values()[result.getInt("idStatus") - 1];
			Room room = new Room(number, content, statusRoom, stars, priceRoom);
			room.setId(idRoom);

			Chek chek = new Chek(dateInSettle, dateOutSettle, status, guest,
					room);
			chek.setId(idChek);

			service = new Service(name, priceService);
			service.setChek(chek);
			service.setId(idService);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return service;
	}

	@Override
	public List<Service> getListServiceSortedByPrice(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Service> serviceList = new ArrayList<Service>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT Service.idService, Service.name, Service.price AS priceService,"
							+ " Service.Chek_idChek, Chek.date_in_settle, Chek.date_out_settle, Chek.status,"
							+ "Chek.Guest_idGuest, Guest.name AS nameGuest,"
							+ "Chek.Room_idRoom, Room.number, Room.content, Room.price AS priceRoom, Room.stars, Room.idStatus"
							+ "FROM Service JOIN Chek"
							+ "ON Service.Chek_idChek=Chek.idChek"
							+ "left JOIN Guest"
							+ "ON Chek.Guest_idGuest=Guest.idGuest"
							+ "LEFT JOIN Room"
							+ "ON Chek.Room_idRoom = Room.idRoom ORDER BY priceService;");
			while (result.next()) {
				serviceList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return serviceList;

	}

	@Override
	public List<Service> getListServiceSortedByName(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Service> serviceList = new ArrayList<Service>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT Service.idService, Service.name, Service.price AS priceService,"
							+ " Service.Chek_idChek, Chek.date_in_settle, Chek.date_out_settle, Chek.status,"
							+ "Chek.Guest_idGuest, Guest.name AS nameGuest,"
							+ "Chek.Room_idRoom, Room.number, Room.content, Room.price AS priceRoom, Room.stars, Room.idStatus"
							+ "FROM Service JOIN Chek"
							+ "ON Service.Chek_idChek=Chek.idChek"
							+ "left JOIN Guest"
							+ "ON Chek.Guest_idGuest=Guest.idGuest"
							+ "LEFT JOIN Room"
							+ "ON Chek.Room_idRoom=Room.idRoom ORDER BY name;");
			while (result.next()) {
				serviceList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return serviceList;

	}

	@Override
	public List<Service> getGuestThemServices(Connection connect, int idGuest) {
		Statement statement = null;
		ResultSet result = null;
		List<Service> serviceList = new ArrayList<Service>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT Service.idService, Service.name, Service.price AS priceService,"
							+ " Service.Chek_idChek, Chek.date_in_settle, Chek.date_out_settle, Chek.status,"
							+ "Chek.Guest_idGuest, Guest.name AS nameGuest,"
							+ "Chek.Room_idRoom, Room.number, Room.content, Room.price AS priceRoom, Room.stars, Room.idStatus"
							+ "FROM Service JOIN Chek"
							+ "ON Service.Chek_idChek=Chek.idChek"
							+ "left JOIN Guest"
							+ "ON Chek.Guest_idGuest=Guest.idGuest"
							+ "LEFT JOIN Room"
							+ "ON Chek.Room_idRoom=Room.idRoom "
							+ "Where Guest.idGuest=" + idGuest + ";");
			while (result.next()) {
				serviceList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return serviceList;
	}

	@Override
	public int getSumServiceForGuest(Connection connect, int idGuest) {
		Statement statement = null;
		ResultSet result = null;
		int sum = 0;
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT sum(Service.price) AS sum, Service.Guest_idGuest, guest.name as nameGuest, guest.pasport"
							+ "FROM Service join Guest "
							+ "ON Service.Guest_idGuest = Guest.idGuest"
							+ "WHERE Guest.idGuest = " + idGuest + ";");
			sum = result.getInt("sum");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return sum;
	}
}
