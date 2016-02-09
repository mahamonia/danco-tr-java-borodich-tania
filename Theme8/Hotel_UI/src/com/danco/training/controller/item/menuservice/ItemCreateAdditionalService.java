package com.danco.training.controller.item.menuservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemCreateAdditionalService extends ItemOperating {
	private static final String PROTOCOL = "additionalService" + ";"
			+ "createAdditionalService" + ";";
	private static final String SEPARATOR = ";";
	private static final String MESSAGE_1 = "Name...";
	private static final String MESSAGE_2 = "Price...";
	private static final String MESSAGE_3 = "Description...";
	private static final String MESSAGE_4 = "Add price...";
	private static final String MESSAGE_5 = "Message";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemCreateAdditionalService.class);

	public ItemCreateAdditionalService(String name, IProcessing processing) {
		super(name, processing);
	}

	public Menu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		try {
			System.out.println(MESSAGE_1);
			String name = reader.readLine();

			System.out.println(MESSAGE_2);
			String price = reader.readLine();

			System.out.println(MESSAGE_3);
			String description = reader.readLine();

			System.out.println(MESSAGE_4);
			String addPrice = reader.readLine();

			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL).append(name).append(SEPARATOR).append(price)
					.append(SEPARATOR).append(description).append(SEPARATOR)
					.append(addPrice);
			System.out.println(MESSAGE_5 + processing.dataProcessing(str));

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();

	}

}
