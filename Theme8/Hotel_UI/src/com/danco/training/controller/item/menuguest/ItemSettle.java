package com.danco.training.controller.item.menuguest;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemSettle extends ItemOperating {
	private static final String PROTOCOL = "4"+";"+"settleGuestInRoom"+";";
	private static final String MESSAGE_1 = "Id guest";
	private static final String MESSAGE_2 = "Id room";
	private static final String MESSAGE_3 = "Date in settle";
	private static final String MESSAGE_4 = "Date out settle";
	private static final String MESSAGE_5 = "Message";
	
	private static final Logger LOGGER = LogManager.getLogger(ItemSettle.class);

	public ItemSettle(String name, Socket socket) {
		super(name, socket);
	}

	@Override
	public Menu work() {
		BufferedReader readers = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String idGuest = readers.readLine();

			System.out.println(MESSAGE_2);
			String idRoom = readers.readLine();

			System.out.println(MESSAGE_3);
			String dateInSettle = readers.readLine();

			System.out.println(MESSAGE_4);
			String dateOutSettle = readers.readLine();
			
			String str = PROTOCOL+idGuest+";"+idRoom+";"+dateInSettle+";"+dateOutSettle;
			DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(str);
			out.flush();
			
			String line = in.readUTF(); // ждем пока сервер отошлет строку текста.
			System.out.println(MESSAGE_5+line);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();

	}

}
