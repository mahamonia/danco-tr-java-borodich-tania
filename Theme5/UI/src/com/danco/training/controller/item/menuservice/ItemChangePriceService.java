package com.danco.training.controller.item.menuservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Service;
import com.danco.training.services.ServiceAdmin;

public class ItemChangePriceService extends ItemOperating{
	
	public final String MESSAGE_1 ="Id service..";
	public final String MESSAGE_2="price..";

	public ItemChangePriceService(String name, ServiceAdmin admin) {
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

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
			}
			try {
				reader.close();
			} catch (IOException e) {
			}
		}
		return this.getMenu();
	}

}
