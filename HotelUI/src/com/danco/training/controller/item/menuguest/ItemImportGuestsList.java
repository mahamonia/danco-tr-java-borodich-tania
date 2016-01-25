package com.danco.training.controller.item.menuguest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.services.ServiceAdmin;

public class ItemImportGuestsList extends ItemOperating{
	
	private static final Logger LOGGER = LogManager.getLogger(ItemImportGuestsList.class);

	public ItemImportGuestsList(String name, ServiceAdmin admin) {
		super(name, admin);
	}
	
	public Menu work() {

		try {
			admin.importGuestsList();
			System.out.println("Import ok!");

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
		
	}

}
