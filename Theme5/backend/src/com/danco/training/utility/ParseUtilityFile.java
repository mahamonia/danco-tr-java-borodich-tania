package com.danco.training.utility;

import java.util.ArrayList;
import java.util.List;

import com.danco.config.PropertyProgramm;
import com.danco.training.TextFileWorker;
import com.danco.training.entity.AdditionalService;
import com.danco.training.entity.DailService;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Order;
import com.danco.training.entity.Room;
import com.danco.training.entity.Service;
import com.danco.training.entity.Status;

public class ParseUtilityFile {

	private TextFileWorker fileGuest = new TextFileWorker("Guest.txt");
	private TextFileWorker fileRoom = new TextFileWorker("Room.txt");
	private TextFileWorker fileOrder = new TextFileWorker("Order.txt");
	private TextFileWorker fileDailService = new TextFileWorker("DailService.txt");
	private TextFileWorker fileAdditionalService = new TextFileWorker("AdditionalService.txt");
	
	private PropertyProgramm config = PropertyProgramm.getInstance();

	public ParseUtilityFile(){
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

	public List<Guest> getGuests() {

		String[][] tempGuest = getArrayEntity(fileGuest);
		List<Guest> guestsList = new ArrayList<Guest>();

		for (int i = 0; i < tempGuest.length; i++) {
			int Id = Integer.valueOf(tempGuest[i][0]);
			String name = tempGuest[i][1];
			String pasport = tempGuest[i][2];
			String dateInSettle = tempGuest[i][3];
			String dateOutSettle = tempGuest[i][4];
			int IdOrder = Integer.valueOf(tempGuest[i][5]);
			
			Guest newGuest = new Guest(Id, name, pasport, dateInSettle, dateOutSettle, IdOrder);

			guestsList.add(newGuest);
		}
		return guestsList;
	}

	public void setGuests(List<Guest> guestsList) {

		StringBuilder strTemp = new StringBuilder();
		String[] strGuests = new String[guestsList.size()];

		for (int i = 0; i < strGuests.length; i++) {
			strTemp.append(guestsList.get(i).getId() + ";");
			strTemp.append(guestsList.get(i).getName() + ";");
			strTemp.append(guestsList.get(i).getPasport() + ";");
			strTemp.append(guestsList.get(i).getDateInSettle() + ";");
			strTemp.append(guestsList.get(i).getDateOutSettle()+ ";");
			strTemp.append(guestsList.get(i).getIdOrder() );
			
			strGuests[i] = strTemp.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
		}
		fileGuest.writeToFile(strGuests);
	}

	public List<Room> getRooms() {

		String[][] tempRoom = getArrayEntity(fileRoom);
		List<Room> roomsList = new ArrayList<>();
		
		
		for (int i = 0; i < tempRoom.length; i++) {
			int number = Integer.valueOf(tempRoom[i][0]);
			int content = Integer.valueOf(tempRoom[i][1]);
			Status status = Status.valueOf(tempRoom[i][2]);
			int stars = Integer.valueOf(tempRoom[i][3]);
			int price = Integer.valueOf(tempRoom[i][4]);		
			String tempIdGuest = tempRoom[i][5];	
			
			int amt = config.getConfigHistoryRoom("amount");
			
			int [] IdGuest = new int [amt];
			String [] str = tempIdGuest.split("_");
			
			for (int j = 0; j < IdGuest.length; j++) { 
				IdGuest[j]= Integer.parseInt(str[j]);
				
			}	
			Room newRoom = new Room(number, content, status, stars, price, IdGuest);
			roomsList.add(newRoom);
		}
		return roomsList;
	}

	public void setRooms(List<Room> roomsList) {

		StringBuilder strTemp = new StringBuilder();
		String[] strRooms = new String[roomsList.size()];

		for (int i = 0; i < strRooms.length; i++) {
			strTemp.append(roomsList.get(i).getNumber() + ";");
			strTemp.append(roomsList.get(i).getContent() + ";");
			strTemp.append(roomsList.get(i).getStatus() + ";");
			strTemp.append(roomsList.get(i).getStars() + ";");
			strTemp.append(roomsList.get(i).getPrice() + ";");
			
			StringBuilder str = new StringBuilder();
			for (int j = 0; j < roomsList.get(i).getIdGuest().length; j++) {
				str.append(roomsList.get(i).getIdGuest()[j]+"_");
			}
	
			strRooms[i] = strTemp.toString()+str.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
			str.delete(0, Integer.MAX_VALUE);
		}
		fileRoom.writeToFile(strRooms);
	}

	public List<Order> getOrders() {

		String[][] tempOrders = getArrayEntity(fileOrder);
		List<Order> ordersList = new ArrayList<>();

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

			 Order newOrder= new Order(Id, IdGuest, IdServices, price);
			 
			 ordersList.add(newOrder);
		}
		return ordersList;
	}

	public void setOrders(List<Order> ordersList) {

		StringBuilder strTemp = new StringBuilder();
		String[] strOrders = new String[ordersList.size()];

		for (int i = 0; i < strOrders.length; i++) {
			strTemp.append(ordersList.get(i).getId() + ";");
			strTemp.append(ordersList.get(i).getIdGuest() + ";");
			
			// array IdServices translate in string
			StringBuilder str = new StringBuilder();
			for (int j = 0; j < ordersList.get(i).getIdServices().length; j++) {
				str.append(ordersList.get(i).getIdServices()[j]+"_");
			}			
			// add string IdServices to [i] string order 
			strTemp.append(str+";");
			str.delete(0, Integer.MAX_VALUE);
			
			strTemp.append(ordersList.get(i).getSumPrice());
			
			strOrders[i] = strTemp.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
		}
		fileOrder.writeToFile(strOrders);
		
	}

	public List<Service> getDailServices() {
		
		String[][] tempServices = getArrayEntity(fileDailService);
		List<Service> dailServicesList = new ArrayList<>();
		
		for (int i = 0; i < tempServices.length; i++) {
			int Id = Integer.valueOf(tempServices[i][0]);
			String name = tempServices[i][1];
			int price = Integer.valueOf(tempServices[i][2]);
			
			Service newService = new DailService(Id, name, price);
			 
			 dailServicesList.add(newService);
		}
		return dailServicesList;
	}

	public void setDailServices(Service[] dailServicesList) {
		// 
	}
	
	public List<AdditionalService> getAdditionalServices() {
		
		String[][] tempServices = getArrayEntity(fileAdditionalService);
		List<AdditionalService> additionalServicesList = new ArrayList<>();
		
		for (int i = 0; i < tempServices.length; i++) {
			int Id = Integer.valueOf(tempServices[i][0]);
			String name = tempServices[i][1];
			int price = Integer.valueOf(tempServices[i][2]);
			String description = tempServices[i][3];
			int addPrice = Integer.valueOf(tempServices[i][4]);		
			
			AdditionalService newService= new AdditionalService(Id, name, price, description, addPrice);
			
			additionalServicesList.add(newService); 
		}
		return additionalServicesList;
	}

	public void setAdditionalServices(Service[] additionalServicesList) {
		
	}

}
