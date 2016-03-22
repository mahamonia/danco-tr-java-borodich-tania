package com.danco.utility;

import java.util.ArrayList;
import java.util.List;

import com.danco.annotation.Injection;
import com.danco.api.backend.IParseUtilityCSVForGuest;
import com.danco.api.backend.IServiceAdmin;
import com.danco.model.entity.Check;
import com.danco.model.entity.Guest;

public class ParseUtilityCSVForGuest implements IParseUtilityCSVForGuest{
	
	private CSVFileWorker fileGuest = new CSVFileWorker("guest.csv");
	@Injection
	private IServiceAdmin admin;
	
	public ParseUtilityCSVForGuest(){
		
	}
	@Override
	public List<Guest> importData() { // read from CSV

		String[][] tempGuest = fileGuest.readFromFile();
		List<Guest> guestsList = new ArrayList<Guest>();

		for (int i = 0; i < tempGuest.length; i++) {
			int id = Integer.valueOf(tempGuest[i][0]);
			String name = tempGuest[i][1];
			String pasport = tempGuest[i][2];
			int idCheck = Integer.valueOf(tempGuest[i][3]);
			
			Guest newGuest = new Guest(name, pasport);
			newGuest.setId(id);

			Check check = admin.getCheckById(idCheck);
			newGuest.setCheck(check);

			guestsList.add(newGuest);
		}
		return guestsList;
			
		}
	@Override
	public void exportData(List<Guest> guestsList) { // write in CSV
		
		StringBuilder strTemp = new StringBuilder();
		String [] strGuests = new String[guestsList.size()];
		
		for (int i = 0; i < strGuests.length; i++) {
			strTemp.append(guestsList.get(i).getId() + ";");
			strTemp.append(guestsList.get(i).getName() + ";");
			strTemp.append(guestsList.get(i).getPasport() + ";");
			strTemp.append(guestsList.get(i).getCheck().getId()+";");
			
			strGuests[i] = strTemp.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
		}		
		fileGuest.writeToFile(strGuests);	
	}

}
