package com.danco.training.controller.item.menuroom.printlist;

import java.io.IOException;
import java.util.List;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Room;
import com.danco.training.services.ServiceAdmin;

public class ItemPrintRoomSortedByContent extends ItemOperating {

	public final String MESSAGE_1 = "List room";
	public final String MESSAGE_2 = " contains ";
	public final String MESSAGE_3 = " places";

	public ItemPrintRoomSortedByContent(String name, ServiceAdmin admin) {
		super(name, admin);

	}

	@Override
	public Menu work() {
		try {
			List<Room> roomList = admin.printSortedRoomsByContent();
			System.out.println(MESSAGE_1);
			for (int i = 0; i < roomList.size(); i++) {
				System.out.println(roomList.get(i).getNumber() + MESSAGE_2
						+ roomList.get(i).getContent() + MESSAGE_3);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return this.getMenu();
	}

}
