package com.danco.training.controller.item.menuroom;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Room;
import com.danco.training.services.IServiceAdmin;

public class ItemChangePriceRoom extends ItemOperating {
	public final String MESSAGE_1 ="Id room";
	public final String MESSAGE_2 ="price..";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemChangePriceRoom.class);

	public ItemChangePriceRoom(String name, IServiceAdmin admin) {
		super(name, admin);

	}

	@Override
	public Menu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String line = reader.readLine();
			int idRoom = Integer.parseInt(line);

			System.out.println(MESSAGE_2);
			line = reader.readLine();
			int price = Integer.parseInt(line);

			Room room = admin.getRoomByNumber(idRoom);
			admin.changeRoomPrice(room, price);
			admin.updateRoom(room);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
