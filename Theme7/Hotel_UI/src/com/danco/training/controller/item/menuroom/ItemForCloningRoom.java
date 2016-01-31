package com.danco.training.controller.item.menuroom;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.IServiceAdmin;
import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Room;

public class ItemForCloningRoom extends ItemOperating{
	public final String MESSAGE_1 ="Id room..";
	public final String MESSAGE_2 ="Clone create!";
	private static final Logger LOGGER = LogManager
			.getLogger(ItemForCloningRoom.class);

	public ItemForCloningRoom(String name, IServiceAdmin admin) {
		super(name, admin);
	}
	
	public Menu work(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String line = reader.readLine();
			int idRoom = Integer.parseInt(line);

			Room room = admin.getRoomByNumber(idRoom);
			admin.cloneRoom(room);	

			
			System.out.println(MESSAGE_2);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} 
		return this.getMenu();
	}

}
