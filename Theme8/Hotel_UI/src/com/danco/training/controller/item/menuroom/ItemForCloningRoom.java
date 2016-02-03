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

public class ItemForCloningRoom extends ItemOperating{
	private static final String PROTOCOL = "1"+";"+"cloneRoom"+";";
	private static final String MESSAGE_1 ="Id room..";
	private static final String MESSAGE_2 ="Message";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemForCloningRoom.class);

	public ItemForCloningRoom(String name, Socket socket) {
		super(name, socket);
	}
	
	public Menu work(){
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
			System.out.println(MESSAGE_2+line);


		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} 
		return this.getMenu();
	}

}
