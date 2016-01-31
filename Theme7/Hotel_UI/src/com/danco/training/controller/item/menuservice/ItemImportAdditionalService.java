package com.danco.training.controller.item.menuservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.api.IServiceAdmin;

public class ItemImportAdditionalService extends ItemOperating{	
	private static final Logger LOGGER = LogManager.getLogger(ItemImportAdditionalService.class);

	public ItemImportAdditionalService(String name, IServiceAdmin admin) {
		super(name, admin);
	}
	public Menu work() {

		try {
			admin.importAdditionalServicesList();
			System.out.println("Import ok!");

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
		
	}

}
