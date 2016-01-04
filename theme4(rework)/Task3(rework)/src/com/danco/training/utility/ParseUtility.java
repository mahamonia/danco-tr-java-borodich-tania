package com.danco.training.utility;

import com.danco.training.TextFileWorker;
import com.danco.training.entity.AdditionalService;
import com.danco.training.entity.DailService;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Order;
import com.danco.training.entity.Room;
import com.danco.training.entity.Service;
import com.danco.training.entity.Status;

public class ParseUtility {

	private TextFileWorker fileGuest;
	private TextFileWorker fileRoom;
	private TextFileWorker fileOrder;
	private TextFileWorker fileDailService;
	private TextFileWorker fileAdditionalService;

	public ParseUtility(String strGuest, String strRoom, String strOrder, String strDailService, String strAdditionalService) {
		fileGuest = new TextFileWorker(strGuest);
		fileRoom = new TextFileWorker(strRoom);
		fileOrder = new TextFileWorker(strOrder);
		fileDailService = new TextFileWorker(strDailService);
		fileAdditionalService = new TextFileWorker(strAdditionalService);
	}
	private String[][] getArrayEntity(TextFileWorker fileEntity) {

		String[] tempString = fileEntity.readFromFile(); // array string file
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

		String[][] tempGuest = getArrayEntity(fileGuest);
		Guest [] guestsList = new Guest[tempGuest.length];

		for (int i = 0; i < tempGuest.length; i++) {
			int Id = Integer.valueOf(tempGuest[i][0]);
			String name = tempGuest[i][1];
			String pasport = tempGuest[i][2];
			String dateInSettle = tempGuest[i][3];
			String dateOutSettle = tempGuest[i][4];
			int IdOrder = Integer.valueOf(tempGuest[i][5]);

			guestsList[i] = new Guest(Id, name, pasport, dateInSettle, dateOutSettle,IdOrder);
		}
		return guestsList;
	}

	public void setGuests(Guest[] guests) {

		StringBuilder strTemp = new StringBuilder();
		String[] strGuests = new String[guests.length];

		for (int i = 0; i < strGuests.length; i++) {
			strTemp.append(guests[i].getId() + ";");
			strTemp.append(guests[i].getName() + ";");
			strTemp.append(guests[i].getPasport() + ";");
			strTemp.append(guests[i].getDateInSettle() + ";");
			strTemp.append(guests[i].getDateOutSettle()+ ";");
			strTemp.append(guests[i].getIdOrder() );
			
			strGuests[i] = strTemp.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
		}
		fileGuest.writeToFile(strGuests);
	}

	public Room[] getRooms() {

		String[][] tempRoom = getArrayEntity(fileRoom);
		Room [] roomsList = new Room[tempRoom.length];
		
		
		for (int i = 0; i < tempRoom.length; i++) {
			int number = Integer.valueOf(tempRoom[i][0]);
			int content = Integer.valueOf(tempRoom[i][1]);
			Status status = Status.valueOf(tempRoom[i][2]);
			int stars = Integer.valueOf(tempRoom[i][3]);
			int price = Integer.valueOf(tempRoom[i][4]);		
			String tempIdGuest = tempRoom[i][5];		
			
			int [] IdGuest = new int [3];
			String [] str = tempIdGuest.split("_");
			
			for (int j = 0; j < IdGuest.length; j++) { 
				IdGuest[j]= Integer.parseInt(str[j]);
			}			
			roomsList[i] = new Room(number, content, status, stars, price, IdGuest);
		}
		return roomsList;
	}

	public void setRooms(Room[] rooms) {

		StringBuilder strTemp = new StringBuilder();
		String[] strRooms = new String[rooms.length];

		for (int i = 0; i < strRooms.length; i++) {
			strTemp.append(rooms[i].getNumber() + ";");
			strTemp.append(rooms[i].getContent() + ";");
			strTemp.append(rooms[i].getStatus() + ";");
			strTemp.append(rooms[i].getStars() + ";");
			strTemp.append(rooms[i].getPrice() + ";");
			
			StringBuilder str = new StringBuilder();
			for (int j = 0; j < rooms[i].getIdGuest().length; j++) {
				str.append(rooms[i].getIdGuest()[j]+"_");
			}
	
			strRooms[i] = strTemp.toString()+str.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
			str.delete(0, Integer.MAX_VALUE);
		}
		fileRoom.writeToFile(strRooms);
	}

	public Order[] getOrders() {

		String[][] tempOrders = getArrayEntity(fileOrder);
		Order[] ordersList = new Order[tempOrders.length ];

		for (int i = 0; i < tempOrders.length; i++) {
			int Id = Integer.valueOf(tempOrders[i][0]);
			int IdGuest = Integer.valueOf(tempOrders[i][1]);

			// get array IdServices
			String[] strIdServices = tempOrders[i][2].split("_");
			int[] IdServices = new int[strIdServices.length];
			for (int j = 0; j < strIdServices.length; j++) {
				IdServices[j] = Integer.parseInt(strIdServices[j]);
			}
			int price = Integer.valueOf(tempOrders[i][3]);

			ordersList[i] = new Order(Id, IdGuest, IdServices, price);
		}
		return ordersList;
	}

	public void setOrders(Order[] orders) {

		StringBuilder strTemp = new StringBuilder();
		String[] ordersList = new String[orders.length];

		for (int i = 0; i < ordersList.length; i++) {
			strTemp.append(orders[i].getId() + ";");
			strTemp.append(orders[i].getIdGuest() + ";");
			
			// array IdServices translate in string
			StringBuilder str = new StringBuilder();
			for (int j = 0; j < orders[i].getIdServices().length; j++) {
				str.append(orders[i].getIdServices()[j]+"_");
			}			
			// add string IdServices to [i] string order 
			strTemp.append(str+";");
			str.delete(0, Integer.MAX_VALUE);
			
			strTemp.append(orders[i].getSumPrice());
			
			ordersList[i] = strTemp.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
		}
		fileOrder.writeToFile(ordersList);
		
	}

	public Service[] getDailServices() {
		
		String[][] tempServices = getArrayEntity(fileDailService);
		Service [] dailServicesList = new DailService[tempServices.length];
		
		for (int i = 0; i < tempServices.length; i++) {
			int Id = Integer.valueOf(tempServices[i][0]);
			String name = tempServices[i][1];
			int price = Integer.valueOf(tempServices[i][2]);
			
			dailServicesList[i] = new DailService(Id, name, price);
		}
		return dailServicesList;
	}

	public void setDailServices(Service[] dailServicesList) {
		// 
	}
	
	public Service[] getAdditionalServices() {
		
		String[][] tempServices = getArrayEntity(fileAdditionalService);
		Service [] additionalServicesList = new AdditionalService[tempServices.length];
		
		for (int i = 0; i < tempServices.length; i++) {
			int Id = Integer.valueOf(tempServices[i][0]);
			String name = tempServices[i][1];
			int price = Integer.valueOf(tempServices[i][2]);
			String description = tempServices[i][3];
			int addPrice = Integer.valueOf(tempServices[i][4]);		
			
			additionalServicesList[i] = new AdditionalService(Id, name, price, description, addPrice);
		}
		return additionalServicesList;
	}

	public void setAdditionalServices(Service[] additionalServicesList) {
		
	}

}
