package com.danco.training.controller.item.menuguest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.IServiceAdmin;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Guest;


public class ItemRegistry extends ItemOperating {
	private final String MESSAGE_1 = "Name...";
	private final String MESSAGE_2 = "Pasport...";
	private final String MESSAGE_3 = "Date in settle...";
	private final String OPEN_DATE = "";
	private static final Logger LOGGER = LogManager.getLogger(ItemRegistry.class);

	public ItemRegistry(String name, IServiceAdmin admin) {
		super(name, admin);
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

			String dateOutSettle = OPEN_DATE;
			Guest guest = new Guest(0, name, pasport, dateInSettle,
					dateOutSettle);

			admin.createGuest(guest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();

	}

}
