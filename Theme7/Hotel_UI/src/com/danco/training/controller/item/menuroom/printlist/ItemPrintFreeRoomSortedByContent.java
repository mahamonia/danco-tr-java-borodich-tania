package com.danco.training.controller.item.menuroom.printlist;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Room;
import com.danco.api.IServiceAdmin;

public class ItemPrintFreeRoomSortedByContent extends ItemOperating {

	public final String MESSAGE_1 = "List room";
	public final String MESSAGE_2 = " contains ";
	public final String MESSAGE_3 = " places";
	private static final Logger LOGGER = LogManager.getLogger(ItemPrintFreeRoomSortedByContent.class);

	public ItemPrintFreeRoomSortedByContent(String name, IServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public Menu work() {

		try {
			List<Room> roomList = admin.printRoomFreeSortetdByContent();
			System.out.println(MESSAGE_1);
			for (int i = 0; i < roomList.size(); i++) {
				System.out.println(roomList.get(i).getNumber() + MESSAGE_2
						+ roomList.get(i).getContent() + MESSAGE_3);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
