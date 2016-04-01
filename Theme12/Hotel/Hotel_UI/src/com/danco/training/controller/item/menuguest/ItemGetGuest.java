package com.danco.training.controller.item.menuguest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.model.entity.Guest;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemGetGuest extends ItemOperating {

	private static final String PROTOCOL = "1;"+"getGuestById"+";";
	private static final String SEPARATOR =";";
	private static final String MESSAGE_1 = "Id guest";
	
	private static final Logger LOGGER = LogManager.getLogger(ItemAddService.class);
	
	public ItemGetGuest(String name, IProcessing processing) {
		super(name, processing);
	}

	@Override
	public Menu work() {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String idGuest = reader.readLine();
			
			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL).append(idGuest).append(SEPARATOR);
			
			
			Guest guest = (Guest)processing.dataProcessing(str);
			
			System.out.println(guest.getName());

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
