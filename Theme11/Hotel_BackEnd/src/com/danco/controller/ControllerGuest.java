package com.danco.controller;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerGuest;
import com.danco.api.backend.IParseUtilityCSVForGuest;
import com.danco.model.dao.GuestDao;
import com.danco.model.entity.*;

public class ControllerGuest implements IControllerGuest {

	private static final Logger LOGGER = LogManager
			.getLogger(ControllerGuest.class);

	@Injection
	private IParseUtilityCSVForGuest utility;

	private GuestDao guestDao;

	public ControllerGuest(GuestDao guestDao) {
		this.guestDao = guestDao;

	}

	@Override
	public void createGuest(Connection connect, Guest guest) {
		try {
			guestDao.create(connect, guest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void updateGuest(Connection connect, int idGuest) {
		try {
			guestDao.update(connect, idGuest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

	}

	@Override
	public void deleteGuest(Connection connect, int idGuest) {
		try {
			guestDao.delete(connect, idGuest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public Guest getGuest(Connection connect, int idGuest) {
		return guestDao.getById(connect, idGuest);
	}

	@Override
	public List<Guest> getListGuest(Connection connect) {
		return guestDao.getList(connect);
	}

	@Override
	public List<Guest> getListGuestSortedByName(Connection connect) {
		return guestDao.getListGuestSortedByName(connect);
	}

	@Override
	public List<Guest> getListGuestSortedByDateOutSettle(Connection connect) {
		return guestDao.getListGuestSortedByDateOutSettle(connect);
	}

	@Override
	public int getAmountGuest(Connection connect) {
		return guestDao.getList(connect).size();
	}

	@Override
	public List<Guest> importGuestsList(Connection connect) {
		return utility.importData();
	}

	@Override
	public void exportGuestsList(Connection connect) {
		utility.exportData(guestDao.getList(connect));

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
