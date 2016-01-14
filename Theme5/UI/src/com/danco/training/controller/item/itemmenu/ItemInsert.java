package com.danco.training.controller.item.itemmenu;

import com.danco.training.controller.menu.AbstractMenu;

public class ItemInsert extends AbstractItem {

	public ItemInsert(String name) {
		super(name);
	}

	@Override
	public AbstractMenu work() {
		return this.getMenu();

	}

}
