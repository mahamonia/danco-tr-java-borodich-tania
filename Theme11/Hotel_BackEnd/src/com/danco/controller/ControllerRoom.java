package com.danco.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.ConfigProperty;
import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerRoom;
import com.danco.api.backend.IParseUtilityCSVForRoom;
import com.danco.comparator.Comparator;
import com.danco.training.dao.RoomDao;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Room;
import com.danco.training.entity.Status;

public class ControllerRoom implements IControllerRoom {

	private static Logger LOGGER = LogManager.getLogger(ControllerRoom.class);

	private RoomDao roomDao;

	@ConfigProperty(configName = "property.properties", propertyName = "status", type = "boolean")
	private boolean statusRoom;

	private final String MESSAGE_1 = "Prohibited privacy settings";

	@Injection
	private IParseUtilityCSVForRoom utility;

	public ControllerRoom(RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	@Override
	public void createRoom(Room room) {
		roomDao.create(room);
	}


	@Override
	public void updateRoom(Room room) {
		roomDao.update(room);
	}

	@Override
	public void deleteRoom(Room room) {
		roomDao.delete(room);
	}

	@Override
	public Room getRoomByNumber(int number) {
		// try {
		// for (int i = 0; i < this.roomsList.size(); i++) {
		// if (this.roomsList.get(i).getNumber() == number) {
		// return this.roomsList.get(i);
		// }
		// }
		// } catch (Exception e) {
		// LOGGER.error(e.getMessage());
		// }
		return null;
	}

	@Override
	public String[] getListRoom() {
		String[] arrayStr = null;
		
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Hotel_DB", "root", "root");
			statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Room"); 
			int i = 0;
			result.last();
			int size = result.getRow();
			result.beforeFirst();

			arrayStr = new String[size];
			StringBuffer str = new StringBuffer();
			while (result.next()) {
				String userid = result.getString("idRoom");
				String number = result.getString("number");
				String price = result.getString("price");
				str.append("Id - ").append(userid).append(" number: ")
						.append(number).append(" price: ").append(price);
				arrayStr[i] = str.toString();
				str.delete(0, Integer.MAX_VALUE);
				i++;
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return arrayStr;
	}


	@Override
	public String[] getListThreeLastGuestsOfRoom(int idRoom) {
		String[] arrayStr = null;

//		try (ResultSet result = stat
//				.executeQuery("select * from history_room join guest "
//						+ "on guest.idGuest = history_room.Guest_idGuest "
//						+ " where history_room.idHistory_Room=" + idRoom +"limit 3")) {
//			int i = 0;
//			result.last();
//			int size = result.getRow();
//			result.beforeFirst();
//
//			arrayStr = new String[size];
//			StringBuffer str = new StringBuffer();
//			while (result.next()) {
//				String userid = result.getString("idGuest");
//				String username = result.getString("name");
//				String userpasport = result.getString("pasport");
//				str.append("Id - ").append(userid).append(" name: ")
//						.append(username).append(" pasport: ")
//						.append(userpasport);
//				arrayStr[i] = str.toString();
//				i++;
//
//			}
//
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		}

		return arrayStr;

	}

	@Override
	public String[] printRoomSortedByContetn() {

		try {
			//Collections.sort(roomsList, Comparator.ROOM_BY_CONTENT);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		//return roomsList;
		return null;
	}

	@Override
	public List<Room> printRoomSortedByNumber(List<Room> roomsList) {
		try {
			Collections.sort(roomsList, Comparator.ROOM_BY_NUMBER);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return roomsList;
	}

	@Override
	public List<Room> printRoomSortedByPrice(List<Room> roomsList) {

		try {
			Collections.sort(roomsList, Comparator.ROOM_BY_PRICE);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return roomsList;
	}

	@Override
	public List<Room> printRoomSortedByStars(List<Room> roomsList) {

		try {
			Collections.sort(roomsList, Comparator.ROOM_BY_STARS);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return roomsList;
	}

	@Override
	public List<Room> getRoomListFree() {

		List<Room> roomFreeList = new ArrayList<Room>();

		// try {
		// for (int i = 0; i < this.roomsList.size(); i++) {
		// if (this.roomsList.get(i).getStatus() == Status.FREE) {
		// roomFreeList.add(this.roomsList.get(i));
		// }
		// }
		// } catch (Exception e) {
		// LOGGER.error(e.getMessage());
		// }
		return roomFreeList;
	}

	@Override
	public int printAmountRoomFree() {
		int amountFree = 0;

		// try {
		// for (int i = 0; i < this.roomsList.size(); i++) {
		// if (this.roomsList.get(i).getStatus() == Status.FREE) {
		// amountFree++;
		// }
		// }
		// } catch (Exception e) {
		// LOGGER.error(e.getMessage());
		// }
		return amountFree;
	}

	@Override
	public List<Guest> printRoomThemGuests(Room room, List<Guest> guestsList) {

		List<Guest> newGuestList = new ArrayList<>();

		try {

			List<Guest> guestList = room.getGuestList();
			for (int i = guestList.size(); i > guestList.size() - 3; i--) {
				newGuestList.add(guestList.get(i));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return newGuestList;
	}

	@Override
	public void changeRoomStatus(Room room, Status status) {
		try {
			if (statusRoom == true) {
				room.setStatus(status);
			} else {
				System.out.println(MESSAGE_1);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void changeRoomPrice(Room room, int price) {

		try {
			room.setPrice(price);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public Room cloneRoom(Room room) {
		Room clon = room;
//		try {
//			clon = room.clone();
//			int number = getNumberForNewRoom();
//			clon.setNumber(number);
//		} catch (CloneNotSupportedException e) {
//			LOGGER.error(e.getMessage());
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		}
		return clon;
	}

	@Override
	public List<Room> importRoomsList() {

		return utility.importData();
	}

	@Override
	public void exportRoomsList(List<Room> roomsList) {
		utility.exportData(roomsList);
	}

}
