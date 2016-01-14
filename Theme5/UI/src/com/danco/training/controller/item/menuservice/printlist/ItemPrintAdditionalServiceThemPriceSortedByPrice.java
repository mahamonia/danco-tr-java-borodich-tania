package com.danco.training.controller.item.menuservice.printlist;

import java.io.IOException;
import java.util.List;

import com.danco.training.controller.item.itemmenu.AbstractItemEmpty;
import com.danco.training.controller.menu.AbstractMenu;
import com.danco.training.entity.AdditionalService;
import com.danco.training.services.ServiceAdmin;

public class ItemPrintAdditionalServiceThemPriceSortedByPrice extends
		AbstractItemEmpty {
	public final String MESSAGE_1 = "List additional service";
	public final String MESSAGE_2 = " cost ";
	public final String MESSAGE_3 = " + ";

	public ItemPrintAdditionalServiceThemPriceSortedByPrice(String name,
			ServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public AbstractMenu work() {
		List<AdditionalService> serviceList = admin.getListAdditionalService();
		try {
			serviceList = admin.printAdditionalServicesSortedByPrice();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(MESSAGE_1);
		for (int i = 0; i < serviceList.size(); i++) {
			System.out.println(serviceList.get(i).getName() + MESSAGE_2
					+ serviceList.get(i).getPrice() + MESSAGE_3
					+ serviceList.get(i).getAddPrice());
		}
		return null;
	}

}
