package com.danco.training.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.danco.training.entity.Guest;
import com.opencsv.CSVWriter;

public class ParseUtilityCSV {
	
	private static ParseUtilityCSV utility;
	
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
			file = new FileWriter("guest.csv");			
			writer = new CSVWriter(file, '\t');
			
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
			
			// String[] entries = "first#second#third".split("#");
		    // writer.writeNext(entries);
			writer.writeNext(strGuests);
			writer.close();
			file.close();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	
	public List<Guest> exportDate() { // read from CSV
		
		return null;
		
	}

}
