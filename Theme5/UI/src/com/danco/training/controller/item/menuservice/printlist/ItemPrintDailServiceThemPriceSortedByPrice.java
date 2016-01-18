package com.danco.training.controller.item.menuservice.printlist;

import java.io.IOException;
import java.util.List;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Service;
import com.danco.training.services.ServiceAdmin;

public class ItemPrintDailServiceThemPriceSortedByPrice extends ItemOperating {
	public final String MESSAGE_1 = "List dail service";
	public final String MESSAGE_2 = " cost ";

	public ItemPrintDailServiceThemPriceSortedByPrice(String name,
			ServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public Menu work() {
		try {
			List<Service> serviceList = admin.printDailServicesSortedByPrice();
			System.out.println(MESSAGE_1);
			for (int i = 0; i < serviceList.size(); i++) {
				System.out.println(serviceList.get(i).getName() + MESSAGE_2
						+ serviceList.get(i).getPrice());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.getMenu();
	}

}
