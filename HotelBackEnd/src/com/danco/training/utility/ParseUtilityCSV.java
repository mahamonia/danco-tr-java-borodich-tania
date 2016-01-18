package com.danco.training.utility;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.danco.training.entity.Guest;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class ParseUtilityCSV {
	
	private static ParseUtilityCSV utility;
	private final String FILE = "guest.csv";
	
	private ParseUtilityCSV(){
		
	}
	
	public static ParseUtilityCSV getInstance() {
		if (utility == null) {
			utility = new ParseUtilityCSV();
		}
		return utility;
	}
	
	public void importDate(List<Guest> guestsList) { // write in CSV
		
		FileWriter file;
		CSVWriter writer;
		try {
			file = new FileWriter(FILE);			
			writer = new CSVWriter(file);
			
			StringBuilder strTemp = new StringBuilder();
			String [] strGuests = new String[guestsList.size()];
			
			for (int i = 0; i < strGuests.length; i++) {
				strTemp.append(guestsList.get(i).getId() + ";");
				strTemp.append(guestsList.get(i).getName() + ";");
				strTemp.append(guestsList.get(i).getPasport() + ";");
				strTemp.append(guestsList.get(i).getDateInSettle() + ";");
				strTemp.append(guestsList.get(i).getDateOutSettle()+ ";");
				strTemp.append(guestsList.get(i).getIdOrder());
				
				strGuests[i] = strTemp.toString();
				strTemp.delete(0, Integer.MAX_VALUE);
			}
			
			writer.writeNext(strGuests);
			writer.close();
			file.close();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	
	public List<Guest> exportDate() { // read from CSV
		
		FileReader file;
		CSVReader reader;
		List<Guest> guestsList;
		try {
			file = new FileReader(FILE);			
			reader = new CSVReader(file);
			
			String[][] tempGuest = getArrayEntity();
			guestsList = new ArrayList<Guest>();

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
		} catch (Exception e) {

		}
		
		
		return guestsList;
		
	}
	
	private String[][] getArrayEntity( ) {

		
		return null;
	}

}
