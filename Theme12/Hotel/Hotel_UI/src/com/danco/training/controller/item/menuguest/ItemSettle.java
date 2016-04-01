package com.danco.training.controller.item.menuguest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemSettle extends ItemOperating {
	private static final String PROTOCOL = "4"+";"+"settleGuestInRoom"+";";
	private static final String SEPARATOR =";";
	private static final String MESSAGE_1 = "Id guest";
	private static final String MESSAGE_2 = "Id room";
	private static final String MESSAGE_3 = "Date in settle";
	private static final String MESSAGE_4 = "Date out settle";
	private static final String MESSAGE_5 = "Message";
	
	private static final Logger LOGGER = LogManager.getLogger(ItemSettle.class);

	public ItemSettle(String name, IProcessing processing) {
		super(name, processing);
	}

	@Override
	public Menu work() {
		BufferedReader readers = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String idGuest = readers.readLine();

			System.out.println(MESSAGE_2);
			String idRoom = readers.readLine();

			System.out.println(MESSAGE_3);
			String dateInSettle = readers.readLine();

			System.out.println(MESSAGE_4);
			String dateOutSettle = readers.readLine();
			
			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL).append(idGuest).append(SEPARATOR).append(idRoom).append(SEPARATOR).append(dateInSettle).append(SEPARATOR).append(dateOutSettle);
			
			//выводим результат		
			System.out.println(MESSAGE_5 + processing.dataProcessing(str));

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();

	}

}
