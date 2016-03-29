package com.danco.training.controller.item.menuservice.printlist;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.model.entity.Service;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemPrintServiceThemPriceSortedByName extends ItemOperating {
	private static final String PROTOCOL = "0" + ";" + "printServicesSortedByName";
	private static final String MESSAGE_1 = "List service";
	private static final String MESSAGE_2 = ", cost - ";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemPrintServiceThemPriceSortedByName.class);

	public ItemPrintServiceThemPriceSortedByName(String name,
			IProcessing processing) {
		super(name, processing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Menu work() {
		try {
			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL);

			List<Service> serviceList = (List<Service>) processing
					.dataProcessing(str);
			System.out.println(MESSAGE_1);
			for (int i = 0; i < serviceList.size(); i++) {
				System.out.println((i+1)+". "+serviceList.get(i).getName() + MESSAGE_2
						+ serviceList.get(i).getPrice());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
