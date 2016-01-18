package com.danco.training.controller.item.menuguest;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.services.ServiceAdmin;

public class ItemAmountGuest extends ItemOperating {
	public final String MESSAGE = "Amount guest = ";

	public ItemAmountGuest(String name, ServiceAdmin admin) {
		super(name, admin);

	}

	@Override
	public Menu work() {
		try {
			int amount = admin.getAmountGuest();
			System.out.println(MESSAGE + amount);
		} catch (Exception e) {

		}
		return this.getMenu();
	}

}
