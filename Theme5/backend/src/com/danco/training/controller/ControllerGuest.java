package com.danco.training.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.comparator.Comparator;
import com.danco.training.entity.*;

public class ControllerGuest implements IPrintGuest {

	private static Logger logger = LogManager.getLogger(ControllerGuest.class);

	private List<Guest> guestsList;

	private List<Order> orderList;

	public ControllerGuest(List<Guest> guestsList, List<Order> orderList) {
		this.guestsList = guestsList;
		this.orderList = orderList;

	}

	public void createGuest(Guest guest) {

		guestsList.add(guest);
	}
	
	public void updateGuest(Guest guest) {
		int i = getIndexGuest(guest);
		guestsList.set(i, guest);
		
	}

	public void deleteGuest(Guest guest) {
		int i = getIndexGuest(guest);
		if (i != -1) {
			guestsList.remove(i);
		}
	}

	private int getIndexGuest(Guest guest) {

		int indexGuest = getIndexGuestById(guest.getId());
		return indexGuest;
	}
	
	public int getIdForNewGuest() {

		for (int i = 0; i < guestsList.size(); i++) {
			if (guestsList.get(i) == null) {
				int newId = guestsList.get(i-1).getId()+1;
				return newId;
			}
		}
		return -1;
	}

	private int getIndexGuestById(int Id) {
		for (int i = 0; i < this.guestsList.size(); i++) {
			if (guestsList.get(i) != null && guestsList.get(i).getId() == Id) {
				return i;
			}
		}
		return -1;
	}
	
	public Guest getGuestById(int id) {
		
		for (int i = 0; i < this.guestsList.size(); i++) {
			if (guestsList.get(i) != null && guestsList.get(i).getId() == id) {
				return guestsList.get(i);
			}
		}return null;

	}
	
	public List<Guest> getListGuest() {
		
		return this.guestsList;
		
	}
	
	public int getIdForNewOrder() {

		for (int i = 0; i < orderList.size(); i++) {
			if (orderList.get(i) == null) {
				int newId = orderList.get(i-1).getId()+1;
				return newId;
			}
		}
		return -1;
	}

	@Override
	public List<Guest> printGuestsSortedByName(List<Guest> guestsList) {
		try {
			Collections.sort(guestsList, Comparator.GUEST_BY_NAME);
		} catch (RuntimeException e) {
			logger.info("RuntimeException");
		}

		return guestsList;
	}

	@Override
	public List<Guest> printGuestsSortedByDateOutSettle(List<Guest> guestsList) {
		try {
			Collections.sort(guestsList, Comparator.GUEST_BY_DATE);
		} catch (RuntimeException e) {
			logger.info("RuntimeException");
		}

		return guestsList;
	}

	public void addRoomForGuest(Guest guest, Room room) {

		try {
			// array later Guests
			int[] laterGuestsRoom = room.getIdGuest();
			// record Id new guest
			for (int i = 0; i < laterGuestsRoom.length - 1; i++) {
				laterGuestsRoom[i] = laterGuestsRoom[i + 1];
				laterGuestsRoom[laterGuestsRoom.length - 1] = guest.getId();
			}
			room.setIdGuest(laterGuestsRoom);
		} catch (RuntimeException e) {
			logger.info("RuntimeException");
		}

	}

	public void addServiceForGuest(Guest guest, Service service) {

		try {
			for (int i = 0; i < orderList.size(); i++) {
				// find order of guest
				if (guest.getId() == orderList.get(i).getIdGuest()) {
					// get array id services
					for (int j = 0; j < orderList.get(i).getIdServices().length - 1; j++) {
						int[] arrayService = orderList.get(i).getIdServices();
						// write id service in array
						if (arrayService[j] == 0) {
							arrayService[j] = service.getId();
							orderList.get(i).setIdServices(arrayService);
							break;
						}
					}
				}
			}
		} catch (RuntimeException e) {
			logger.info("RuntimeException");
		}

	}

	public void addDateInSettle(Guest guest, String dateInSettle) {
		try {
			guest.setDateInSettle(dateInSettle);
		} catch (RuntimeException e) {
			logger.info("RuntimeException");
		}

	}

	public void addDateOutSettle(Guest guest, String dateOutSettle) {
		try {
			guest.setDateOutSettle(dateOutSettle);
		} catch (RuntimeException e) {
			logger.info("RuntimeException");
		}

	}

	public void createOrderForGuest(Guest guest) {
		try {
			for (int i = 0; i < orderList.size(); i++) {
				if (orderList.get(i).getIdGuest() == 0) {
					guest.setIdOrder(orderList.get(i).getId());
				}
			}
		} catch (RuntimeException e) {
			logger.info("RuntimeException");
		}

	}

	public int getAmountGuests(List<Guest> guestsList) {
		int amountGuests = 0;
		try {
			for (int i = 0; i < guestsList.size(); i++) {
				if (guestsList.get(i).getIdOrder() != 0) {
					amountGuests++;
				}

			}
		} catch (RuntimeException e) {
			logger.info("");
		}

		return amountGuests;
	}
	
	public Room getRoomInLiveGuest(Guest guest, List<Room> roomsList) {
		Room room;
		for (int i = 0; i < roomsList.size(); i++) {
			int[] laterGuests = roomsList.get(i).getIdGuest();
			if (laterGuests[laterGuests.length - 1] == guest.getId()){
				return room = roomsList.get(i);
			}
		}
		return null;		
	}

	public List<Guest> printGuestsThemRoom(List<Guest> guestsList,
			List<Room> roomsList) {

		List<Guest> newGuestList = new ArrayList<Guest>();
		try {
			for (int i = 0; i < roomsList.size(); i++) {
				int[] laterGuests = roomsList.get(i).getIdGuest();
				// get array later Guests lived in room [i]

				if (roomsList.get(i).getStatus() == Status.NOTFREE) {

					for (int j = 0; j < guestsList.size() - 1; j++) {

						// compare the last entry in room and Id guest[j]
						if (laterGuests[laterGuests.length - 1] == guestsList
								.get(j).getId()) {
							newGuestList.add(guestsList.get(j));
							// guestsList[j].getName()+ " live in " +
							// roomsList[j].getNumber()+ " room"
						}
					}
				}
			}
		} catch (RuntimeException e) {
			logger.info(" ");
		}

		return newGuestList;
	}

	public int[] getGuestThemServices(Guest guest) {

		try {
			for (int i = 0; i < orderList.size(); i++) {
				if (guest.getIdOrder() == orderList.get(i).getId()) {
					return orderList.get(i).getIdServices();
				}
			}
		} catch (RuntimeException e) {
			logger.info("  ");
		}
		return null;
	}

}
