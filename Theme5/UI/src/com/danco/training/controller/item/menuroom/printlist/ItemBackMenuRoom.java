package com.danco.training.controller.item.menuroom.printlist;


import com.danco.training.controller.item.itemmenu.ItemInsert;
import com.danco.training.controller.menu.AbstractMenu;

public class ItemBackMenuRoom extends ItemInsert{

	public ItemBackMenuRoom(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractMenu work() {
		// TODO Auto-generated method stub
		return this.getMenu();
	}

}
