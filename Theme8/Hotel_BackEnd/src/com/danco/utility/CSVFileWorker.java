package com.danco.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CSVFileWorker {

	private final File file;
	private String newLine = System.getProperty("line.separator");
	private static Logger logger = LogManager.getLogger(CSVFileWorker.class);

	public CSVFileWorker(String fileName) {

		file = new File(fileName);

	}

	public void writeToFile(String[] array) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < array.length; i++) {
				writer.write(array[i] + newLine);
			}
			writer.flush();
			writer.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public String[][] readFromFile() {

		BufferedReader reader;
		List<String> listString = new ArrayList<String>();
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((reader.readLine()) != null) {
				listString.add(reader.readLine());
			}
			reader.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return getArrayString(listString);

	}

	private String[][] getArrayString(List<String> listString) {

		String[] tempString = listString
				.toArray(new String[listString.size()]);
		String[] tempStringEntity;

		int amountString = tempString.length;
		int amountColumn = tempString[0].split(";").length;

		String[][] tempEntity = new String[amountString][amountColumn];

		// get array entity
		for (int i = 0; i < amountString; i++) {
			tempStringEntity = tempString[i].split(";");

			for (int j = 0; j < amountColumn; j++) {
				tempEntity[i][j] = tempStringEntity[j];
			}
		}
		return tempEntity;
	}

}
