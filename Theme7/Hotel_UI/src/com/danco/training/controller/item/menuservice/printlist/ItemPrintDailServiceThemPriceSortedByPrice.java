package com.danco.training.controller.item.menuservice.printlist;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.DailService;
import com.danco.training.services.IServiceAdmin;

public class ItemPrintDailServiceThemPriceSortedByPrice extends ItemOperating {
	public final String MESSAGE_1 = "List dail service";
	public final String MESSAGE_2 = " cost ";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemPrintDailServiceThemPriceSortedByPrice.class);

	public ItemPrintDailServiceThemPriceSortedByPrice(String name,
			IServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public Menu work() {
		try {
			List<DailService> serviceList = admin
					.printDailServicesSortedByPrice();
			System.out.println(MESSAGE_1);
			for (int i = 0; i < serviceList.size(); i++) {
				System.out.println(serviceList.get(i).getName() + MESSAGE_2
						+ serviceList.get(i).getPrice());
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return this.getMenu();
	}

}
