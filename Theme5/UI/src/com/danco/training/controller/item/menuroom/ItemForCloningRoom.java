package com.danco.training.controller.item.menuroom;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Room;
import com.danco.training.services.ServiceAdmin;

public class ItemForCloningRoom extends ItemOperating{
	public final String MESSAGE_1 ="Id room..";
	public final String MESSAGE_2 ="Clone create!";

	public ItemForCloningRoom(String name, ServiceAdmin admin) {
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
			admin.createRoom(room);
			
			System.out.println(MESSAGE_2);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
			}
			try {
				reader.close();
			} catch (IOException e) {
			}
		}
		return this.getMenu();
	}

}
