package com.danco.utility;

import java.util.ArrayList;
import java.util.List;

import com.danco.annotation.Injection;
import com.danco.api.backend.IParseUtilityCSVForGuest;
import com.danco.api.backend.IServiceAdmin;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Order;

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
			int Id = Integer.valueOf(tempGuest[i][0]);
			String name = tempGuest[i][1];
			String pasport = tempGuest[i][2];
			String dateInSettle = tempGuest[i][3];
			String dateOutSettle = tempGuest[i][4];
			int idOrder = Integer.valueOf(tempGuest[i][5]);
			
			Guest newGuest = new Guest(Id, name, pasport, dateInSettle, dateOutSettle);

			Order order = admin.getOrderById(idOrder);
			newGuest.setOrder(order);

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
			strTemp.append(guestsList.get(i).getDateInSettle() + ";");
			strTemp.append(guestsList.get(i).getDateOutSettle()+ ";");
			strTemp.append(guestsList.get(i).getOrder().getId()+";");
			
			strGuests[i] = strTemp.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
		}		
		fileGuest.writeToFile(strGuests);	
	}

}
