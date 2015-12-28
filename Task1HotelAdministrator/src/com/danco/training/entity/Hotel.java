package com.danco.training.entity;

import com.danco.training.TextFileWorker;

public class Hotel {

	private Guest[] Guests;
	private Room[] Rooms;
	private Service[] Services;
	private Order[] Orders;

	private TextFileWorker FileGuest = new TextFileWorker("Guest.txt");
	private TextFileWorker FileRoom = new TextFileWorker("Room.txt");
	private TextFileWorker FileOrders = new TextFileWorker("Order.txt");

	public Hotel() {
	}

	private String[][] getArrayEntity(TextFileWorker FileEntity) {

		String[] tempString = FileEntity.readFromFile(); // array string file
		String[] tempStringEntity;

		int amountString = tempString.length;
		int amountColumn = tempString[0].split(";").length;

		String[][] tempEntity = new String[amountString][amountColumn];

		// get array entity
		for (int i = 0; i < amountString; i++) { 
			tempStringEntity = tempString[i].split(";");

			for (int j = 0; j < amountColumn; j++) {
				tempEntity[i][j] = tempStringEntity[j]; 
			}
		}
		return tempEntity;
	}

	public Guest[] getGuests() {

		String[][] tempGuest = getArrayEntity(FileGuest);
		Guests = new Guest[tempGuest.length];

		for (int i = 0; i < tempGuest.length; i++) {
			int Id = Integer.valueOf(tempGuest[i][0]);
			String Name = tempGuest[i][1];
			String Pasport = tempGuest[i][2];
			int IdOrder = Integer.valueOf(tempGuest[i][3]);

			Guests[i] = new Guest(Id, Name, Pasport, IdOrder);
		}
		return Guests;
	}

	public void setGuests(Guest[] guests) {

		StringBuilder strTemp = new StringBuilder();
		String[] strGuests = new String[guests.length];

		for (int i = 0; i < strGuests.length; i++) {
			strTemp.append(guests[i].getId() + ";");
			strTemp.append(guests[i].getName() + ";");
			strTemp.append(guests[i].getPasport() + ";");
			strTemp.append(guests[i].getIdOrder());

			strGuests[i] = strTemp.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
		}
		FileGuest.writeToFile(strGuests);
	}

	public Room[] getRooms() {

		String[][] tempRoom = getArrayEntity(FileRoom);
		Rooms = new Room[tempRoom.length];
		
		for (int i = 0; i < tempRoom.length; i++) {
			int Number = Integer.valueOf(tempRoom[i][0]);
			int Level = Integer.valueOf(tempRoom[i][1]);
			// Status Status = Status.valueOf(tempRoom[i][2]);
			int Stars = Integer.valueOf(tempRoom[i][3]);
			int Price = Integer.valueOf(tempRoom[i][4]);
			String DateInSettle = tempRoom[i][5];
			String DateOutSettle = tempRoom[i][6];
			int IdGuest = Integer.valueOf(tempRoom[i][7]);

			Rooms[i] = new Room(Number, Level, Status.FREE, Stars, Price,
					DateInSettle, DateOutSettle, IdGuest);
		}
		return Rooms;
	}

	public void setRooms(Room[] rooms) {

		StringBuilder strTemp = new StringBuilder();
		String[] strRooms = new String[rooms.length];

		for (int i = 0; i < strRooms.length; i++) {
			strTemp.append(rooms[i].getNumber() + ";");
			strTemp.append(rooms[i].getLevel() + ";");
			strTemp.append(rooms[i].getStatus() + ";");
			strTemp.append(rooms[i].getStars() + ";");
			strTemp.append(rooms[i].getPrice() + ";");
			strTemp.append(rooms[i].getDateInSettle() + ";");
			strTemp.append(rooms[i].getDateOutSettle() + ";");
			strTemp.append(rooms[i].getIdGuest());

			strRooms[i] = strTemp.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
		}
		FileGuest.writeToFile(strRooms);

	}

	public Order[] getOrders() {

		String[][] tempOrders = getArrayEntity(FileOrders);
		Orders = new Order[tempOrders.length];
		
		for (int i = 0; i < tempOrders.length; i++) {
			int IdOrder = Integer.valueOf(tempOrders[i][0]);
			int IdGuest = Integer.valueOf(tempOrders[i][1]);
			Service[] service = null;
			int Price = Integer.valueOf(tempOrders[i][3]);

			Orders[i] = new Order(IdOrder, IdGuest, service, Price);
		}
		return Orders;
	}

	public void setOrders(Order[] orders) {

		StringBuilder strTemp = new StringBuilder();
		String[] strOrders = new String[orders.length];

		for (int i = 0; i < strOrders.length; i++) {
			strTemp.append(orders[i].getIdOrder() + ";");
			strTemp.append(orders[i].getIdGuest() + ";");
			strTemp.append("" + ";");
			strTemp.append(orders[i].getSumPrice());

			strOrders[i] = strTemp.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
		}
		FileGuest.writeToFile(strOrders);

		Orders = orders;
	}

	public Service[] getServices() {
		return Services;
	}

	public void setServices(Service[] services) {
		Services = services;
	}

}
