package com.danco.training.controller.item.menuservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.api.IServiceAdmin;

public class ItemExportAdditionalService extends ItemOperating{
	private static final Logger LOGGER = LogManager.getLogger(ItemExportAdditionalService.class);

	public ItemExportAdditionalService(String name, IServiceAdmin admin) {
		super(name, admin);
	}
	
	public Menu work() {

		try {
			admin.exportAdditionalServicesList();
			System.out.println("Export ok!");

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
		
	}

}
