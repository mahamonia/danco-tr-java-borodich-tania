package com.danco.training.controller.item.menuguest.printlist;

import com.danco.training.controller.item.itemmenu.AbstractItemEmpty;
import com.danco.training.controller.menu.AbstractMenu;
import com.danco.training.services.ServiceAdmin;

public class ItemPrintGuestThemRoomSortedByName extends AbstractItemEmpty{

	public ItemPrintGuestThemRoomSortedByName(String name, ServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public AbstractMenu work() {
		// TODO Auto-generated method stub
		return this.getMenu();
	}

}
