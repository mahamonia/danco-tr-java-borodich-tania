package com.danco.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.danco.api.dao.IGuestDao;
import com.danco.model.entity.Chek;
import com.danco.model.entity.Guest;
import com.danco.model.entity.Room;
import com.danco.model.entity.Status;

public class GuestDao implements IGuestDao {

	@Override
	public void create(Connection connect, Guest model) throws SQLException {
		Statement statement = connect.createStatement();
		statement.executeUpdate("INSERT INTO `Hotel_service`.`Guest` "
				+ "(`name`, `pasport`) " + "VALUES ('" + model.getName()
				+ "', '" + model.getPasport() + "' );");
	}

	@Override
	public void update(Connection connect, Guest model) throws SQLException {
		Statement statement = connect.createStatement();
		statement.executeUpdate("UPDATE  Guest SET name = " + model.getName()
				+ ", pasport = " + model.getPasport() + "WHERE idGuest = "
				+ model.getId() + ";");
	}

	@Override
	public void delete(Connection connect, int idModel) throws SQLException {
		Statement statement = connect.createStatement();
		statement.executeUpdate("DELETE FROM Guest WHERE idGuest = " + idModel
				+ ";");
	}

	@Override
	public Guest getById(Connection connect, int idModel) throws SQLException {
		Statement statement = null;
		ResultSet result = null;
		Guest guest = null;
		List<Guest> guestList = new ArrayList<Guest>();
		statement = connect.createStatement();
		result = statement.executeQuery("SELECT * FROM guest JOIN chek"
				+ " ON Guest_idGuest = idGuest" + " LEFT JOIN Room"
				+ " ON Room_idRoom = idRoom WHERE idGuest =" + idModel + ";");
		result.absolute(1);
		
		while (result.next()) {		
			guest = parseResultForGuest(result);
			Chek chek = parseResultForChek(result);
			guestList = addGuestInList(guestList, guest, chek);
		}
		return guestList.get(0);
	}

	@Override
	public List<Guest> getList(Connection connect) throws SQLException {
		Statement statement = null;
		ResultSet result = null;
		List<Guest> guestList = new ArrayList<Guest>();
		statement = connect.createStatement();
		result = statement.executeQuery("SELECT * FROM Guest JOIN Chek"
				+ " ON Guest_idGuest = idGuest" + " LEFT JOIN Room"
				+ " ON Room_idRoom = idRoom;");

		while (result.next()) {

			Guest guest = parseResultForGuest(result);
			Chek chek = parseResultForChek(result);

			guestList = addGuestInList(guestList, guest, chek);
		}
		return guestList;
	}

	private Guest parseResultForGuest(ResultSet result) throws SQLException {
		Guest guest = null;
		int id = result.getInt("idGuest");
		String name = result.getString("name");
		String pasport = result.getString("pasport");

		guest = new Guest(name, pasport);
		guest.setId(id);

		return guest;
	}

	private Chek parseResultForChek(ResultSet result) throws SQLException {
		Chek chek = null;
		int idRoom = result.getInt("Room_idRoom");
		int number = result.getInt("number");
		int content = result.getInt("content");
		int stars = result.getInt("stars");
		int price = result.getInt("price");
		Status statusRoom = Status.values()[result.getInt("idStatus") - 1];

		Room room = new Room(number, content, statusRoom, stars, price);
		room.setId(idRoom);

		int idCheck = result.getInt("idChek");
		Date dateInSettle = result.getDate("date_in_settle");
		Date dateOutSettle = result.getDate("date_out_settle");
		boolean statusChek = result.getBoolean("status");

		chek = new Chek(dateInSettle, dateOutSettle, null, room);
		chek.setStatus(statusChek);
		chek.setId(idCheck);

		return chek;
	}

	private List<Guest> addGuestInList(List<Guest> guestList, Guest guest,
			Chek chek) {

		if (guestList.size() == 0) {
			addChekForGuest(guest, chek);
			guestList.add(guest);
		} else {
			boolean isGuest = false;
			int index = 0;
			
			for (int i = 0; i < guestList.size(); i++) {
				if (guestList.get(i).getId() == guest.getId()) {
					isGuest = true;
					index = i;
				}
			}
			// if guest is exist - modify
			if (isGuest) {
				guest = guestList.get(index);
				addChekForGuest(guest, chek);
				guestList.set(index, guest);
			} else {
				addChekForGuest(guest, chek);
				guestList.add(guest);
			}
		}
		return guestList;
	}

	private void addChekForGuest(Guest guest, Chek chek) {
		List<Chek> chekList = new ArrayList<Chek>();
		if (guest.getChekList()!=null){
			chekList = guest.getChekList();
		} 
			chekList.add(chek);
			guest.setChekList(chekList);		
	}

	@Override
	public List<Guest> getListGuestSorted(Connection connect, String param)
			throws SQLException {
		Statement statement = null;
		ResultSet result = null;
		List<Guest> guestList = new ArrayList<Guest>();
		statement = connect.createStatement();
		result = statement.executeQuery("SELECT * FROM Guest ORDER BY '"
				+ param + "';");
		while (result.next()) {
			guestList.add(parseResultForGuest(result));
		}
		return guestList;
	}

}
