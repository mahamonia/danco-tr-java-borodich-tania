package com.danco.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.danco.api.dao.IChekDao;
import com.danco.model.entity.Chek;
import com.danco.model.entity.Guest;
import com.danco.model.entity.Room;
import com.danco.model.entity.Service;
import com.danco.model.entity.Status;

public class ChekDao implements IChekDao {

	@Override
	public void create(Connection connect, Chek model) throws SQLException {
		Statement statement = connect.createStatement();
		statement
				.executeUpdate("INSERT INTO `Hotel_service`.`Chek` "
						+ "(`date_in_settle`, `date_out_settle`, `status`, `Room_idRoom`, `Guest_idGuest`) "
						+ "VALUES ('" + model.getDateInSettle() + "', '"
						+ model.getDateOutSettle() + "', " + model.isStatus()
						+ ", " + model.getRoom().getId() + ", "
						+ model.getGuest().getId() + ");");
	}

	@Override
	public void update(Connection connect, Chek model) throws SQLException {
		Statement statement = connect.createStatement();
		statement.executeUpdate("UPDATE  Chek SET date_in_settle = '"
				+ model.getDateInSettle() + "', date_out_settle = '"
				+ model.getDateOutSettle() + "', status = " + model.isStatus()
				+ ", Room_idRoom = " + model.getRoom().getId()
				+ ", Guest_idGuest = " + model.getGuest().getId()
				+ " WHERE idChek = " + model.getId() + ";");
	}

	@Override
	public void delete(Connection connect, int idModel) throws SQLException {
		Statement statement = connect.createStatement();
		statement.executeUpdate("DELETE FROM Chek WHERE idChek = " + idModel
				+ ";");
	}

	@Override
	public Chek getById(Connection connect, int idModel) throws SQLException {
		Statement statement = null;
		ResultSet result = null;
		List<Chek> chekList = new ArrayList<Chek>();
		statement = connect.createStatement();
		result = statement
				.executeQuery("SELECT Chek.idChek, Chek.date_in_settle, Chek.date_out_settle,"
						+ " Chek.status, Chek.Guest_idGuest, Guest.name, Guest.pasport,"
						+ " Chek.Room_idRoom, Room.number, Room.content,"
						+ " Room.price, Room.stars, Room.idStatus"
						+ " Service.idService, Service.name AS nameService, Service.price"
						+ " FROM Chek JOIN Guest"
						+ " ON Chek.Guest_idGuest = Guest.idGuest"
						+ " LEFT JOIN Room"
						+ " ON Chek.Room_idRoom = Room.idRoom"
						+ " LEFT JOIN Service"
						+ " ON idService = Service.Chek_idChek"
						+ " WHERE idChek = " + idModel + ";");
		while(result.next()) {
			Chek chek = parseResultForChek(result);
			Service service = parseResultForService(result);
			chekList = addChekInList(chekList, service,chek) ;
		}
		return chekList.get(0);
	}
	@Override
	public List<Chek> getList(Connection connect) throws SQLException {
		Statement statement = null;
		ResultSet result = null;
		List<Chek> chekList = new ArrayList<Chek>();
		statement = connect.createStatement();
		result = statement
				.executeQuery("SELECT Chek.idChek, Chek.date_in_settle, Chek.date_out_settle,"
						+ " Chek.status, Chek.Guest_idGuest, Guest.name, Guest.pasport,"
						+ " Chek.Room_idRoom, Room.number, Room.content,"
						+ " Room.price, Room.stars, Room.idStatus"
						+ " Service.idService, Service.name AS nameService, Service.price"
						+ " FROM Chek JOIN Guest"
						+ " ON Chek.Guest_idGuest = Guest.idGuest"
						+ " LEFT JOIN Room"
						+ " ON Chek.Room_idRoom = Room.idRoom"
						+ "	LEFT JOIN Service"
						+ " ON idService = Service.Chek_idChek;");

		while (result.next()) {
			Chek chek = parseResultForChek(result);
			Service service = parseResultForService(result);
			
			chekList = addChekInList(chekList, service,chek) ;
			
		}
		return chekList;
	}

	private Chek parseResultForChek(ResultSet result) throws SQLException {

		Chek chek = null;
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

		chek = new Chek(dateInSettle, dateOutSettle, guest, room);
		chek.setId(idCheck);
		chek.setStatus(status);
		
		return chek;
	}
	
	private Service parseResultForService(ResultSet result) throws SQLException{
		
		int idService = result.getInt("idService");
		String nameService = result.getString("nameService");
		int priceService = result.getInt("priceService");

		Service service = new Service(nameService, priceService);
		service.setId(idService);
		return service;
	}

	private void addServiceForChek(Service service, Chek chek){
		List<Service> serviceList = new ArrayList<Service>();
		if (chek.getServiceList()!=null){
			serviceList = chek.getServiceList();
		} 
		serviceList.add(service);
			chek.setServiceList(serviceList);	
	}
	
	private List<Chek> addChekInList(List<Chek> chekList, Service service,
			Chek chek) {

		if (chekList.size() == 0) {
			addServiceForChek(service, chek);
			chekList.add(chek);
		} else {
			boolean isChek = false;
			int index = 0;
			
			for (int i = 0; i < chekList.size(); i++) {
				if (chekList.get(i).getId() == chek.getId()) {
					isChek = true;
					index = i;
				}
			}
			// if Chek is exist - modify
			if (isChek) {
				chek = chekList.get(index);
				addServiceForChek(service, chek);
				chekList.set(index, chek);
			} else {
				addServiceForChek(service, chek);
				chekList.add(chek);
			}
		}
		return chekList;
	}



	@Override
	public List<Chek> getListChekSorted(Connection connect, String param)
			throws SQLException {
		Statement statement = null;
		ResultSet result = null;
		List<Chek> chekList = new ArrayList<Chek>();
		statement = connect.createStatement();
		result = statement
				.executeQuery("SELECT Chek.idChek, Chek.date_in_settle, Chek.date_out_settle,"
						+ " Chek.status, Chek.Guest_idGuest, Guest.name, Guest.pasport,"
						+ " Chek.Room_idRoom, Room.number, Room.content,"
						+ " Room.price, Room.stars, Room.idStatus"
						+ " Service.idService, Service.name AS nameService, Service.price"
						+ " FROM Chek JOIN Guest"
						+ " ON Chek.Guest_idGuest = Guest.idGuest"
						+ " LEFT JOIN Room"
						+ " ON Chek.Room_idRoom = Room.idRoom"
						+ " LEFT JOIN Service"
						+ " ON idService = Service.Chek_idChek"
						+ " ORDER BY '"	+ param + "';");

		while (result.next()) {
			chekList.add(parseResultForChek(result));
		}
		return chekList;
	}

	@Override
	public Chek getChekForIdGuest(Connection connect, int idGuest)
			throws SQLException {
		Statement statement = null;
		ResultSet result = null;
		Chek chek = null;
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
			chek = parseResultForChek(result);
		}
		return chek;
	}
}
