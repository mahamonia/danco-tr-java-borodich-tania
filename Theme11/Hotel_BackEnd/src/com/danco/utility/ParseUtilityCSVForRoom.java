package com.danco.utility;

import java.util.ArrayList;
import java.util.List;

import com.danco.annotation.Injection;
import com.danco.api.backend.IParseUtilityCSVForRoom;
import com.danco.api.backend.IServiceAdmin;
import com.danco.model.entity.Room;
import com.danco.model.entity.Status;

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
		
		for (int i = 0; i < tempRoom.length; i++) {
			int id = Integer.valueOf(tempRoom[i][0]);
			int number = Integer.valueOf(tempRoom[i][1]);
			int content = Integer.valueOf(tempRoom[i][2]);
			Status status = Status.valueOf(tempRoom[i][3]);
			int stars = Integer.valueOf(tempRoom[i][4]);
			int price = Integer.valueOf(tempRoom[i][5]);
	
			Room newRoom = new Room(number, content, status, stars, price);
						
			newRoom.setId(id);;
			roomsList.add(newRoom);
		}	
		return roomsList;
		
	}
	@Override
	public void exportData(List<Room> roomsList) { // write in CSV
		StringBuilder strTemp = new StringBuilder();
		String[] strRooms = new String[roomsList.size()];

		for (int i = 0; i < strRooms.length; i++) {
			strTemp.append(roomsList.get(i).getId() + ";");
			strTemp.append(roomsList.get(i).getNumber() + ";");
			strTemp.append(roomsList.get(i).getContent() + ";");
			strTemp.append(roomsList.get(i).getStatus() + ";");
			strTemp.append(roomsList.get(i).getStars() + ";");
			strTemp.append(roomsList.get(i).getPrice() + ";");
		
			strRooms[i] = strTemp.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
		}
		fileRoom.writeToFile(strRooms);
	}

}
