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

public class ItemCreateRoom extends ItemOperating{
	private static final String PROTOCOL = "room"+";"+"createRoom"+";";
	private static final String MESSAGE_1 = "Number ";
	private static final String MESSAGE_2 = "Content...";
	private static final String MESSAGE_3 = "Stars...";
	private static final String MESSAGE_4 = "Price...";
	private static final String MESSAGE_5 = "Message";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemCreateRoom.class);

	public ItemCreateRoom(String name, Socket socket) {
		super(name, socket);
	}
	
	public Menu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		try {
			
			System.out.println(MESSAGE_1 );
			String number = reader.readLine();
			
			System.out.println(MESSAGE_2);
			String content = reader.readLine();
			
			System.out.println(MESSAGE_3);
			String stars = reader.readLine();
			
			System.out.println(MESSAGE_4);
			String price = reader.readLine();

			String str = PROTOCOL+number+";"+content+";"+stars+";"+price;
			DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(str);
			out.flush();
			
			String line = in.readUTF(); // ���� ���� ������ ������� ������ ������.
			System.out.println(MESSAGE_5+line);
						
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return this.getMenu();

	}

}
