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

public class ItemOutSettle extends ItemOperating {
	private static final String PROTOCOL = "2"+";"+"settleGuestOutRoom"+";";
	private static final String MESSAGE_1 = "Id guest";
	private static final String MESSAGE_2 = "Message";
	
	private static final Logger LOGGER = LogManager.getLogger(ItemOutSettle.class);

	public ItemOutSettle(String name, Socket socket) {
		super(name, socket);
	}

	@Override
	public Menu work() {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String idGuest = reader.readLine();
		
			String str = PROTOCOL+idGuest;
			DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(str);
			out.flush();
			
			String line = in.readUTF(); // ждем пока сервер отошлет строку текста.
			System.out.println(MESSAGE_2+line);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} 
		return this.getMenu();
	}

}
