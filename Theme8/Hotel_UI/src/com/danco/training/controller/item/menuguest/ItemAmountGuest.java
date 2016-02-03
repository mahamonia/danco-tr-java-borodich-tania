package com.danco.training.controller.item.menuguest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemAmountGuest extends ItemOperating {
	private static final String PROTOCOL = "0"+";"+"getAmountGuest";
	private static final String MESSAGE_1 = "Amount guest = ";

	private static final Logger LOGGER = LogManager.getLogger(ItemAmountGuest.class);

	public ItemAmountGuest(String name, Socket socket) {
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
			
			String line = in.readUTF(); // ждем пока сервер отошлет строку текста.
			
			System.out.println(MESSAGE_1 + line);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
	}

}
