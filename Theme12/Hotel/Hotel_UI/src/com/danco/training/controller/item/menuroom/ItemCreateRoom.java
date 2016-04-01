package com.danco.training.controller.item.menuroom;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemCreateRoom extends ItemOperating {
	private static final String PROTOCOL = "room" + ";" + "createRoom" + ";";
	private static final String SEPARATOR = ";";
	private static final String MESSAGE_1 = "Number ";
	private static final String MESSAGE_2 = "Content...";
	private static final String MESSAGE_3 = "Stars...";
	private static final String MESSAGE_4 = "Price...";
	private static final String MESSAGE_5 = "Message";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemCreateRoom.class);

	public ItemCreateRoom(String name, IProcessing processing) {
		super(name, processing);
	}

	public Menu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		try {

			System.out.println(MESSAGE_1);
			String number = reader.readLine();

			System.out.println(MESSAGE_2);
			String content = reader.readLine();

			System.out.println(MESSAGE_3);
			String stars = reader.readLine();

			System.out.println(MESSAGE_4);
			String price = reader.readLine();

			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL).append(number).append(SEPARATOR)
					.append(content).append(SEPARATOR).append(stars)
					.append(SEPARATOR).append(price);
			
			System.out.println(MESSAGE_5 + processing.dataProcessing(str));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();

	}

}
