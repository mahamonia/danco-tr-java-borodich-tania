package com.danco.api.backend;

import java.util.List;

import com.danco.training.entity.Guest;
import com.danco.training.entity.Check;
import com.danco.training.entity.Room;
import com.danco.training.entity.DailService;

public interface IControllerGuest {

	public void createGuest(Guest guest);

	public void updateGuest(Guest guest);

	public void deleteGuest(Guest guest);

	public int getIdForNewGuest();

	public Guest getGuestById(int id);

	public List<Guest> getListGuest();

	public void setListGuest(List<Guest> guestsList);

	public List<Check> getListCheck();

	public int getIdForNewCheck();

	public Check getCheckById(int id);

	public List<Guest> printGuestsSortedByName(List<Guest> guestsList);

	public List<Guest> printGuestsSortedByDateOutSettle(List<Guest> guestsList);

	public void addRoomForGuest(Guest guest, Room room);

	public void addServiceForGuest(Guest guest, DailService service);

	public void addDateInSettle(Guest guest, String dateInSettle);

	public void addDateOutSettle(Guest guest, String dateOutSettle);

	public Room getRoomInLiveGuest(Guest guest, List<Room> roomsList);

	public List<DailService> getGuestThemServices(Guest guest);

	public int getSumOrderGuest(Guest guest);

	public int getAmountGuest();

	public List<Guest> importGuestsList();

	public void exportGuestsList(List<Guest> guestsList);

}
