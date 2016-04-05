package com.danco.training.controller.item.menuroom;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemChangeStatusRoom extends ItemOperating {
	private static final String PROTOCOL = "2Dif"+";"+"changeRoomStatus"+";";
	private static final String SEPARATOR =";";
	private static final String MESSAGE_1 = "Id room";
	private static final String MESSAGE_2 = "status..";
	
	private static final Logger LOGGER = LogManager
			.getLogger(ItemChangeStatusRoom.class);

	public ItemChangeStatusRoom(String name, IProcessing processing) {
		super(name, processing);
	}

	@Override
	public Menu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String idRoom = reader.readLine();

			System.out.println(MESSAGE_2);
			String status = reader.readLine();

			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL).append(idRoom).append(SEPARATOR).append(status);
			
			System.out.println(processing.dataProcessing(str));
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
