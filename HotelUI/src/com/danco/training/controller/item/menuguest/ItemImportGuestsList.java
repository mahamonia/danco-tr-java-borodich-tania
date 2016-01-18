package com.danco.training.controller.item.menuguest;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.services.ServiceAdmin;

public class ItemImportGuestsList extends ItemOperating{
	
	public final String MESSAGE_1 = "Id guest";

	public ItemImportGuestsList(String name, ServiceAdmin admin) {
		super(name, admin);
	}
	
	public Menu work() {

		try {
			admin.importGuestsList();
			System.out.println("Import ok!");

		} catch (Exception e) {

			e.printStackTrace();
		}
		return this.getMenu();
		
	}

}
