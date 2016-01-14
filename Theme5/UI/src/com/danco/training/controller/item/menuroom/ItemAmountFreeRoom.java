package com.danco.training.controller.item.menuroom;

import java.io.IOException;

import com.danco.training.controller.item.itemmenu.AbstractItemEmpty;
import com.danco.training.controller.menu.AbstractMenu;
import com.danco.training.services.ServiceAdmin;

public class ItemAmountFreeRoom extends AbstractItemEmpty{
	public final String MESSAGE = "Amount free room = ";

	public ItemAmountFreeRoom(String name, ServiceAdmin admin) {
		super(name, admin);

	}

	@Override
	public AbstractMenu work()  {
		try {
			int amount = admin.getAmountFreeRoom();
			System.out.println(MESSAGE+ amount);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return this.getMenu();
	}

}
