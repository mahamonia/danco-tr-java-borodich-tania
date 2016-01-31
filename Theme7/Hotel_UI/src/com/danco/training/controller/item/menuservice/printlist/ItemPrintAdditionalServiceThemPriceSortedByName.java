package com.danco.training.controller.item.menuservice.printlist;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.AdditionalService;
import com.danco.api.IServiceAdmin;

public class ItemPrintAdditionalServiceThemPriceSortedByName extends
		ItemOperating {

	public final String MESSAGE_1 = "List additional service";
	public final String MESSAGE_2 = " cost ";
	public final String MESSAGE_3 = " + ";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemPrintAdditionalServiceThemPriceSortedByName.class);

	public ItemPrintAdditionalServiceThemPriceSortedByName(String name,
			IServiceAdmin admin) {
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
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}
}
