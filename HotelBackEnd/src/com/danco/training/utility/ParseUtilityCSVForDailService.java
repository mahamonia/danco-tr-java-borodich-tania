package com.danco.training.utility;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.entity.DailService;

public class ParseUtilityCSVForDailService {
	private static ParseUtilityCSVForDailService utility;
	private CSVFileWorker fileDailService = new CSVFileWorker("dailService.csv");
	
	private ParseUtilityCSVForDailService(){
		
	}
	
	public static ParseUtilityCSVForDailService getInstance() {
		if (utility == null) {
			utility = new ParseUtilityCSVForDailService();
		}
		return utility;
	}
	
	public List<DailService> importData() {// read from CSV
		String[][] tempServices = fileDailService.readFromFile();
		List<DailService> dailServicesList = new ArrayList<>();
		
		for (int i = 0; i < tempServices.length; i++) {
			int Id = Integer.valueOf(tempServices[i][0]);
			String name = tempServices[i][1];
			int price = Integer.valueOf(tempServices[i][2]);
			
			DailService newService = new DailService(Id, name, price);
			 
			 dailServicesList.add(newService);
		}
		return dailServicesList;
	}
	
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
