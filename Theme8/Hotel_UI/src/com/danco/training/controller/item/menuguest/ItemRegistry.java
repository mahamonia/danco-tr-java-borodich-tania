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

public class ItemRegistry extends ItemOperating {
	private static final String PROTOCOL = "guest"+";"+"createGuest"+";";
	private static final String MESSAGE_1 = "Name...";
	private static final String MESSAGE_2 = "Pasport...";
	private static final String MESSAGE_3 = "Date in settle...";
	private static final String MESSAGE_4 = "Message ";
	private static final Logger LOGGER = LogManager.getLogger(ItemRegistry.class);

	public ItemRegistry(String name, Socket socket) {
		super(name, socket);
	}

	public Menu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		try {
			System.out.println(MESSAGE_1);
			String name = reader.readLine();
			System.out.println(MESSAGE_2);
			String pasport = reader.readLine();
			System.out.println(MESSAGE_3);
			String dateInSettle = reader.readLine();
			
			String str = PROTOCOL+name+";"+pasport+";"+dateInSettle;
			DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(str);
			out.flush();
			
			String line = in.readUTF(); // ���� ���� ������ ������� ������ ������.
			System.out.println(MESSAGE_4+line);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();

	}

}
