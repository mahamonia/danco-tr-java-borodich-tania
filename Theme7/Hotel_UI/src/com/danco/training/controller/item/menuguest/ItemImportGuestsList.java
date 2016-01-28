package com.danco.training.controller.item.menuguest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.IServiceAdmin;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemImportGuestsList extends ItemOperating{
	
	private static final Logger LOGGER = LogManager.getLogger(ItemImportGuestsList.class);

	public ItemImportGuestsList(String name, IServiceAdmin admin) {
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
