package com.danco.training.controller.item.menuservice.printlist;

import java.io.IOException;
import java.util.List;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.AdditionalService;
import com.danco.training.services.ServiceAdmin;

public class ItemPrintAdditionalServiceThemPriceSortedByName extends
		ItemOperating {

	public final String MESSAGE_1 = "List additional service";
	public final String MESSAGE_2 = " cost ";
	public final String MESSAGE_3 = " + ";

	public ItemPrintAdditionalServiceThemPriceSortedByName(String name,
			ServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public Menu work() {
		List<AdditionalService> serviceList;
		try {
			serviceList = admin.getListAdditionalService();
			serviceList = admin.printAdditionalServicesSortedByName();
			System.out.println(MESSAGE_1);
			for (int i = 0; i < serviceList.size(); i++) {
				System.out.println(serviceList.get(i).getName() + MESSAGE_2
						+ serviceList.get(i).getPrice() + MESSAGE_3
						+ serviceList.get(i).getAddPrice());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.getMenu();
	}
}
