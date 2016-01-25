package com.danco.training.controller.item.menuguest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Guest;
import com.danco.training.services.ServiceAdmin;

public class ItemGetSumOrderGuest extends ItemOperating{
	
	public final String MESSAGE_1 = "Id guest..";
	public final String MESSAGE_2 = "Sum order guest = ";
	private static final Logger LOGGER = LogManager.getLogger(ItemGetSumOrderGuest.class);

	public ItemGetSumOrderGuest(String name, ServiceAdmin admin) {
		super(name, admin);

	}

	@Override
	public Menu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String line = reader.readLine();
			int idGuest = Integer.parseInt(line);
			
			Guest guest = admin.getGuestById(idGuest);
			int sum = admin.getSumOrderGuest(guest);
			
			System.out.println(MESSAGE_2+ sum);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}	

		return this.getMenu();
	}

}
