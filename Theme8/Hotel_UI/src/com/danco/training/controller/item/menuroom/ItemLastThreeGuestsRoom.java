package com.danco.training.controller.item.menuroom;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Guest;

public class ItemLastThreeGuestsRoom extends ItemOperating {
	private static final String PROTOCOL = "1"+";"+"getListGuestRoom"+";";
	private static final String MESSAGE_1 = "Id room";
	private static final String MESSAGE_2 = " settel ";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemLastThreeGuestsRoom.class);

	public ItemLastThreeGuestsRoom(String name, Socket socket) {
		super(name, socket);
	}

	@Override
	public Menu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String idRoom = reader.readLine();

			String str = PROTOCOL+idRoom;
			DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(str);
			out.flush();
			
			String line = in.readUTF(); // ждем пока сервер отошлет строку текста.

			List<Guest> guestList = null;

			int amount = 0;
			if (guestList.size() >= 3) {
				amount = 3;
			}

			for (int i = guestList.size(); i < guestList.size() - amount; i--) {
				System.out.println(guestList.get(i).getName() + MESSAGE_2
						+ guestList.get(i).getDateInSettle());
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
