package com.danco.training.controller.item.menuservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.AdditionalService;
import com.danco.training.services.IServiceAdmin;

public class ItemCreateAdditionalService extends ItemOperating{
	private final String MESSAGE_1 = "Name...";
	private final String MESSAGE_2 = "Price...";
	private final String MESSAGE_3 = "Description...";
	private final String MESSAGE_4 = "Add price...";
	private static final Logger LOGGER = LogManager.getLogger(ItemCreateAdditionalService.class);

	public ItemCreateAdditionalService(String name, IServiceAdmin admin) {
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
			
			System.out.println(MESSAGE_3);
			String description = reader.readLine();
			
			System.out.println(MESSAGE_4);
			int addPrice = Integer.parseInt(reader.readLine());
			
			AdditionalService service =  new AdditionalService(0, name, price, description, addPrice);

			admin.createAdditionalService(service);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();

	}

}
