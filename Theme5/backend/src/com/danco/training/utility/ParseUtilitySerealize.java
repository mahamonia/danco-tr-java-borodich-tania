package com.danco.training.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.danco.config.PropertyProgramm;
import com.danco.training.entity.AdditionalService;
import com.danco.training.entity.DailService;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Order;
import com.danco.training.entity.Room;
import com.danco.training.entity.Service;
import com.danco.training.entity.Status;
import com.danco.training.services.ServiceAdmin;

public class ParseUtilitySerealize {
	
	private static ParseUtilitySerealize utility;

	private final String SERIALIZED_DATA = "SerializedData.txt";
	
	private PropertyProgramm config = PropertyProgramm.getInstance();
	
	private ParseUtilitySerealize() {

	}

	public static ParseUtilitySerealize getInstance() {

		if (utility == null) {
			utility = new ParseUtilitySerealize();
		}
		return utility;
	}

	private List<List> getListEntity(List<Guest> guestList,
			List<Room> roomList, List<Order> orderList,
			List<Service> dailList, List<AdditionalService> addServiceList) {

		List<List> entitiesList = new ArrayList<>();
		entitiesList.add(guestList);
		entitiesList.add(roomList);
		entitiesList.add(orderList);
		entitiesList.add(dailList);
		entitiesList.add(addServiceList);
		return entitiesList;
	}

	public void setSerializeData(List<Guest> guestList,
			List<Room> roomList, List<Order> orderList,
			List<Service> dailList, List<AdditionalService> addServiceList) {

		List<List> entitiesList = getListEntity(guestList, roomList, orderList,
				dailList, addServiceList);
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(SERIALIZED_DATA);
			out = new ObjectOutputStream(fos);
			out.writeObject(entitiesList);
			out.close();
			fos.close();
		} catch (Exception e) {

		}

	}
	private List<List> getSerializeData() {

		List<List> entitiesList = new ArrayList<>();;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(SERIALIZED_DATA);
			in = new ObjectInputStream(fis);
			entitiesList = (List<List>)in.readObject();
			
			in.close();
			fis.close();
		} catch (Exception e) {
		}
		return entitiesList;
	}
	
	
	public List<Guest> getGuestsList() {
		List<Guest> guestsList;
		if (getSerializeData().size()!=0){
			guestsList = getSerializeData().get(0);
		} else guestsList = new ArrayList<Guest>();
		
		return guestsList;
	}
	
	public List<Room> getRoomsList() {
		
		List<Room> roomsList;
		if ((getSerializeData().size()!=0)&&(getSerializeData().get(1).size()!=0)){
			roomsList = getSerializeData().get(1);

		}else {roomsList = new ArrayList<Room>();
		
		int amt = config.getConfigHistoryRoom("amount");	
		int [] idGuest = new int [amt];
		
		Room room1 = new Room(1, 2, Status.FREE, 3, 10, idGuest);
		Room room2 = new Room(2, 2, Status.FREE, 2, 10, idGuest);
		Room room3 = new Room(3, 3, Status.FREE, 3, 12, idGuest);
		Room room4 = new Room(4, 3, Status.FREE, 2, 5, idGuest);
		Room room5 = new Room(5, 2, Status.FREE, 3, 12, idGuest);
		roomsList.add(room1);
		roomsList.add(room2);
		roomsList.add(room3);
		roomsList.add(room4);
		roomsList.add(room5);
		}		
		return roomsList;
	}
	
	public List<Order> getOrdersList() {
		
		List<Order> ordersList;
		if (getSerializeData().size()!= 0){
			ordersList = getSerializeData().get(2);
		} else ordersList = new ArrayList<Order>();
		return ordersList;	
	}
	
	public List<Service> getDailServiceList() {
		
		List<Service> dailServiceList;
		if ((getSerializeData().size() !=0)&&(getSerializeData().get(3).size()!=0)){
			dailServiceList= getSerializeData().get(3);
		} else {dailServiceList = new ArrayList<Service>();
		DailService service1 = new DailService(1, "Room", 2);
		DailService service2 = new DailService(2, "Breakfast", 3);
		DailService service3 = new DailService(3, "Dinner", 4);
		DailService service4 = new DailService(4, "Suppe", 6);
		dailServiceList.add(service1);
		dailServiceList.add(service2);
		dailServiceList.add(service3);
		dailServiceList.add(service4);
		}
		return dailServiceList;	
	}
	
	public List<AdditionalService> getAdditionalServiceList() {
		
		List<AdditionalService> additionalServicesList;
		if ((getSerializeData().size()!=0 )&&(getSerializeData().get(4).size()!=0)){
			additionalServicesList= getSerializeData().get(4);
		}else {additionalServicesList = new ArrayList<AdditionalService>();
		AdditionalService service1 = new AdditionalService(5, "animals", 3, "walk the dog", 2);
		AdditionalService service2 = new AdditionalService(6, "animals", 3, "feed the dog", 5);
		additionalServicesList.add(service1);
		additionalServicesList.add(service2);
		
		}
		return additionalServicesList;
	}


}
