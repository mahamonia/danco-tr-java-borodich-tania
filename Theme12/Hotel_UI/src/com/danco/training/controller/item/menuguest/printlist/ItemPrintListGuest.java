package com.danco.training.controller.item.menuguest.printlist;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.ui.IProcessing;
import com.danco.model.entity.Guest;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemPrintListGuest extends ItemOperating {
	
	private static final String PROTOCOL = "0" + ";"
			+ "getListGuest";
	private static final String MESSAGE_1 = "List guest";
	private static final String MESSAGE_2 = ", pasport ";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemPrintGuestThemRoomSortedByName.class);

	public ItemPrintListGuest(String name, IProcessing processing) {
		super(name, processing);
	}
	
	@Override
	public Menu work(){
		try {
			StringBuilder str = new StringBuilder();
			str.append(PROTOCOL);
			
			// ������� ���������
			System.out.println(MESSAGE_1);
			@SuppressWarnings("unchecked")
			List<Guest> guestsList = (List<Guest>) processing.dataProcessing(str);
			for (int i = 0; i < guestsList.size(); i++) {
				System.out.println("idGuest - "+guestsList.get(i).getId() + ", name: "
						+ guestsList.get(i).getName() + MESSAGE_2
						+ guestsList.get(i).getPasport());

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
