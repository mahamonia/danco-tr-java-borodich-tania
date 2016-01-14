package com.danco.training.controller.item.menuguest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.danco.training.controller.item.itemmenu.AbstractItemEmpty;
import com.danco.training.controller.menu.AbstractMenu;
import com.danco.training.entity.Guest;
import com.danco.training.services.ServiceAdmin;

public class ItemRegistry extends AbstractItemEmpty{
	public final String MESSAGE_1 = "Name...";
	public final String MESSAGE_2= "Pasport...";
	public final String MESSAGE_3 = "Date in settle...";
	public final String OPEN_DATE = "";

	public ItemRegistry(String name, ServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public AbstractMenu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));		
		
		try {
			System.out.println(MESSAGE_1);
			String name = reader.readLine();
			System.out.println(MESSAGE_2);
			String pasport = reader.readLine();
			System.out.println(MESSAGE_3);
			String dateInSettle = reader.readLine();
			
			int id = admin.getIdForNewGuest();
			String dateOutSettle = OPEN_DATE;
			int idOrder = admin.getIdForNewOrder();
			Guest guest = new Guest(id, name, pasport, dateInSettle, dateOutSettle, idOrder);
			
			admin.createGuest(guest);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.getMenu();
			
	}

}
