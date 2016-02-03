package com.danco.training.controller.item.menuroom.printlist;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Room;

public class ItemPrintFreeRoomSortedByStars extends ItemOperating {
	private static final String PROTOCOL = "0"+";"+"printRoomFreeSortetdByStars";
	private static final String MESSAGE_1 = "List room";
	private static final String MESSAGE_2 = " have stars ";
	private static final Logger LOGGER = LogManager.getLogger(ItemPrintFreeRoomSortedByStars.class);

	public ItemPrintFreeRoomSortedByStars(String name, Socket socket) {
		super(name, socket);
	}

	@Override
	public Menu work() {
		try {
			String str = PROTOCOL;
			DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(str);
			out.flush();
			
			String line = in.readUTF(); // ���� ���� ������ ������� ������ ������.
	
			List<Room> roomList = null;
			System.out.println(MESSAGE_1);
			for (int i = 0; i < roomList.size(); i++) {
				System.out.println(roomList.get(i).getNumber() + MESSAGE_2
						+ roomList.get(i).getStars());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return this.getMenu();
	}

}
