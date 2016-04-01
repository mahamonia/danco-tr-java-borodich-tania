package com.danco.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.danco.api.dao.IServiceDao;
import com.danco.model.entity.Chek;
import com.danco.model.entity.Guest;
import com.danco.model.entity.Room;
import com.danco.model.entity.Service;
import com.danco.model.entity.Status;

public class ServiceDao implements IServiceDao {

	@Override
	public void create(Session session, Service model) throws SQLException {
//		Statement statement = connect.createStatement();
//		statement.executeUpdate("INSERT INTO `Hotel_service`.`Service` "
//				+ "(`name`, `price`, `Chek_idChek`) " + "VALUES ('"
//				+ model.getName() + "', " + model.getPrice() + ", "
//				+ model.getChek().getId() + ");");
	}

	@Override
	public void update(Session session, Service model) throws SQLException {
		//Statement statement = connect.createStatement();
//		statement.executeUpdate("UPDATE  Service SET name = '"
//				+ model.getName() + "', price = " + ", Chek_idChek = "
//				+ model.getChek().getId() + " WHERE idService = "
//				+ model.getId() + ";");
	}

	@Override
	public void delete(Session session, int idModel) throws SQLException {
//		Statement statement = connect.createStatement();
//		statement.executeUpdate("DELETE FROM Service WHERE idService = "
//				+ idModel + ";");
	}

	@Override
	public Service getById(Session session, int idModel) throws SQLException {
		Statement statement = null;
		ResultSet result = null;
		Service service = null;
	//	statement = connect.createStatement();
		result = statement
				.executeQuery("SELECT Service.idService, Service.name, Service.price AS priceService,"
						+ " Service.Chek_idChek, Chek.date_in_settle, Chek.date_out_settle, Chek.status,"
						+ "Chek.Guest_idGuest, Guest.name AS nameGuest, Guest.pasport,"
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
		return service;
	}

	@Override
	public List<Service> getList(Session session) throws SQLException {
		Statement statement = null;
		ResultSet result = null;
		List<Service> serviceList = new ArrayList<Service>();
	//	statement = connect.createStatement();
		result = statement
				.executeQuery("SELECT Service.idService, Service.name, Service.price AS priceService,"
						+ " Service.Chek_idChek, Chek.date_in_settle, Chek.date_out_settle, Chek.status,"
						+ " Chek.Guest_idGuest, Guest.name AS nameGuest, Guest.pasport,"
						+ " Chek.Room_idRoom, Room.number, Room.content, Room.price AS priceRoom, Room.stars, Room.idStatus"
						+ " FROM Service JOIN Chek"
						+ " ON Service.Chek_idChek=Chek.idChek"
						+ " left JOIN Guest"
						+ " ON Chek.Guest_idGuest=Guest.idGuest"
						+ " LEFT JOIN Room" + " ON Chek.Room_idRoom=Room.idRoom;");
		while (result.next()) {
			serviceList.add(parseResultSet(result));
		}
		return serviceList;
	}

	private Service parseResultSet(ResultSet result) throws SQLException {
		Service service = null;
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

		int idRoom = result.getInt("Room_idRoom");
		int number = result.getInt("number");
		int content = result.getInt("content");
		int stars = result.getInt("stars");
		int priceRoom = result.getInt("priceRoom");
		Status statusRoom = Status.values()[result.getInt("idStatus") - 1];
		Room room = new Room(number, content, statusRoom, stars, priceRoom);
		room.setId(idRoom);

		Chek chek = new Chek(dateInSettle, dateOutSettle, guest, room);
		chek.setId(idChek);
		chek.setStatus(status);

		service = new Service(name, priceService);
		service.setChek(chek);
		service.setId(idService);
		return service;
	}

	@Override
	public List<Service> getListServiceSorted(Session session, String param)
			throws SQLException {
		Statement statement = null;
		ResultSet result = null;
		List<Service> serviceList = new ArrayList<Service>();
		//statement = connect.createStatement();
		result = statement
				.executeQuery("select Service.idService, Service.name, Service.price AS priceService,"
						+ " Service.Chek_idChek, Chek.date_in_settle, Chek.date_out_settle, Chek.status,"
						+ " Chek.Guest_idGuest, Guest.name AS nameGuest,Guest.pasport,"
						+ " Chek.Room_idRoom, Room.number, Room.content, Room.price AS priceRoom, Room.stars, Room.idStatus"
						+ " from Service join Chek"
						+ " on Service.Chek_idChek=Chek.idChek"
						+ " left join Guest"
						+ " on Chek.Guest_idGuest=Guest.idGuest"
						+ " left join Room"
						+ " on Chek.Room_idRoom=Room.idRoom ORDER BY '"
						+ param
						+ "';");
		while (result.next()) {
			serviceList.add(parseResultSet(result));
		}
		return serviceList;

	}

	@Override
	public List<Service> getGuestThemServices(Session session, int idGuest)
			throws SQLException {
//		Statement statement = null;
//		ResultSet result = null;
		List<Service> serviceList = new ArrayList<Service>();
//		statement = connect.createStatement();
//		result = statement
//				.executeQuery("SELECT Service.idService, Service.name, Service.price AS priceService,"
//						+ " Service.Chek_idChek, Chek.date_in_settle, Chek.date_out_settle, Chek.status,"
//						+ " Chek.Guest_idGuest, Guest.name AS nameGuest,"
//						+ " Chek.Room_idRoom, Room.number, Room.content, Room.price AS priceRoom, Room.stars, Room.idStatus"
//						+ " FROM Service JOIN Chek"
//						+ " ON Service.Chek_idChek=Chek.idChek"
//						+ " left JOIN Guest"
//						+ " ON Chek.Guest_idGuest=Guest.idGuest"
//						+ " LEFT JOIN Room"
//						+ " ON Chek.Room_idRoom=Room.idRoom "
//						+ " Where Guest.idGuest=" + idGuest + ";");
//		while (result.next()) {
//			serviceList.add(parseResultSet(result));
//		}
		return serviceList;
	}

	@Override
	public int getSumServiceForGuest(Session session, int idGuest)
			throws SQLException {
//		Statement statement = null;
//		ResultSet result = null;
		int sum = 0;
//		statement = connect.createStatement();
//		result = statement
//				.executeQuery("SELECT sum(Service.price) AS sum, Service.Guest_idGuest, guest.name as nameGuest, guest.pasport"
//						+ "FROM Service join Guest "
//						+ "ON Service.Guest_idGuest = Guest.idGuest"
//						+ "WHERE Guest.idGuest = " + idGuest + ";");
//		sum = result.getInt("sum");
		return sum;
	}
}
