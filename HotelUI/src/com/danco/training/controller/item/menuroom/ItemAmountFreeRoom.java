package com.danco.training.controller.item.menuroom;

import java.io.IOException;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.services.ServiceAdmin;

public class ItemAmountFreeRoom extends ItemOperating{
	public final String MESSAGE = "Amount free room = ";

	public ItemAmountFreeRoom(String name, ServiceAdmin admin) {
		super(name, admin);

	}

	@Override
	public Menu work()  {
		try {
			int amount = admin.getAmountFreeRoom();
			System.out.println(MESSAGE+ amount);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.getMenu();
	}

}
