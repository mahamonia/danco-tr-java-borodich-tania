package com.danco.training.controller.item.menuservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Service;
import com.danco.api.IServiceAdmin;

public class ItemChangePriceService extends ItemOperating{
	
	public final String MESSAGE_1 ="Id service..";
	public final String MESSAGE_2="price..";
	private static final Logger LOGGER = LogManager.getLogger(ItemChangePriceService.class);

	public ItemChangePriceService(String name, IServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public Menu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String line = reader.readLine();
			int idService = Integer.parseInt(line);

			System.out.println(MESSAGE_2);
			line = reader.readLine();
			int price = Integer.parseInt(line);

			Service service = admin.getServiceById(idService);
			admin.changeServicePrice(service, price);;
			admin.updateService(service);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} 
		return this.getMenu();
	}

}
