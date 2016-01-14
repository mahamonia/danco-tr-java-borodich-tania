package com.danco.training.controller.item.menuservice.printlist;

import com.danco.training.controller.item.itemmenu.ItemInsert;
import com.danco.training.controller.menu.AbstractMenu;

public class ItemBackMenuService extends ItemInsert{

	public ItemBackMenuService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractMenu work() {
		// TODO Auto-generated method stub
		return this.getMenu();
	}

}
