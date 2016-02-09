package com.danco.training.controller.item.menuservice.printlist;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.AdditionalService;

public class ItemPrintAdditionalServiceThemPriceSortedByName extends
		ItemOperating {
	private static final String PROTOCOL = "0" + ";"
			+ "printAdditionalServicesSortedByName";
	private static final String MESSAGE_1 = "List additional service";
	private static final String MESSAGE_2 = " cost ";
	private static final String MESSAGE_3 = " + ";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemPrintAdditionalServiceThemPriceSortedByName.class);

	public ItemPrintAdditionalServiceThemPriceSortedByName(String name,
			IProcessing processing) {
		super(name, processing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Menu work() {

		try {
			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL);

			System.out.println(MESSAGE_1);

			List<AdditionalService> serviceList = (List<AdditionalService>) processing
					.dataProcessing(str);
			for (int i = 0; i < serviceList.size(); i++) {
				System.out.println(serviceList.get(i).getName() + MESSAGE_2
						+ serviceList.get(i).getPrice() + MESSAGE_3
						+ serviceList.get(i).getAddPrice());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}
}
