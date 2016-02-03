package com.danco.training.controller.item.menuroom;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemChangePriceRoom extends ItemOperating {
	private static final String PROTOCOL = "2"+";"+"changeRoomPrice"+";";
	private static final String MESSAGE_1 ="Id room";
	private static final String MESSAGE_2 ="price..";
	private static final String MESSAGE_3 ="Message";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemChangePriceRoom.class);

	public ItemChangePriceRoom(String name, Socket socket) {
		super(name, socket);

	}

	@Override
	public Menu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String idRoom = reader.readLine();

			System.out.println(MESSAGE_2);
			String price = reader.readLine();
			
			String str = PROTOCOL+idRoom+";"+price;
			DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(str);
			out.flush();
			
			String line = in.readUTF(); // ���� ���� ������ ������� ������ ������.
			System.out.println(MESSAGE_3+line);
			

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
