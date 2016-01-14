package com.danco.training.controller.item.menuguest.printlist;

import com.danco.training.controller.item.itemmenu.ItemInsert;
import com.danco.training.controller.menu.AbstractMenu;

public class ItemBackMenuGuest extends ItemInsert{

	public ItemBackMenuGuest(String name) {
		super(name);
	}

	@Override
	public AbstractMenu work() {
		return this.getMenu();
	}

}
