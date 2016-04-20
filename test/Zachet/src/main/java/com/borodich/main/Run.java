package com.borodich.main;


public class Run {

	public static void main(String[] args) {
		
		
		Controller contr = new Controller("fileForCSV.csv");
		//contr.exportData();
		contr.importAndUpdateData();
		

	}

}
