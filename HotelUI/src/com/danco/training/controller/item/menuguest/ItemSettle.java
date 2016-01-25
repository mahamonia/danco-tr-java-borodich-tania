package com.danco.training.controller.item.menuguest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Room;
import com.danco.training.services.ServiceAdmin;

public class ItemSettle extends ItemOperating {

	public final String MESSAGE_1 = "Id guest";
	public final String MESSAGE_2 = "Id room";
	public final String MESSAGE_3 = "Date in settle";
	public final String MESSAGE_4 = "Date out settle";
	private static final Logger LOGGER = LogManager.getLogger(ItemSettle.class);

	public ItemSettle(String name, ServiceAdmin admin) {
		super(name, admin);

	}

	@Override
	public Menu work() {
		BufferedReader readers = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String line = readers.readLine();
			int idGuest = Integer.parseInt(line);

			System.out.println(MESSAGE_2);
			line = readers.readLine();
			int idRoom = Integer.parseInt(line);

			System.out.println(MESSAGE_3);
			String dateInSettle = readers.readLine().toString();

			System.out.println(MESSAGE_4);
			String dateOutSettle = readers.readLine().toString();
			Guest guest = admin.getGuestById(idGuest);
			Room room = admin.getRoomByNumber(idRoom);

			admin.settleGuestInRoom(guest, room, dateInSettle, dateOutSettle);
			admin.updateGuest(guest);
			admin.updateRoom(room);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();

	}

}
