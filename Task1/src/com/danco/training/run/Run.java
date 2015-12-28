package com.danco.training.run;

import com.danco.training.comparators.TypeSort;
import com.danco.training.controller.ControllerAdditionalService;
import com.danco.training.controller.ControllerDailService;
import com.danco.training.controller.ControllerGuest;
import com.danco.training.controller.ControllerRoom;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Order;
import com.danco.training.entity.Room;
import com.danco.training.entity.Service;
import com.danco.training.services.ServiceAdmin;
import com.danco.training.utility.ParseUtility;

public class Run {

	public static void main(String[] args) {

		ParseUtility utility = new ParseUtility();
		Guest[] guestsList = utility.getGuests();
		Room[] roomsList = utility.getRooms();
		Order[] ordersList = utility.getOrders();
		Service[] dailServicesList = utility.getDailService();
		Service[] additionalServicesList = utility.getAdditionalServices();

		ControllerGuest contGuest = new ControllerGuest(guestsList, ordersList);
		ControllerRoom contRoom = new ControllerRoom(roomsList);
		ControllerDailService contDailService = new ControllerDailService(
				dailServicesList);
		ControllerAdditionalService contAddService = new ControllerAdditionalService(
				additionalServicesList);

		ServiceAdmin admin = new ServiceAdmin(contGuest, contRoom,
				contDailService, contAddService);

		admin.printRooms(roomsList, TypeSort.BY_PRICE);
		admin.printRoomFree(roomsList, TypeSort.BY_NUMBER);
		admin.printGuestsThemRoom(guestsList, roomsList, TypeSort.BY_NAME);

		System.out.println(admin.amountFreeRoom(roomsList));
		System.out.println(admin.amountGuest(guestsList));

		System.out.println(admin.getSumOrderGuest(guestsList[1]));

		admin.printRoomThemGuests(roomsList[2], guestsList);

		admin.printGuestThemServiceList(guestsList[1]);

		admin.inSettleGuestRoom(guestsList[0], roomsList[0], "02.12.2014",
				"03.12.2014");
		admin.printGuestsThemRoom(guestsList, roomsList, TypeSort.BY_DATE);

		utility.setGuests(guestsList);
		utility.setRooms(roomsList);
		utility.setOrders(ordersList);

	}

}
