package com.danco.training.controller.item.menuroom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Guest;

public class ItemLastThreeGuestsRoom extends ItemOperating {
	private static final String PROTOCOL = "1"+";"+"getListThreeLastGuestsOfRoom"+";";
	private static final String MESSAGE_1 = "Number room";
	private static final String MESSAGE_2 = "Last Three Guests Room";
	private static final String MESSAGE_3 = " settel ";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemLastThreeGuestsRoom.class);

	public ItemLastThreeGuestsRoom(String name, IProcessing processing) {
		super(name, processing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Menu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String idRoom = reader.readLine();

			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL).append(idRoom);
			
			System.out.println(MESSAGE_2);
			List<Guest> guestList = (List<Guest>)processing.dataProcessing(str);

			int amount = 0;
			if (guestList.size() >= 3) {
				amount = 3;
			}
			
			for (int i = guestList.size(); i < guestList.size() - amount; i--) {
				System.out.println(guestList.get(i).getName() + MESSAGE_3
						+ guestList.get(i).getCheck().getDateInSettle());
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
