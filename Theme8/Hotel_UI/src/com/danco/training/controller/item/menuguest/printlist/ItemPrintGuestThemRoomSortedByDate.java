package com.danco.training.controller.item.menuguest.printlist;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Guest;

public class ItemPrintGuestThemRoomSortedByDate extends ItemOperating {
	private static final String PROTOCOL = "0" + ";"
			+ "printGuestsSortedByDateOutSettle";
	private static final String MESSAGE_1 = "List guest";
	private static final String MESSAGE_2 = " date out settle: ";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemPrintGuestThemRoomSortedByDate.class);

	public ItemPrintGuestThemRoomSortedByDate(String name, IProcessing processing) {
		super(name, processing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Menu work() {

		try {
			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL);

			// выводим результат
			System.out.println(MESSAGE_1);
			List<Guest> guestsList = (List<Guest>) processing.dataProcessing(str);
			for (int i = 0; i < guestsList.size(); i++) {
				System.out.println(guestsList.get(i).getId() + " - "
						+ guestsList.get(i).getName() + MESSAGE_2
						+ guestsList.get(i).getDateOutSettle());

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
