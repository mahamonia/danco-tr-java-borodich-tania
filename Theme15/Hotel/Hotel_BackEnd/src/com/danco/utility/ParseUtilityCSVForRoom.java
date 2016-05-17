package com.danco.utility;

import java.util.ArrayList;
import java.util.List;

import com.danco.api.backend.IParseUtilityCSVForRoom;
import com.danco.model.entity.Room;
import com.danco.model.entity.Status;

public class ParseUtilityCSVForRoom implements IParseUtilityCSVForRoom {

	private CSVFileWorker fileRoom = new CSVFileWorker();

	public ParseUtilityCSVForRoom() {
	}

	@Override
	public List<Room> importData(String nameFile) { // read from CSV
		String[][] tempRoom = fileRoom.readFromFile(nameFile);
		List<Room> roomsList = new ArrayList<Room>();

		for (int i = 1; i < tempRoom.length; i++) {
			int id = Integer.valueOf(tempRoom[i][0]);
			int number = Integer.valueOf(tempRoom[i][1]);
			int content = Integer.valueOf(tempRoom[i][2]);
			Status status = Status.valueOf(tempRoom[i][3]);
			int stars = Integer.valueOf(tempRoom[i][4]);
			int price = Integer.valueOf(tempRoom[i][5]);

			Room newRoom = new Room(number, content, status, stars, price);

			newRoom.setId(id);
			;
			roomsList.add(newRoom);
		}
		return roomsList;

	}

	@Override
	public void exportData(List<Room> roomsList, String nameFile) { // write in CSV
		StringBuilder strTemp = new StringBuilder();
		String[] strRooms = new String[roomsList.size()];
		strTemp.append("Id;").append("Number;").append("Content;")
				.append("Status;").append("Stars;").append("Price;");
		strRooms[0] = strTemp.toString();
		strTemp.delete(0, Integer.MAX_VALUE);
		for (int i = 1; i < strRooms.length; i++) {
			strTemp.append(roomsList.get(i).getId() + ";");
			strTemp.append(roomsList.get(i).getNumber() + ";");
			strTemp.append(roomsList.get(i).getContent() + ";");
			strTemp.append(roomsList.get(i).getStatus() + ";");
			strTemp.append(roomsList.get(i).getStars() + ";");
			strTemp.append(roomsList.get(i).getPrice() + ";");

			strRooms[i] = strTemp.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
		}
		fileRoom.writeToFile(strRooms, nameFile);
	}

}
