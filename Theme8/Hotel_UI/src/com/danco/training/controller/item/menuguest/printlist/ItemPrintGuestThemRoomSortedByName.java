package com.danco.training.controller.item.menuguest.printlist;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Guest;

public class ItemPrintGuestThemRoomSortedByName extends ItemOperating {
	private static final String PROTOCOL = "0"+";"+"printGuestsSortedByName";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemPrintGuestThemRoomSortedByName.class);

	public ItemPrintGuestThemRoomSortedByName(String name, Socket socket) {
		super(name, socket);
	}

	@Override
	public Menu work() {
		try {
			String str = PROTOCOL;
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(
					socket.getOutputStream());
			out.writeUTF(str);
			out.flush();

			String line = in.readUTF(); 

			List<Guest> guestsList = null;
			for (int i = 0; i < guestsList.size(); i++) {
				System.out.println(guestsList.get(i).getName());

			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
