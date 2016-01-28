package com.danco.training.controller.item.menuguest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.IServiceAdmin;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Guest;

public class ItemOutSettle extends ItemOperating {

	public final String MESSAGE = "Id guest";
	private static final Logger LOGGER = LogManager.getLogger(ItemOutSettle.class);

	public ItemOutSettle(String name, IServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public Menu work() {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE);
			String line = reader.readLine();
			int idGuest = Integer.parseInt(line);

			Guest guest = admin.getGuestById(idGuest);

			admin.settleGuestOutRoom(guest);
			admin.updateGuest(guest);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} 
		return this.getMenu();
	}

}
