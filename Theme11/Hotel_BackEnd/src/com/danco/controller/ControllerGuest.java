package com.danco.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerGuest;
import com.danco.api.backend.IParseUtilityCSVForGuest;
import com.danco.model.dao.DataSource;
import com.danco.model.dao.GuestDao;
import com.danco.model.entity.*;

public class ControllerGuest implements IControllerGuest {

	private static final Logger LOGGER = LogManager
			.getLogger(ControllerGuest.class);

	@Injection
	private IParseUtilityCSVForGuest utility;

	private GuestDao guestDao;
	private DataSource source;

	public ControllerGuest(DataSource source, GuestDao guestDao) {
		this.source = source;
		this.guestDao = guestDao;

	}

	@Override
	public void createGuest(Guest guest) {
		try {
			guestDao.create(source, guest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void updateGuest(int idGuest) {
		try {
			guestDao.update(source, idGuest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

	}

	@Override
	public void deleteGuest(int idGuest) {
		try {
			guestDao.delete(source, idGuest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public Guest getGuest(int idGuest) {
		return guestDao.getById(source, idGuest);
	}

	@Override
	public List<Guest> getListGuest() {
		return guestDao.getList(source);
	}

	@Override
	public List<Guest> getListGuestSortedByName() {
		return guestDao.getListGuestSortedByName(source);
	}

	@Override
	public List<Guest> getListGuestSortedByDateOutSettle() {
		return guestDao.getListGuestSortedByDateOutSettle(source);
	}

	@Override
	public int getAmountGuest() {
		return guestDao.getList(source).size();
	}

	@Override
	public List<Guest> importGuestsList() {
		return utility.importData();
	}

	@Override
	public void exportGuestsList() {
		utility.exportData(guestDao.getList(source));

	}

	// @Override
	// public void addRoomForGuest(int idGuest, int idRoom) {
	//
	// // try {
	// // List<Guest> guestList = room.getGuestList();
	// //
	// // if (maxAmountGuestsInRoom > guestList.size()) {
	// // guestList.add(guest);
	// // } else {
	// // List<Guest> newList = new ArrayList<Guest>();
	// // for (int i = 0; i < maxAmountGuestsInRoom; i++) {
	// // // write latest guest in newList
	// // newList.set(i, guestList.get(i
	// // + (guestList.size() - maxAmountGuestsInRoom)));
	// // }
	// // newList.set(maxAmountGuestsInRoom, guest);
	// // room.setGuestList(newList);
	// // }
	// //
	// // } catch (Exception e) {
	// // LOGGER.error(e.getMessage());
	// // }
	//
	// }
	//
	// @Override
	// public void addServiceForGuest(int idGuest, int idService) {
	//
	// // try {
	// // Check check = guest.getCheck();
	// // List<Service> serviceList = check.getServiceList();
	// // serviceList.add(service);
	// // check.setServiceList(serviceList);
	// // } catch (Exception e) {
	// // LOGGER.error(e.getMessage());
	// // }


	// @SuppressWarnings("unused")
	// @Override
	// public Room getRoomInLiveGuest(int guest) {
	// // try {
	// // for (int i = 0; i < roomsList.size(); i++) {
	// // if (roomsList.get(i).getStatus() == Status.NOTFREE) {
	// // List<Guest> guestList = roomsList.get(i).getGuestList();
	// // for (int j = guestList.size(); j > 0; j--) {
	// // if (guestList.get(j).equals(guest)) {
	// // return roomsList.get(i);
	// //
	// // }
	// // }
	// // }
	// // break;
	// // }
	// // } catch (Exception e) {
	// // LOGGER.error(e.getMessage());
	// // }
	// return null;
	// }
	//
	// @Override
	// public List<Service> getGuestThemServices(int idGuest) {
	// List<Service> serviceList = new ArrayList<>();
	//
	// // try {
	// // serviceList = guest.getCheck().getServiceList();
	// // } catch (Exception e) {
	// // LOGGER.error(e.getMessage());
	// // }
	// return serviceList;
	// }
	//
	// @Override
	// public int getSumOrderGuest(int idGuest) {
	// int sum = 0;
	// // try {
	// // List<DailService> serviceList = guest.getCheck().getServiceList();
	// //
	// // for (int i = 0; i < guest.getCheck().getServiceList().size(); i++) {
	// // sum = +serviceList.get(i).getPrice();
	// // if (serviceList.get(i).getId() >= 5) { // begin additional
	// // // services
	// // AdditionalService service = (AdditionalService) serviceList
	// // .get(i);
	// // sum = +service.getAddPrice();
	// // }
	// // }
	// // return sum;
	// // } catch (Exception e) {
	// // LOGGER.error(e.getMessage());
	// // }
	// return sum;

}
