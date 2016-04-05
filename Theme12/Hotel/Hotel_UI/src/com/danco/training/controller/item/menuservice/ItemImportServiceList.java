package com.danco.training.controller.item.menuservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemImportServiceList extends ItemOperating {
	private static final String PROTOCOL = "0" + ";" + "importServicesList";

	private static final Logger LOGGER = LogManager
			.getLogger(ItemImportServiceList.class);

	public ItemImportServiceList(String name, IProcessing processing) {
		super(name, processing);
	}

	public Menu work() {

		try {
			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL);
			System.out.println(processing.dataProcessing(str));

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
