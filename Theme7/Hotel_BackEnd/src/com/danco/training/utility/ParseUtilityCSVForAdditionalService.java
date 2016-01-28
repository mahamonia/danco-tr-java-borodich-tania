package com.danco.training.utility;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.entity.AdditionalService;

public class ParseUtilityCSVForAdditionalService {
	private static ParseUtilityCSVForAdditionalService utility;
	private CSVFileWorker fileAdditionalService = new CSVFileWorker("additionalService.csv");
	
	private ParseUtilityCSVForAdditionalService(){
		
	}
	
	public static ParseUtilityCSVForAdditionalService getInstance() {
		if (utility == null) {
			utility = new ParseUtilityCSVForAdditionalService();
		}
		return utility;
	}
	
	public List<AdditionalService> importData() {// read from CSV
		String[][] tempServices = fileAdditionalService.readFromFile();
		List<AdditionalService> additionalServicesList = new ArrayList<>();
		
		for (int i = 0; i < tempServices.length; i++) {
			int Id = Integer.valueOf(tempServices[i][0]);
			String name = tempServices[i][1];
			int price = Integer.valueOf(tempServices[i][2]);
			String description = tempServices[i][3];
			int addPrice = Integer.valueOf(tempServices[i][4]);
			
			AdditionalService newService = new AdditionalService(Id, name, price, description, addPrice);
			 
			additionalServicesList.add(newService);
		}
		return additionalServicesList;
	}
	
	public void exportData(List<AdditionalService> servicesList) { // write in CSV
		StringBuilder strTemp = new StringBuilder();
		String[] strServices = new String[servicesList.size()];
		
		for (int i = 0; i < strServices.length; i++) {
			strTemp.append(servicesList.get(i).getId()+";");
			strTemp.append(servicesList.get(i).getName()+";");
			strTemp.append(servicesList.get(i).getPrice()+";");
			strTemp.append(servicesList.get(i).getDescription()+";");
			strTemp.append(servicesList.get(i).getAddPrice()+";");
			
			strServices[i] =  strTemp.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
		}
		fileAdditionalService.writeToFile(strServices);
	}
}