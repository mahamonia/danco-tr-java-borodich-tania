package com.danco.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.ConfigProperty;
import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerGuest;
import com.danco.api.backend.IParseUtilityCSVForGuest;
import com.danco.comparator.Comparator;
import com.danco.training.dao.CheckDao;
import com.danco.training.dao.GuestDao;
import com.danco.training.entity.*;

public class ControllerGuest implements IControllerGuest {

	private static final Logger LOGGER = LogManager
			.getLogger(ControllerGuest.class);

	private volatile GuestDao guestDao;
	private volatile CheckDao checkDao;

	@ConfigProperty(configName = "property.properties", propertyName = "amount", type = "int")
	private int maxAmountGuestsInRoom;

	@Injection
	private IParseUtilityCSVForGuest utility;

	public ControllerGuest(GuestDao guestDao, CheckDao checkDao) {
		this.guestDao = guestDao;
		this.checkDao = checkDao;

	}

	@Override
	public void createGuest(Guest guest) {
//		int idCheck = getIdForNewCheck();
//		Check check = new Check(null, null, guest);
//		check.setId(idCheck);
//		this.checkList.add(check);
//		guest.setCheck(check);
//		try {
//			this.guestsList.add(guest);
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		}
	}

	@Override
	public void updateGuest(Guest guest) {
//		try {
//			int i = getIndexGuest(guest);
//			this.guestsList.set(i, guest);
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		}
	}

	@Override
	public void deleteGuest(Guest guest) {
//		try {
//			int i = getIndexGuest(guest);
//			if (i != -1) {
//				this.guestsList.remove(i);
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		}

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
		return 0;
//		int newId = 0;
//
//		try {
//			newId = this.guestsList.size() + 1;
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		}
//		return newId;

	}

	private int getIndexGuestById(int Id) {
//		try {
//			for (int i = 0; i < this.guestsList.size(); i++) {
//				if (this.guestsList.get(i).getId() == Id) {
//					return i;
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		}

		return -1;
	}

	@Override
	public Guest getGuestById(int id) {
//		try {
//			for (int i = 0; i < this.guestsList.size(); i++) {
//				if (this.guestsList.get(i).getId() == id) {
//					return this.guestsList.get(i);
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		}
		return null;

	}

	@Override
	public List<Guest> getListGuest() {
		return null;

	//	return this.guestsList;
	}

	@Override
	public void setListGuest(List<Guest> guestsList) {

		//this.guestsList = guestsList;
	}

	@Override
	public List<Check> getListCheck() {
		return null;

		//return this.checkList;
	}

	@Override
	public int getIdForNewCheck() {
		int newId = 0;

//		try {
//			newId = this.checkList.size() + 1;
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		}
		return newId;
	}

	@Override
	public Check getCheckById(int id) {
//		try {
//			for (int i = 0; i < this.checkList.size(); i++) {
//				if (this.checkList.get(i).getId() == id) {
//					return this.checkList.get(i);
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		}
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
	public void addServiceForGuest(Guest guest, DailService service) {

		try {
			Check check = guest.getCheck();
			List<DailService> serviceList = check.getServiceList();
			serviceList.add(service);
			check.setServiceList(serviceList);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

	}

	@Override
	public void addDateInSettle(Guest guest, String dateInSettle) {
		try {
			guest.getCheck().setDateInSettle(LocalDateTime.parse(dateInSettle));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

	}

	@Override
	public void addDateOutSettle(Guest guest, String dateOutSettle) {
		try {
			guest.getCheck().setDateOutSettle(
					LocalDateTime.parse(dateOutSettle));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@SuppressWarnings("unused")
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
	public List<DailService> getGuestThemServices(Guest guest) {
		List<DailService> serviceList = new ArrayList<>();

		try {
			serviceList = guest.getCheck().getServiceList();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return serviceList;
	}

	@Override
	public int getSumOrderGuest(Guest guest) {
		int sum = 0;
		try {
			List<DailService> serviceList = guest.getCheck().getServiceList();
			
			for (int i = 0; i < guest.getCheck().getServiceList().size(); i++) {
				sum = +serviceList.get(i).getPrice();
				if (serviceList.get(i).getId() >= 5) { // begin additional
														// services
					AdditionalService service = (AdditionalService) serviceList
							.get(i);
					sum = +service.getAddPrice();
				}
			}
			return sum;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return sum;

	}

	@Override
	public int getAmountGuest() {

		int amountGuest = 0;
//		try {
//			for (int i = 0; i < this.guestsList.size(); i++) {
//				amountGuest++;
//			}
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//		}
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
