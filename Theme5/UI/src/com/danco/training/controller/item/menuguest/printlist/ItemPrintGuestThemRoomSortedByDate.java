package com.danco.training.controller.item.menuguest.printlist;


import com.danco.training.controller.item.itemmenu.AbstractItemEmpty;
import com.danco.training.controller.menu.AbstractMenu;
import com.danco.training.services.ServiceAdmin;

public class ItemPrintGuestThemRoomSortedByDate extends AbstractItemEmpty{

	public ItemPrintGuestThemRoomSortedByDate(String name, ServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public AbstractMenu work() {
		

		return this.getMenu();
	}

}
