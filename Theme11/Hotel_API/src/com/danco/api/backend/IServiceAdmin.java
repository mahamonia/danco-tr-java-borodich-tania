package com.danco.api.backend;

import java.util.List;

import com.danco.training.entity.AdditionalService;
import com.danco.training.entity.DailService;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Check;
import com.danco.training.entity.Room;
import com.danco.training.entity.Status;

public interface IServiceAdmin {

	public void initData();

	public void saveData();

	// guest
	public void createGuest(Guest guest);

	public Guest getGuestById(int id);

	public List<Guest> getListGuest();

	public void deleteGuest(int id);

	public void updateGuest(int idGuest);

	public Check getCheckById(int id);

	public void settleGuestInRoom(int idGuest, int idRoom, String dateInSettle,
			String dateOutSettle);

	public void addServiceForGuest(int idGuest, int idService);

	public void settleGuestOutRoom(int idGuest);

	public Room getRoomInLiveGuest(int idGuest);

	public int getSumOrderGuest(int idGuest);

	public List<Guest> printListGuestRoom(int idRoom);

	public List<Guest> printGuestsSortedByName();

	public List<Guest> printGuestsSortedByDateOutSettle();

	public int getAmountGuest();

	public void importGuestsList();

	public void exportGuestsList();

	// room
	public void createRoom(Room room);

	public String [] getListRoom();

	public String[] getListThreeLastGuestsOfRoom(int idRoom);

	public void updateRoom(int idRoom);

	public List<Room> printSortedRoomsByContent();

	public List<Room> printSortedRoomsByNumber();

	public List<Room> printSortedRoomsByPrice();

	public List<Room> printSortedRoomByStars();

	public List<Room> printRoomFreeSortetdByContent();

	public List<Room> printRoomFreeSortetdByPrice();

	public List<Room> printRoomFreeSortetdByNumber();

	public List<Room> printRoomFreeSortetdByStars();

	public int getAmountFreeRoom();

	public void changeRoomStatus(int idRoom, String status);

	public void changeRoomPrice(int idRoom, int price);

	public void cloneRoom(int idRoom);

	public void importRoomsList();

	public void exportRoomsList();

	// service
	public void createService(DailService service);

	public void createAdditionalService(AdditionalService service);

	public DailService getServiceById(int id);

	public List<DailService> getListDailService();

	public List<AdditionalService> getListAdditionalService();

	public List<DailService> printDailServicesSortedByName();

	public List<AdditionalService> printAdditionalServicesSortedByName();

	public List<DailService> printDailServicesSortedByPrice();

	public List<AdditionalService> printAdditionalServicesSortedByPrice();

	public void changeServicePrice(int idService, int price);

	public void importDailServicesList();

	public void exportDailServicesList();

	public void importAdditionalServicesList();

	public void exportAdditionalServicesList();

}
