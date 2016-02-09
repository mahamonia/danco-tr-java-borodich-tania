package com.danco.training.controller.item.menuguest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemAddService extends ItemOperating {

	private static final String PROTOCOL = "2"+";"+"addService"+";";
	private static final String SEPARATOR =";";
	private static final String MESSAGE_1 = "Id guest";
	private static final String MESSAGE_2 = "Id service";
	private static final String MESSAGE_3 = "Message ";
	
	private static final Logger LOGGER = LogManager.getLogger(ItemAddService.class);
	
	public ItemAddService(String name, IProcessing processing) {
		super(name, processing);
	}

	@Override
	public Menu work() {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String idGuest = reader.readLine();

			System.out.println(MESSAGE_2);
			String idService = reader.readLine();
			
			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL).append(idGuest).append(SEPARATOR).append(idService);
			
			System.out.println(MESSAGE_3 + processing.dataProcessing(str));

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
