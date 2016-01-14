package com.danco.training.run;

import java.io.IOException;
import java.util.List;

import com.danco.training.controller.ControllerAdditionalService;
import com.danco.training.controller.ControllerDailService;
import com.danco.training.controller.ControllerGuest;
import com.danco.training.controller.ControllerRoom;
import com.danco.training.controller.menu.AbstractMenu;
import com.danco.training.controller.workmenu.MenuBuilder;
import com.danco.training.controller.workmenu.WorkingMenu;
import com.danco.training.entity.AdditionalService;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Order;
import com.danco.training.entity.Room;
import com.danco.training.entity.Service;
import com.danco.training.services.ServiceAdmin;
import com.danco.training.utility.ParseUtility;



public class Run {

	public static void main(String[] args) throws IOException {
		
		// BACKEND
				ParseUtility utility = new ParseUtility();
				List<Guest> guestsList = utility.getGuests();
				List<Room> roomsList = utility.getRooms();
				List<Order> ordersList = utility.getOrders();
				List<Service> dailServicesList = utility.getDailServices();
				List<AdditionalService> additionalServicesList = utility.getAdditionalServices();

				ControllerGuest contGuest = new ControllerGuest(guestsList, ordersList);
				ControllerRoom contRoom = new ControllerRoom(roomsList);
				ControllerDailService contDailService = new ControllerDailService(
						dailServicesList);
				ControllerAdditionalService contAddService = new ControllerAdditionalService(
						additionalServicesList);

				ServiceAdmin admin = ServiceAdmin.getInstance(contGuest, contRoom, contDailService, contAddService);
				
				// UI
		MenuBuilder builder = new MenuBuilder(admin);
		AbstractMenu mainMenu = builder.buildMenu();
		WorkingMenu menu = new WorkingMenu(mainMenu);
		menu.workMenu();
	}

}
