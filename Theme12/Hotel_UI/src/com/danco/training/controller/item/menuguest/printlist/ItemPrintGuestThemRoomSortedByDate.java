package com.danco.training.controller.item.menuguest.printlist;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.model.entity.Chek;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemPrintGuestThemRoomSortedByDate extends ItemOperating {
	private static final String PROTOCOL = "0" + ";"
			+ "getListChekSortedByDateOutSettle";
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
			List<Chek> list = (List<Chek>) processing.dataProcessing(str);
			System.out.println(list.size());
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getGuest().getId() + " - "
						+ list.get(i).getGuest().getName() +" live in "+ list.get(i).getRoom().getNumber() + MESSAGE_2
						+ list.get(i).getDateOutSettle());

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
