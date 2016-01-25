package com.danco.training.controller.item.menuroom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.services.ServiceAdmin;

public class ItemAmountFreeRoom extends ItemOperating{
	public final String MESSAGE = "Amount free room = ";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemAmountFreeRoom.class);

	public ItemAmountFreeRoom(String name, ServiceAdmin admin) {
		super(name, admin);

	}

	@Override
	public Menu work()  {
		try {
			int amount = admin.getAmountFreeRoom();
			System.out.println(MESSAGE+ amount);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return this.getMenu();
	}

}
