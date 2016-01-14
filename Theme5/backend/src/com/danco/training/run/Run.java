package com.danco.training.run;

import java.util.List;

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
	


	public static void main(String[] args) throws Exception{

//		ParseUtility utility = new ParseUtility();
//		List<Guest> guestsList = utility.getGuests();
//		List<Room> roomsList = utility.getRooms();
//		List<Order> ordersList = utility.getOrders();
//		List<Service> dailServicesList = utility.getDailServices();
//		List<Service> additionalServicesList = utility.getAdditionalServices();
//
//		ControllerGuest contGuest = new ControllerGuest(guestsList, ordersList);
//		ControllerRoom contRoom = new ControllerRoom(roomsList);
//		ControllerDailService contDailService = new ControllerDailService(
//				dailServicesList);
//		ControllerAdditionalService contAddService = new ControllerAdditionalService(
//				additionalServicesList);
//
//		ServiceAdmin admin = new ServiceAdmin(contGuest, contRoom,
//				contDailService, contAddService);
//
//		System.out.println("size guest "+ guestsList.size());
//		System.out.println("roomsList");
//		
//		admin.printSortetdRoomsByPrice(roomsList);
//		
//		for (int i = 0; i < roomsList.size(); i++) {
//			System.out.println(roomsList.get(i).getNumber()+" cost "+roomsList.get(i).getPrice());
//		}
//
//		System.out.println("AmountFreeRoom");
//		System.out.println(admin.getAmountFreeRoom(roomsList));
//
//		System.out.println("SumOrderGuest");
//		System.out.println(admin.getSumOrderGuest(guestsList.get(2)));
//
//		List<Guest> newGuestsList = admin.printRoomThemGuestsAndDateInSettle(roomsList.get(0), guestsList);
//		System.out.println("RoomThemGuestsAndDateInSettle");
//		for (int i = 0; i < newGuestsList.size(); i++) {
//			System.out.println(newGuestsList.get(i).getName()+" in settle "+newGuestsList.get(i).getDateInSettle());
//		}
//
//		
////		System.out.println("GuestThemServiceList");
////		List<Service> service = admin.printGuestThemServiceList(guestsList.get(2));
////		for (int i = 0; i < service.size(); i++) {
////			System.out.println(service.get(i).getName());
////		}
//
//	//	admin.settleGuestInRoom(guestsList.get(0), roomsList.get(0), "02.12.2014","03.12.2014");
//		admin.printGuestsThemRoom(guestsList, roomsList);
//
//		utility.setGuests(guestsList);
//		utility.setRooms(roomsList);
//		utility.setOrders(ordersList);

	}

}
