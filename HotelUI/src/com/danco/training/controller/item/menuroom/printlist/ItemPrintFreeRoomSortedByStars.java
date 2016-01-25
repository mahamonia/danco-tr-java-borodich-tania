package com.danco.training.controller.item.menuroom.printlist;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Room;
import com.danco.training.services.ServiceAdmin;

public class ItemPrintFreeRoomSortedByStars extends ItemOperating {

	public final String MESSAGE_1 = "List room";
	public final String MESSAGE_2 = " have stars ";
	private static final Logger LOGGER = LogManager.getLogger(ItemPrintFreeRoomSortedByStars.class);

	public ItemPrintFreeRoomSortedByStars(String name, ServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public Menu work() {
		try {
			List<Room> roomList = admin.printRoomFreeSortetdByStars();
			System.out.println(MESSAGE_1);
			for (int i = 0; i < roomList.size(); i++) {
				System.out.println(roomList.get(i).getNumber() + MESSAGE_2
						+ roomList.get(i).getStars());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return this.getMenu();
	}

}
