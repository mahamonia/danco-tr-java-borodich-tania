package com.danco.training.controller.item.menuservice.printlist;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.DailService;

public class ItemPrintDailServiceThemPriceSortedByName extends ItemOperating {
	private static final String PROTOCOL = "0" + ";" + "printDailServicesSortedByName";
	private static final String MESSAGE_1 = "List dail service";
	private static final String MESSAGE_2 = " cost ";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemPrintDailServiceThemPriceSortedByName.class);

	public ItemPrintDailServiceThemPriceSortedByName(String name,
			IProcessing processing) {
		super(name, processing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Menu work() {
		try {
			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL);

			List<DailService> serviceList = (List<DailService>) processing
					.dataProcessing(str);
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
