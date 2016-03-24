package com.danco.utility;

import java.util.ArrayList;
import java.util.List;

import com.danco.annotation.Injection;
import com.danco.api.backend.IParseUtilityCSVForGuest;
import com.danco.api.backend.IServiceAdmin;
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
//		List<Chek> listChekForGuest= new ArrayList<Chek>();

		for (int i = 0; i < tempGuest.length; i++) {
			int id = Integer.valueOf(tempGuest[i][0]);
			String name = tempGuest[i][1];
			String pasport = tempGuest[i][2];
		//	String tempIdChek = tempGuest[i][3];
			
//			String [] str = tempIdChek.split("_");
//			
//			for (int j = 0; j < str.length; j++) { 
//				Chek chek = admin.getChekById(Integer.parseInt(str[j]));
//				listChekForGuest.add(chek);			
//			}

			Guest newGuest = new Guest(name, pasport);
			newGuest.setId(id);		
		//	newGuest.setChekList(listChekForGuest);

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
			
//			StringBuilder str = new StringBuilder();
//			for (int j = 0; j < guestsList.get(i).getChekList().size(); j++) {
//				str.append(guestsList.get(i).getChekList().get(j).getId()+"_");
//			}
			strGuests[i] = strTemp.toString();//+str.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
//			str.delete(0, Integer.MAX_VALUE);
		}		
		fileGuest.writeToFile(strGuests);	
	}

}
