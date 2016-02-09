package com.danco.utility;

import java.util.ArrayList;
import java.util.List;

import com.danco.annotation.Injection;
import com.danco.api.backend.IParseUtilityCSVForRoom;
import com.danco.api.backend.IServiceAdmin;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Room;
import com.danco.training.entity.Status;

public class ParseUtilityCSVForRoom implements IParseUtilityCSVForRoom{
	
	private CSVFileWorker fileRoom = new CSVFileWorker("room.csv");
	@Injection
	private IServiceAdmin admin;
	
	public ParseUtilityCSVForRoom(){	
	}
	@Override
	public List<Room> importData() { // read from CSV
		String[][] tempRoom = fileRoom.readFromFile();
		List<Room> roomsList = new ArrayList<Room>();	
		List<Guest> listGuestForRoom = new ArrayList<Guest>();
		
		for (int i = 0; i < tempRoom.length; i++) {
			int number = Integer.valueOf(tempRoom[i][0]);
			int content = Integer.valueOf(tempRoom[i][1]);
			Status status = Status.valueOf(tempRoom[i][2]);
			int stars = Integer.valueOf(tempRoom[i][3]);
			int price = Integer.valueOf(tempRoom[i][4]);
			String tempIdGuest = tempRoom[i][5];
				
			String [] str = tempIdGuest.split("_");
			
			for (int j = 0; j < str.length; j++) { 
				Guest guest = admin.getGuestById(Integer.parseInt(str[j]));
				listGuestForRoom.add(guest);			
			}
				
			Room newRoom = new Room(number, content, status, stars, price);
						
			newRoom.setGuestList(listGuestForRoom);
			roomsList.add(newRoom);
		}	
		return roomsList;
		
	}
	@Override
	public void exportData(List<Room> roomsList) { // write in CSV
		StringBuilder strTemp = new StringBuilder();
		String[] strRooms = new String[roomsList.size()];

		for (int i = 0; i < strRooms.length; i++) {
			strTemp.append(roomsList.get(i).getNumber() + ";");
			strTemp.append(roomsList.get(i).getContent() + ";");
			strTemp.append(roomsList.get(i).getStatus() + ";");
			strTemp.append(roomsList.get(i).getStars() + ";");
			strTemp.append(roomsList.get(i).getPrice() + ";");
			
			StringBuilder str = new StringBuilder();
			for (int j = 0; j < roomsList.get(i).getGuestList().size(); j++) {
				str.append(roomsList.get(i).getGuestList().get(j).getId()+"_");
			}
			
			strRooms[i] = strTemp.toString()+str.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
			str.delete(0, Integer.MAX_VALUE);
		}
		fileRoom.writeToFile(strRooms);
	}

}
