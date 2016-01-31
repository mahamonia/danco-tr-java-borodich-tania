package com.danco.training.controller.item.menuguest.printlist;

import java.util.List;

import com.danco.api.IServiceAdmin;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Guest;


public class ItemPrintGuestThemRoomSortedByDate extends ItemOperating{
	public final String MESSAGE = "date out settle";

	public ItemPrintGuestThemRoomSortedByDate(String name, IServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public Menu work() {
		List<Guest>  guestsList =	admin.printGuestsSortedByDateOutSettle();
		for (int i = 0; i < guestsList.size(); i++) {
			System.out.println(guestsList.get(i).getName() +MESSAGE+guestsList.get(i).getDateOutSettle());
		}

		return this.getMenu();
	}

}
