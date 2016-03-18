package com.danco.utility;

import java.util.ArrayList;
import java.util.List;

import com.danco.api.backend.IParseUtilityCSVForDailService;
import com.danco.training.entity.DailService;

public class ParseUtilityCSVForDailService implements IParseUtilityCSVForDailService{

	private CSVFileWorker fileDailService = new CSVFileWorker("dailService.csv");
	
	public ParseUtilityCSVForDailService(){
		
	}

	@Override
	public List<DailService> importData() {// read from CSV
		String[][] tempServices = fileDailService.readFromFile();
		List<DailService> dailServicesList = new ArrayList<>();
		
		for (int i = 0; i < tempServices.length; i++) {
			int id = Integer.valueOf(tempServices[i][0]);
			String name = tempServices[i][1];
			int price = Integer.valueOf(tempServices[i][2]);
			
			DailService newService = new DailService(name, price);
			newService.setId(id);
			 
			 dailServicesList.add(newService);
		}
		return dailServicesList;
	}
	@Override
	public void exportData(List<DailService> servicesList) { // write in CSV
		StringBuilder strTemp = new StringBuilder();
		String[] strServices = new String[servicesList.size()];
		
		for (int i = 0; i < strServices.length; i++) {
			strTemp.append(servicesList.get(i).getId()+";");
			strTemp.append(servicesList.get(i).getName()+";");
			strTemp.append(servicesList.get(i).getPrice()+";");
			
			strServices[i] =  strTemp.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
		}
		fileDailService.writeToFile(strServices);
	}
	
}
