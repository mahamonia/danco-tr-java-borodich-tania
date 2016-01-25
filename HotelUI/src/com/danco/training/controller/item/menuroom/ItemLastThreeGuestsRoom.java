package com.danco.training.controller.item.menuroom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.config.PropertyProgramm;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Room;
import com.danco.training.services.ServiceAdmin;

public class ItemLastThreeGuestsRoom extends ItemOperating {

	public final String MESSAGE_1 = "Id room";
	public final String MESSAGE_2 = " settel ";
	private PropertyProgramm config = PropertyProgramm.getInstance();
	private static final Logger LOGGER = LogManager
			.getLogger(ItemLastThreeGuestsRoom.class);

	public ItemLastThreeGuestsRoom(String name, ServiceAdmin admin) {
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

			Room room = admin.getRoomByNumber(idRoom);

			List<Guest> guestList = admin.getListGuestRoom(room);
			System.out.println("");
			int amount = 0;
			if (config.getConfigHistoryRoom("amount")<3){
				amount = 1;
			}else amount = 3;
			for (int i = guestList.size(); i < guestList.size() - amount; i--) {
				System.out.println(guestList.get(i).getName() + MESSAGE_2
						+ guestList.get(i).getDateInSettle());
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} 
		return this.getMenu();
	}

}
