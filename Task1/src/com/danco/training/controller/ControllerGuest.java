package com.danco.training.controller;

import java.util.Arrays;

import com.danco.training.comparators.SortGuestByDateOutSettle;
import com.danco.training.comparators.SortGuestByName;
import com.danco.training.comparators.TypeSort;
import com.danco.training.entity.*;

public class ControllerGuest implements IPrintGuest {

	private Guest[] guestsList;
	private final int MAX_GUESTS;
	private Order[] orderList;

	public ControllerGuest(Guest[] guestsList, Order[] orderList) {
		MAX_GUESTS = guestsList.length;
		this.guestsList = guestsList;
		this.orderList = orderList;

	}

	public void createGuests(Guest guest) {

		int i = getNumberForNewGuest();
		guestsList[i] = guest;
	}

	private int getNumberForNewGuest() {

		for (int i = 0; i < guestsList.length; i++) {
			if (guestsList[i] == null) {
				return i;
			}
		}
		return -1;
	}

	public void deleteGuest(Guest guest) {
		int i = getNumberGuest(guest);
		if (i != -1) {
			guestsList[i] = null;
		}
	}

	private int getNumberGuest(Guest guest) {

		int numberGuest = getNumberGuestById(guest.getId());
		return numberGuest;
	}

	private int getNumberGuestById(int Id) {
		for (int i = 0; i < this.guestsList.length; i++) {
			if (guestsList[i] != null && guestsList[i].getId() == Id) {
				return i;
			}
		}
		return -1;
	}

	public void addRoomForGuest(Guest guest, Room room) {

		// array later Guests
		int[] laterGuestsRoom = room.getIdGuest();
		// record Id new guest
		for (int i = 0; i < laterGuestsRoom.length - 1; i++) {
			laterGuestsRoom[i] = laterGuestsRoom[i + 1];
			laterGuestsRoom[laterGuestsRoom.length - 1] = guest.getId();
		}
		room.setIdGuest(laterGuestsRoom);
	}

	public void addServiceForGuest(Guest guest, Service service) {

		for (int i = 0; i < orderList.length-1; i++) {
			// find order of guest
			if (guest.getId() == orderList[i].getIdGuest()) {
				// get array id services
				for (int j = 0; j < orderList[i].getIdServices().length-1; j++) {
					int[] arrayService = orderList[i].getIdServices();
					// write id service in array
					if (arrayService[j] == 0) {
						arrayService[j] = service.getId();
						orderList[i].setIdServices(arrayService);
						break;
					}
				}
			}
		}
	}

	public void addDateInSettle(Guest guest, String dateInSettle) {

		guest.setDateInSettle(dateInSettle);

	}

	public void addDateOutSettle(Guest guest, String dateOutSettle) {

		guest.setDateOutSettle(dateOutSettle);

	}

	public void createOrderForGuest(Guest guest) {

		for (int i = 0; i < orderList.length - 1; i++) {
			if (orderList[i].getIdGuest() == 0) {
				guest.setIdOrder(orderList[i].getId());
			}
		}
	}

	@Override
	public void printGuests(Guest[] guestsList, TypeSort type) {

		for (int i = 0; i < guestsList.length; i++) {
			System.out.println(guestsList[i].getName());

		}
	}
	
	public int amountGuests(Guest[] guestsList) {
		int amountGuests=0;

		for (int i = 0; i < guestsList.length; i++) {
			if (guestsList[i].getIdOrder()!=0){
			amountGuests++;}

		}
		return amountGuests;
	}
	

	@Override
	public void printGuestsThemRoom(Guest[] guestsList, Room[] roomsList,
			TypeSort type) {

		sortedGuest(guestsList, type);

		for (int i = 0; i < roomsList.length; i++) {
			int[] laterGuests = roomsList[i].getIdGuest();
			// get array later Guests lived in room [i]

			if (roomsList[i].getStatus() == Status.NOTFREE) {

				for (int j = 0; j < guestsList.length; j++) {

					// compare the last entry in room and Id guest[j]
					if (laterGuests[laterGuests.length-1] == guestsList[j]
							.getId()) {
						System.out.println(guestsList[j].getName()
								+ " live in " + roomsList[j].getNumber()
								+ " room");
					}
				}
			}
		}
		System.out.println();
	}

	public int[] getGuestThemService(Guest guest) {

		for (int i = 0; i < orderList.length - 1; i++) {
			if (guest.getIdOrder() == orderList[i].getId()) {
				return orderList[i].getIdServices();
			}
		}
		return null;
	}

	public void sortedGuest(Guest[] guestsList, TypeSort type) {

		switch (type) {
		case BY_NAME:
			Arrays.sort(guestsList, new SortGuestByName());
			break;
		case BY_DATE:
			Arrays.sort(guestsList, new SortGuestByDateOutSettle());
			break;
		default:
			Arrays.sort(guestsList, new SortGuestByName());
			break;
		}
	}

	public int getMAX_GUESTS() {
		return MAX_GUESTS;
	}

}
