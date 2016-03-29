package com.danco.training.controller.item.menuroom;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemForCloningRoom extends ItemOperating {
	private static final String PROTOCOL = "1" + ";" + "cloneRoom" + ";";
	private static final String MESSAGE_1 = "Id room..";
	private static final String MESSAGE_2 = "Message";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemForCloningRoom.class);

	public ItemForCloningRoom(String name, IProcessing processing) {
		super(name, processing);
	}

	public Menu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {

			System.out.println(MESSAGE_1);
			String idRoom = reader.readLine();

			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL).append(idRoom);
		
			System.out.println(MESSAGE_2 + processing.dataProcessing(str));

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
