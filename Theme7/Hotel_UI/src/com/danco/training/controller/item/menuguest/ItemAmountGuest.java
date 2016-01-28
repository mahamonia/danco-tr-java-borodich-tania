package com.danco.training.controller.item.menuguest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.IServiceAdmin;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemAmountGuest extends ItemOperating {
	public final String MESSAGE = "Amount guest = ";
	private static final Logger LOGGER = LogManager.getLogger(ItemAmountGuest.class);

	public ItemAmountGuest(String name, IServiceAdmin admin) {
		super(name, admin);

	}

	@Override
	public Menu work() {
		try {
			int amount = admin.getAmountGuest();
			System.out.println(MESSAGE + amount);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
