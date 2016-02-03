package com.danco.training.controller.item.menuservice.printlist;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.DailService;

public class ItemPrintDailServiceThemPriceSortedByName extends ItemOperating {
	private static final String MESSAGE_1 = "List dail service";
	private static final String MESSAGE_2 = " cost ";
	private static final String MESSAGE_3 = "Message ";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemPrintDailServiceThemPriceSortedByName.class);

	public ItemPrintDailServiceThemPriceSortedByName(String name,
			Socket socket) {
		super(name, socket);
	}

	@Override
	public Menu work() {
		try {
			String str = "name";
			DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(str);
			out.flush();
			
			String line = in.readUTF(); // ждем пока сервер отошлет строку текста.
			System.out.println(MESSAGE_3+line);
			
			List<DailService> serviceList = null;
			System.out.println(MESSAGE_1);
			for (int i = 0; i < serviceList.size(); i++) {
				System.out.println(serviceList.get(i).getName() + MESSAGE_2
						+ serviceList.get(i).getPrice());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return this.getMenu();
	}

}
