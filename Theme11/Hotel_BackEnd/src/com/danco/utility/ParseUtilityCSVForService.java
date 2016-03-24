package com.danco.utility;

import java.util.ArrayList;
import java.util.List;

import com.danco.annotation.Injection;
import com.danco.api.backend.IParseUtilityCSVForService;
import com.danco.api.backend.IServiceAdmin;
import com.danco.model.entity.Chek;
import com.danco.model.entity.Service;

public class ParseUtilityCSVForService implements IParseUtilityCSVForService{

	private CSVFileWorker fileService = new CSVFileWorker("service.csv");
	
	@Injection
	private IServiceAdmin admin;
	
	public ParseUtilityCSVForService(){
		
	}

	@Override
	public List<Service> importData() {// read from CSV
		String[][] tempServices = fileService.readFromFile();
		List<Service> servicesList = new ArrayList<>();
		
		for (int i = 0; i < tempServices.length; i++) {
			int id = Integer.valueOf(tempServices[i][0]);
			String name = tempServices[i][1];
			int price = Integer.valueOf(tempServices[i][2]);
			
			int idChek = Integer.valueOf(tempServices[i][3]);

			Service newService = new Service(name, price);
			Chek chek = admin.getChekById(idChek);
			newService.setId(id);
			newService.setChek(chek);
			 
			 servicesList.add(newService);
		}
		return servicesList;
	}
	@Override
	public void exportData(List<Service> servicesList) { // write in CSV
		StringBuilder strTemp = new StringBuilder();
		String[] strServices = new String[servicesList.size()];
		
		for (int i = 0; i < strServices.length; i++) {
			strTemp.append(servicesList.get(i).getId()+";");
			strTemp.append(servicesList.get(i).getName()+";");
			strTemp.append(servicesList.get(i).getPrice()+";");
			strTemp.append(servicesList.get(i).getChek().getId()+";");
						
			strServices[i] =  strTemp.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
		}
		fileService.writeToFile(strServices);
	}
	
}
