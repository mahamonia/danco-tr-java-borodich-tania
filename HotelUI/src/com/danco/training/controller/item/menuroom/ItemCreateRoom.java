package com.danco.training.controller.item.menuroom;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Room;
import com.danco.training.entity.Status;
import com.danco.training.services.ServiceAdmin;

public class ItemCreateRoom extends ItemOperating{
	private final String MESSAGE_1 = "Number ";
	private final String MESSAGE_2 = "Content...";
	private final String MESSAGE_3 = "Stars...";
	private final String MESSAGE_4 = "Price...";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemCreateRoom.class);

	public ItemCreateRoom(String name, ServiceAdmin admin) {
		super(name, admin);
	}
	
	public Menu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		try {
			int number = admin.getNumberForNewRoom();
			System.out.println(MESSAGE_1 + number );
			
			
			System.out.println(MESSAGE_2);
			int content = Integer.parseInt(reader.readLine());
			
			System.out.println(MESSAGE_3);
			int stars = Integer.parseInt(reader.readLine());
			
			System.out.println(MESSAGE_4);
			int price = Integer.parseInt(reader.readLine());

			
			Room room = new Room(number, content, Status.FREE, stars, price);

			admin.createRoom(room);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();

	}

}
