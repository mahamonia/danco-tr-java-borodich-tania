package com.danco.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.ConfigProperty;
import com.danco.annotation.Injection;
import com.danco.api.IControllerGuest;
import com.danco.api.IParseUtilityCSVForGuest;
import com.danco.comparator.Comparator;
import com.danco.training.entity.*;

public class ControllerGuest implements IControllerGuest {

	private static final Logger LOGGER = LogManager
			.getLogger(ControllerGuest.class);

	private List<Guest> guestsList;
	private List<Order> orderList;

	@ConfigProperty(configName = "property.properties", propertyName = "amount", type = "int")
	private int maxAmountGuestsInRoom;
	
	@Injection
	private IParseUtilityCSVForGuest utility;

	public ControllerGuest(List<Guest> guestsList, List<Order> orderList) {
		this.guestsList = guestsList;
		this.orderList = orderList;

	}

	@Override
	public void createGuest(Guest guest) {
		int idOrder = getIdForNewOrder();
		Order order = new Order(idOrder, 0, guest);
		guest.setOrder(order);
		try {
			this.guestsList.add(guest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void updateGuest(Guest guest) {
		try {
			int i = getIndexGuest(guest);
			this.guestsList.set(i, guest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void deleteGuest(Guest guest) {
		try {
			int i = getIndexGuest(guest);
			if (i != -1) {
				this.guestsList.remove(i);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

	}

	private int getIndexGuest(Guest guest) {
		int indexGuest = 0;
		try {
			indexGuest = getIndexGuestById(guest.getId());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return indexGuest;
	}

	@Override
	public int getIdForNewGuest() {
		int newId = 0;

		try {
			newId = this.guestsList.size() + 1;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return newId;

	}

	private int getIndexGuestById(int Id) {
		try {
			for (int i = 0; i < this.guestsList.size(); i++) {
				if (this.guestsList.get(i).getId() == Id) {
					return i;
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return -1;
	}

	@Override
	public Guest getGuestById(int id) {
		try {
			for (int i = 0; i < this.guestsList.size(); i++) {
				if (this.guestsList.get(i).getId() == id) {
					return this.guestsList.get(i);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;

	}

	@Override
	public List<Guest> getListGuest() {

		return this.guestsList;
	}

	@Override
	public void setListGuest(List<Guest> guestsList) {

		this.guestsList = guestsList;
	}

	@Override
	public List<Order> getListOrder() {

		return this.orderList;
	}

	@Override
	public int getIdForNewOrder() {
		int newId = 0;

		try {
			newId = this.orderList.size() + 1;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return newId;
	}

	@Override
	public Order getOrderById(int id) {
		try {
			for (int i = 0; i < this.orderList.size(); i++) {
				if (this.orderList.get(i).getId() == id) {
					return this.orderList.get(i);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;

	}

	@Override
	public List<Guest> printGuestsSortedByName(List<Guest> guestsList) {
		try {
			Collections.sort(guestsList, Comparator.GUEST_BY_NAME);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return guestsList;
	}

	@Override
	public List<Guest> printGuestsSortedByDateOutSettle(List<Guest> guestsList) {
		try {
			Collections.sort(guestsList, Comparator.GUEST_BY_DATE);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return guestsList;
	}

	@Override
	public void addRoomForGuest(Guest guest, Room room) {

		try {
			List<Guest> guestList = room.getGuestList();

			if (maxAmountGuestsInRoom > guestList.size()) {
				guestList.add(guest);
			} else {
				List<Guest> newList = new ArrayList<Guest>();
				for (int i = 0; i < maxAmountGuestsInRoom; i++) {
					// write latest guest in newList
					newList.set(i, guestList.get(i
							+ (guestList.size() - maxAmountGuestsInRoom)));
				}
				newList.set(maxAmountGuestsInRoom, guest);
				room.setGuestList(newList);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

	}

	@Override
	public void addServiceForGuest(Guest guest, Service service) {

		try {
			Order order = guest.getOrder();
			List<Service> serviceList = order.getServiceList();
			serviceList.add(service);
			order.setServiceList(serviceList);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

	}

	@Override
	public void addDateInSettle(Guest guest, String dateInSettle) {
		try {
			guest.setDateInSettle(dateInSettle);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

	}

	@Override
	public void addDateOutSettle(Guest guest, String dateOutSettle) {
		try {
			guest.setDateOutSettle(dateOutSettle);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public Room getRoomInLiveGuest(Guest guest, List<Room> roomsList) {
		try {
			for (int i = 0; i < roomsList.size(); i++) {
				if (roomsList.get(i).getStatus() == Status.NOTFREE) {
					List<Guest> guestList = roomsList.get(i).getGuestList();
					for (int j = guestList.size(); j > 0; j--) {
						if (guestList.get(j).equals(guest)) {
							return roomsList.get(i);

						}
					}
				}
				break;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Service> getGuestThemServices(Guest guest) {
		List<Service> serviceList = new ArrayList<>();

		try {
			serviceList = guest.getOrder().getServiceList();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return serviceList;
	}

	@Override
	public int getSumOrderGuest(Guest guest) {
		int sum = 0;
		try {
			return guest.getOrder().getSumPrice();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return sum;

	}

	@Override
	public int getAmountGuest() {

		int amountGuest = 0;
		try {
			for (int i = 0; i < this.guestsList.size(); i++) {
				amountGuest++;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return amountGuest;

	}

	@Override
	public List<Guest> importGuestsList() {
		return utility.importData();
	}

	@Override
	public void exportGuestsList(List<Guest> guestsList) {
		utility.exportData(guestsList);
	}

}
