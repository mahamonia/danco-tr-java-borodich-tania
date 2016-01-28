package com.danco.training.controller.item.menuguest.printlist;

import java.util.List;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Guest;
import com.danco.training.services.IServiceAdmin;

public class ItemPrintGuestSortedById extends ItemOperating{

	public ItemPrintGuestSortedById(String name, IServiceAdmin admin) {
		super(name, admin);

	}

	public Menu work() {
		
		List<Guest>  guestsList = admin.getListGuest();
		for (int i = 0; i < guestsList.size(); i++) {
			System.out.println(guestsList.get(i).getId() +"-"+guestsList.get(i).getName());
		}
		return this.getMenu();

	}


}
