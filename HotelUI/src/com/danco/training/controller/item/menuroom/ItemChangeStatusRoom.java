package com.danco.training.controller.item.menuroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.danco.training.controller.item.itemmenu.ItemOperating;
import com.danco.training.controller.menu.Menu;
import com.danco.training.entity.Room;
import com.danco.training.entity.Status;
import com.danco.training.services.ServiceAdmin;

public class ItemChangeStatusRoom extends ItemOperating {

	public final String MESSAGE_1 = "Id room";
	public final String MESSAGE_2 = "status..";
	public final String MESSAGE_3 = "You enter the wrong data";

	public ItemChangeStatusRoom(String name, ServiceAdmin admin) {
		super(name, admin);
	}

	@Override
	public Menu work() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			System.out.println(MESSAGE_1);
			String line = reader.readLine();
			int idRoom = Integer.parseInt(line);

			System.out.println(MESSAGE_2);
			line = reader.readLine();
			Status status = Status.valueOf(line);

			Room room = admin.getRoomByNumber(idRoom);
			admin.changeRoomStatus(room, status);
			admin.updateRoom(room);

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
