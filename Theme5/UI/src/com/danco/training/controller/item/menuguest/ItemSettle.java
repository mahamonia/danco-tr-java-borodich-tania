package com.danco.training.controller.item.menuguest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.danco.training.controller.item.itemmenu.AbstractItemEmpty;
import com.danco.training.controller.menu.AbstractMenu;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Room;
import com.danco.training.services.ServiceAdmin;

public class ItemSettle extends AbstractItemEmpty{
	
	public final String MESSAGE_1 ="Id guest";
	public final String MESSAGE_2 ="Id room";
	public final String MESSAGE_3 ="Date in settle";
	public final String MESSAGE_4 ="Date out settle";

	public ItemSettle(String name, ServiceAdmin admin) {
		super(name, admin);

	}

	@Override
	public AbstractMenu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String line = reader.readLine();
			int idGuest = Integer.parseInt(line);
			
			System.out.println(MESSAGE_2);
			line = reader.readLine();
			int idRoom = Integer.parseInt(line);
			
			System.out.println(MESSAGE_3);
			String dateInSettle = reader.readLine();
			
			System.out.println(MESSAGE_4);
			String dateOutSettle = reader.readLine();
			
			Guest guest = admin.getGuestById(idGuest);
			Room room = admin.getRoomByNumber(idRoom);
			
			admin.settleGuestInRoom(guest, room, dateInSettle, dateOutSettle);;
			admin.updateGuest(guest);
			admin.updateRoom(room);

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
