package com.danco.training.controller.item.menuguest.printlist;

import java.util.List;

import com.danco.api.IServiceAdmin;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Guest;

public class ItemPrintGuestThemRoomSortedByName extends ItemOperating{

	public ItemPrintGuestThemRoomSortedByName(String name, IServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public Menu work() {
		List<Guest>  guestsList =	admin.printGuestsSortedByName();
		for (int i = 0; i < guestsList.size(); i++) {
			System.out.println(guestsList.get(i).getName());
		}
		return this.getMenu();
	}

}
