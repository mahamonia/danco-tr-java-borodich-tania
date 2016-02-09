package com.danco.training.controller.item.menuguest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemAmountGuest extends ItemOperating {
	private static final String PROTOCOL = "0"+";"+"getAmountGuest";
	private static final String MESSAGE_1 = "Amount guest = ";

	private static final Logger LOGGER = LogManager.getLogger(ItemAmountGuest.class);

	public ItemAmountGuest(String name, IProcessing processing) {
		super(name, processing);
	}

	@Override
	public Menu work() {
		try {
			
			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL);
			
			//выводим результат		
			System.out.println(MESSAGE_1 + processing.dataProcessing(str));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
