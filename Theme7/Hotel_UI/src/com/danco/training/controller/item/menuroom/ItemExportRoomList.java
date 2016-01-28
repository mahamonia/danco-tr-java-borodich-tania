package com.danco.training.controller.item.menuroom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.services.IServiceAdmin;

public class ItemExportRoomList extends ItemOperating{
	private static final Logger LOGGER = LogManager
			.getLogger(ItemExportRoomList.class);

	public ItemExportRoomList(String name, IServiceAdmin admin) {
		super(name, admin);
	}
	
	public Menu work() {

		try {
			admin.exportRoomsList();
			System.out.println("Import ok!");

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
		
	}

}
