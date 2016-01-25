package com.danco.training.controller.item.menuguest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.services.ServiceAdmin;

public class ItemExportGuestsList extends ItemOperating{
	
	private static final Logger LOGGER = LogManager.getLogger(ItemExportGuestsList.class);

	public ItemExportGuestsList(String name, ServiceAdmin admin) {
		super(name, admin);
	}
	public Menu work() {

		try {
			admin.exportGuestsList();
			System.out.println("Export ok!");

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
		
	}

}
