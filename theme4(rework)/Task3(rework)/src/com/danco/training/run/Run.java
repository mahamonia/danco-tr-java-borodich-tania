package com.danco.training.run;

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

		String strGuest = args[0];
		String strRoom= args[1];
		String strOrder= args[2];
		String strDailService= args[3];
		String strAdditionalService=args[4];

		ParseUtility utility = new ParseUtility(strGuest, strRoom, strOrder, strDailService, strAdditionalService);
		
		Guest[] guestsList = utility.getGuests();
		Room[] roomsList = utility.getRooms();
		Order[] ordersList = utility.getOrders();
		Service[] dailServicesList = utility.getDailServices();
		Service[] additionalServicesList = utility.getAdditionalServices();

		ControllerGuest contGuest = new ControllerGuest(guestsList, ordersList);
		ControllerRoom contRoom = new ControllerRoom(roomsList);
		ControllerDailService contDailService = new ControllerDailService(
				dailServicesList);
		ControllerAdditionalService contAddService = new ControllerAdditionalService(
				additionalServicesList);

		ServiceAdmin admin = new ServiceAdmin(contGuest, contRoom,
				contDailService, contAddService);

		System.out.println("roomsList");
		
		admin.printSortetdRoomsByPrice(roomsList);
		
		for (int i = 0; i < roomsList.length; i++) {
			System.out.println(roomsList[i].getNumber()+" cost "+roomsList[i].getPrice());
		}

		System.out.println("AmountFreeRoom");
		System.out.println(admin.getAmountFreeRoom(roomsList));

		System.out.println("SumOrderGuest");
		System.out.println(admin.getSumOrderGuest(guestsList[2]));

		Guest[] newGuestsList = admin.printRoomThemGuestsAndDateInSettle(roomsList[2], guestsList);
		System.out.println("RoomThemGuestsAndDateInSettle");
		for (int i = 0; i < newGuestsList.length; i++) {
			System.out.println(newGuestsList[i].getName()+" in settle "+newGuestsList[i].getDateInSettle());
		}

		
		System.out.println("GuestThemServiceList");
		Service[] service = admin.printGuestThemServiceList(guestsList[2]);
		for (int i = 0; i < service.length; i++) {
			System.out.println(service[i].getName());
		}

		admin.settleGuestInRoom(guestsList[0], roomsList[0], "02.12.2014","03.12.2014");
		admin.printGuestsThemRoom(guestsList, roomsList);

		utility.setGuests(guestsList);
		utility.setRooms(roomsList);
		utility.setOrders(ordersList);

	}

}
