package com.danco.training.controller.item.menuguest.printlist;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.services.ServiceAdmin;

public class ItemPrintGuestThemRoomSortedByDate extends ItemOperating{

	public ItemPrintGuestThemRoomSortedByDate(String name, ServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public Menu work() {
		

		return this.getMenu();
	}

}
