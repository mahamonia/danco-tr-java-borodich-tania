package com.danco.training.controller.item.menuroom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;

public class ItemExportRoomList extends ItemOperating{
	private static final String PROTOCOL = "0"+";"+"exportRoomsList";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemExportRoomList.class);
	private static final String MESSAGE = "Message ";

	public ItemExportRoomList(String name, Socket socket) {
		super(name, socket);
	}
	
	public Menu work() {

		try {
			String str = PROTOCOL;
			DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(str);
			out.flush();
			
			String line = in.readUTF(); // ���� ���� ������ ������� ������ ������.
			System.out.println(MESSAGE+line);


		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();
		
	}

}
