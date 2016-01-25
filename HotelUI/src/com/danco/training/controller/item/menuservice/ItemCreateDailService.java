package com.danco.training.controller.item.menuservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.DailService;
import com.danco.training.entity.Service;
import com.danco.training.services.ServiceAdmin;

public class ItemCreateDailService extends ItemOperating{
	private final String MESSAGE_1 = "Name...";
	private final String MESSAGE_2 = "Price...";
	private static final Logger LOGGER = LogManager.getLogger(ItemCreateDailService.class);

	public ItemCreateDailService(String name, ServiceAdmin admin) {
		super(name, admin);
	}
	public Menu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		try {
			System.out.println(MESSAGE_1);
			String name = reader.readLine();
			
			System.out.println(MESSAGE_2);
			int price = Integer.parseInt(reader.readLine());
			
			Service service =  new DailService(0, name, price);

			admin.createService(service);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();

	}
	
	

}
