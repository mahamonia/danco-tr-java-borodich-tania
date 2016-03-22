package com.danco.training.controller.item.menuroom.printlist;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.model.entity.Room;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemPrintRoomSortedByContent extends ItemOperating {
	private static final String PROTOCOL = "0" + ";"
			+ "getListRoomFreeSortedByContent";
	private static final String MESSAGE_1 = "List room";
	private static final String MESSAGE_2 = " contains ";
	private static final String MESSAGE_3 = " places";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemPrintRoomSortedByContent.class);

	public ItemPrintRoomSortedByContent(String name, IProcessing processing) {
		super(name, processing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Menu work() {
		try {
			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL);

			List<Room> roomList = (List<Room>) processing.dataProcessing(str);
			System.out.println(MESSAGE_1);
			System.out.println(roomList.size());
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
