package com.danco.training.controller.item.menuroom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemAmountFreeRoom extends ItemOperating{
	private static final String PROTOCOL = "0"+";"+"getAmountFreeRoom"+";";
	private static final String MESSAGE = "Amount free room = ";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemAmountFreeRoom.class);

	public ItemAmountFreeRoom(String name, IProcessing processing) {
		super(name, processing);

	}

	@Override
	public Menu work()  {
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
