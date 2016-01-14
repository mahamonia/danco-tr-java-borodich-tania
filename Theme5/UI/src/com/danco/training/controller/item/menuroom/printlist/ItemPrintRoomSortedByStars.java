package com.danco.training.controller.item.menuroom.printlist;

import java.io.IOException;
import java.util.List;

import com.danco.training.controller.item.itemmenu.AbstractItemEmpty;
import com.danco.training.controller.menu.AbstractMenu;
import com.danco.training.entity.Room;
import com.danco.training.services.ServiceAdmin;

public class ItemPrintRoomSortedByStars extends AbstractItemEmpty{
	
	public final String MESSAGE_1 = "List room";
	public final String MESSAGE_2 = " have stars ";

	public ItemPrintRoomSortedByStars(String name, ServiceAdmin admin) {
		super(name, admin);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractMenu work() {
		try {
			List<Room> roomList = admin.printSortedRoomByStars();
			System.out.println(MESSAGE_1);
			for (int i = 0; i < roomList.size(); i++) {
				System.out.println(roomList.get(i).getNumber() +MESSAGE_2 +roomList.get(i).getStars());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.getMenu();
	}

}
