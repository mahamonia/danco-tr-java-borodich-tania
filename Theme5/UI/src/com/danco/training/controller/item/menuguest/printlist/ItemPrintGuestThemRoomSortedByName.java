package com.danco.training.controller.item.menuguest.printlist;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.services.ServiceAdmin;

public class ItemPrintGuestThemRoomSortedByName extends ItemOperating{

	public ItemPrintGuestThemRoomSortedByName(String name, ServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public Menu work() {

		return this.getMenu();
	}

}
