package com.danco.training.controller.item.menuroom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemExportRoomList extends ItemOperating{
	private static final String PROTOCOL = "0"+";"+"exportRoomsList";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemExportRoomList.class);
	private static final String MESSAGE = "Message ";

	public ItemExportRoomList(String name, IProcessing processing) {
		super(name, processing);
	}
	
	public Menu work() {

		try {
			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL);
			
			System.out.println(MESSAGE + processing.dataProcessing(str));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();		
	}

}
