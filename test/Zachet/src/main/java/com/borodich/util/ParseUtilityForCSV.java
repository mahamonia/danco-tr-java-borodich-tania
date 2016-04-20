package com.borodich.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.borodich.entity.Employee;
import com.borodich.entity.Gender;

public class ParseUtilityForCSV {

	private CSVFileWorker file; 

	public ParseUtilityForCSV(String fileName) {
		
		this.file = new CSVFileWorker(fileName);
	}

	public List<Employee> importData() throws ParseException { // read from CSV

		String[][] temp = file.readFromFile();
		List<Employee> employeeList = new ArrayList<Employee>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		for (int i = 0; i < temp.length; i++) {
			int id = Integer.valueOf(temp[i][0]);
			String fName = temp[i][1];
			String lName = temp[i][2];
//			Date bDate = format.parse(temp[i][3]);
			Gender gender = Gender.valueOf(temp[i][4]);
			String title = temp[i][5];

			Employee newEmployee = new Employee(fName, lName, null, gender,
					title);

			newEmployee.setId(id);
			employeeList.add(newEmployee);
		}
		return employeeList;
	}

	public void exportData(List<Employee> list) { // write in CSV

		StringBuilder strTemp = new StringBuilder();
		String[] str = new String[list.size()];
		str[0] = strTemp.toString();
		strTemp.delete(0, Integer.MAX_VALUE);

		for (int i = 0; i < str.length; i++) {
			strTemp.append(list.get(i).getId() + ";");
			strTemp.append(list.get(i).getfName() + ";");
			strTemp.append(list.get(i).getlName() + ";");
			strTemp.append(list.get(i).getbDate() + ";");
			strTemp.append(list.get(i).getGender() + ";");
			strTemp.append(list.get(i).getTitle() + ";");

			str[i] = strTemp.toString();
			strTemp.delete(0, Integer.MAX_VALUE);
		}
		file.writeToFile(str);
	}

}
