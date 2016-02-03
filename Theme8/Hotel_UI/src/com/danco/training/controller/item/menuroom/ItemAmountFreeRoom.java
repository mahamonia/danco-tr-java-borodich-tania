package com.danco.training.controller.item.menuroom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemAmountFreeRoom extends ItemOperating{
	private static final String PROTOCOL = "0"+";"+"getAmountFreeRoom"+";";
	public final String MESSAGE = "Amount free room = ";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemAmountFreeRoom.class);

	public ItemAmountFreeRoom(String name, Socket socket) {
		super(name, socket);

	}

	@Override
	public Menu work()  {
		try {
					
			String str = PROTOCOL;
			DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(str);
			out.flush();
			
			String line = in.readUTF(); // ждем пока сервер отошлет строку текста.
			System.out.println(MESSAGE+line);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return this.getMenu();
	}

}
