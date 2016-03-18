package com.danco.training.controller.item.menuservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemChangePriceService extends ItemOperating {
	private static final String PROTOCOL = "2" + ";" + "changeServicePrice"
			+ ";";
	private static final String SEPARATOR = ";";
	private static final String MESSAGE_1 = "Id service..";
	private static final String MESSAGE_2 = "price..";
	private static final String MESSAGE_3 = "Message";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemChangePriceService.class);

	public ItemChangePriceService(String name, IProcessing processing) {
		super(name, processing);
	}

	@Override
	public Menu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String idService = reader.readLine();

			System.out.println(MESSAGE_2);
			String price = reader.readLine();

			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL).append(idService).append(SEPARATOR)
					.append(price);
			System.out.println(MESSAGE_3 + processing.dataProcessing(str));

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
