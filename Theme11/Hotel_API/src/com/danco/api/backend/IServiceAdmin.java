package com.danco.api.backend;

import java.util.List;

import com.danco.model.entity.Check;
import com.danco.model.entity.Guest;
import com.danco.model.entity.Room;
import com.danco.model.entity.Service;

public interface IServiceAdmin {

	public void initData();

	public void saveData();

	// G U E S T

	public void createGuest(Guest guest);

	public Guest getGuestById(int id);

	public List<Guest> getListGuest();

	public void deleteGuest(int id);

	public void updateGuest(int idGuest);

	public void settleGuestInRoom(int idGuest, int idRoom, String dateInSettle,
			String dateOutSettle);

	public void addServiceForGuest(int idGuest, int idService);

	public void settleGuestOutRoom(int idGuest);

	public Room getRoomInLiveGuest(int idGuest);

	public List<Guest> printGuestsSortedByName();

	public List<Guest> printGuestsSortedByDateOutSettle();

	public int getAmountGuest();

	public void importGuestsList();

	public void exportGuestsList();

	// R O O M

	public void createRoom(Room room);

	public List<Room> getListRoom();

	public void updateRoom(int idRoom);

	public List<Room> getListRoomSortedByContent();

	public List<Room> getListRoomSortedByNumber();

	public List<Room> getListRoomSortedByPrice();

	public List<Room> getListRoomSortedByStars();

	public List<Room> getListRoomFree();

	public List<Room> getListRoomFreeSortedByContent();

	public List<Room> getListRoomFreeSortedByNumber();

	public List<Room> getListRoomFreeSortedByPrice();

	public List<Room> getListRoomFreeSortedByStars();

	public int getAmountRoomFree();

	public void changeRoomStatus(int idRoom, String status);

	public void changeRoomPrice(int idRoom, int price);

	public void cloneRoom(int idRoom);

	public void importRoomsList();

	public void exportRoomsList();

	// S E R V I C E

	public void createService(Service service);

	public void updateService(int idService);

	public Service getServiceById(int idService);

	public List<Service> getListService();

	public List<Service> printServicesSortedByName();

	public List<Service> printServicesSortedByPrice();

	public List<Service> getGuestThemServices(int idGuest);

	public void changeServicePrice(int idService, int price);

	public void importServicesList();

	public void exportServicesList();
	
	// C H E C K
	
	public void createCheck(Check check);

	public void updateCheck(int idCheck);

	public Check getCheckById(int idCheck);

	public List<Check> getListCheck();
	
	public int getSumCheck(int idGuest);

}
