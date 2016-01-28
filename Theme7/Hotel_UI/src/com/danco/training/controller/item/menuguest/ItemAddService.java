package com.danco.training.controller.item.menuguest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.IServiceAdmin;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Service;

public class ItemAddService extends ItemOperating {

	public final String MESSAGE_1 = "Id guest";
	public final String MESSAGE_2 = "Id service";
	private static final Logger LOGGER = LogManager.getLogger(ItemAddService.class);

	public ItemAddService(String name, IServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public Menu work() {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String line = reader.readLine();
			int idGuest = Integer.parseInt(line);

			System.out.println(MESSAGE_2);
			line = reader.readLine();
			int idService = Integer.parseInt(line);
			Guest guest = admin.getGuestById(idGuest);
			Service service = admin.getServiceById(idService);

			admin.addServiceForGuest(guest, service);
			admin.updateGuest(guest);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
