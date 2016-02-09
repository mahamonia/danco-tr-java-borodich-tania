package com.danco.training.controller.item.menuroom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemImportRoomList extends ItemOperating {
	private static final String PROTOCOL = "0"+";"+"importRoomsList";
	private static final String MESSAGE_1 ="Message";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemImportRoomList.class);

	public ItemImportRoomList(String name, IProcessing processing) {
		super(name, processing);
	}

	public Menu work() {

		try {
			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL);
			
			System.out.println(MESSAGE_1 + processing.dataProcessing(str));

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();

	}

}
