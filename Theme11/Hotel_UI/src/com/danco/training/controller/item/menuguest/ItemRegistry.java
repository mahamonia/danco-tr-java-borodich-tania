package com.danco.training.controller.item.menuguest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemRegistry extends ItemOperating {
	private static final String PROTOCOL = "guest"+";"+"createGuest"+";";
	private static final String SEPARATOR =";";
	private static final String MESSAGE_1 = "Name...";
	private static final String MESSAGE_2 = "Pasport...";
	private static final String MESSAGE_3 = "Date in settle...";
	private static final String MESSAGE_4 = "Message ";
	private static final Logger LOGGER = LogManager.getLogger(ItemRegistry.class);

	public ItemRegistry(String name, IProcessing processing) {
		super(name, processing);
	}

	public Menu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		try {
			System.out.println(MESSAGE_1);
			String name = reader.readLine();
			System.out.println(MESSAGE_2);
			String pasport = reader.readLine();
			System.out.println(MESSAGE_3);
			String dateInSettle = reader.readLine();
			
			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL).append(name).append(SEPARATOR).append(pasport).append(SEPARATOR).append(dateInSettle);
			
			//������� ���������		
			System.out.println(MESSAGE_4 + processing.dataProcessing(str));

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();

	}

}
