package com.danco.training.controller.item.menuroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.danco.training.controller.item.itemmenu.AbstractItemEmpty;
import com.danco.training.controller.menu.AbstractMenu;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Room;
import com.danco.training.services.ServiceAdmin;

public class ItemLastThreeGuestsRoom extends AbstractItemEmpty{
	
	public final String MESSAGE_1 = "Id room";
	public final String MESSAGE_2 =" settel ";

	public ItemLastThreeGuestsRoom(String name, ServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public AbstractMenu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String line = reader.readLine();
			int idRoom = Integer.parseInt(line);
			
			Room room = admin.getRoomByNumber(idRoom);
			
			List<Guest> guestList = admin.printRoomThemGuestsAndDateInSettle(room);
			System.out.println("");
			
			for (int i = 0; i < guestList.size(); i++) {
				System.out.println(guestList.get(i).getName()+MESSAGE_2+ guestList.get(i).getDateInSettle());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(reader != null){
		}
            try {
                reader.close();
            } catch (IOException e) {
            }
		}	
		return this.getMenu();
	}

}
