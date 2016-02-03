package com.danco.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.IParseUtilitySerealize;
import com.danco.training.entity.AdditionalService;
import com.danco.training.entity.DailService;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Order;
import com.danco.training.entity.Room;

public class ParseUtilitySerealize implements IParseUtilitySerealize{
	
	private static final String NAME_FILE = "SerializedData.txt";
	private static final Logger LOGGER = LogManager.getLogger(ParseUtilitySerealize.class);
	
	public ParseUtilitySerealize() {
	}

	@SuppressWarnings("rawtypes")
	private List<List> getListEntity(List<Guest> guestList,
			List<Room> roomList, List<Order> orderList,
			List<DailService> dailList, List<AdditionalService> addServiceList) {

		List<List> entitiesList = new ArrayList<>();
		entitiesList.add(guestList);
		entitiesList.add(roomList);
		entitiesList.add(orderList);
		entitiesList.add(dailList);
		entitiesList.add(addServiceList);
		return entitiesList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setSerializeData(List<Guest> guestList,
			List<Room> roomList, List<Order> orderList,
			List<DailService> dailList, List<AdditionalService> addServiceList) {

		List<List> entitiesList = getListEntity(guestList, roomList, orderList,
				dailList, addServiceList);
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(NAME_FILE);
			out = new ObjectOutputStream(fos);
			out.writeObject(entitiesList);
			out.close();
			fos.close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<List> getSerializeData() {

		List<List> entitiesList = new ArrayList<>();;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(NAME_FILE);
			in = new ObjectInputStream(fis);
			entitiesList = (List<List>)in.readObject();
			
			in.close();
			fis.close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return entitiesList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Guest> getGuestsList() {
		List<Guest> guestsList;
		if (getSerializeData().size()!=0){
			guestsList = getSerializeData().get(0);
		} else guestsList = new ArrayList<Guest>();
		
		return guestsList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getRoomsList() {
		
		List<Room> roomsList;
		if(getSerializeData().size()!=0){
			roomsList = getSerializeData().get(1);

		}else roomsList = new ArrayList<Room>();
		
		return roomsList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrdersList() {
		
		List<Order> ordersList;
		if (getSerializeData().size()!= 0){
			ordersList = getSerializeData().get(2);
		} else ordersList = new ArrayList<Order>();
		return ordersList;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DailService> getDailServiceList() {
		
		List<DailService> dailServiceList;
		if (getSerializeData().size() !=0){
			dailServiceList= getSerializeData().get(3);
		} else dailServiceList = new ArrayList<DailService>();
		
		return dailServiceList;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AdditionalService> getAdditionalServiceList() {
		
		List<AdditionalService> additionalServicesList;
		if (getSerializeData().size()!=0 ){
			additionalServicesList= getSerializeData().get(4);
		}else additionalServicesList = new ArrayList<AdditionalService>();
		return additionalServicesList;
	}


}
