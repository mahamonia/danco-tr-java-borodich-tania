package com.danco.api.backend;

import java.util.List;

import com.danco.model.entity.Chek;
import com.danco.model.entity.Guest;
import com.danco.model.entity.Room;
import com.danco.model.entity.Service;

public interface IServiceAdmin {

	public void initData();

	// G U E S T

	public String createGuest(Guest guest);

	public Guest getGuestById(int id);

	public List<Guest> getListGuest();

	public String settleGuestInRoom(int idGuest, int idRoom, String dateInSettle,
			String dateOutSettle);

	public String addServiceForGuest(int idGuest, int idService);

	public String settleGuestOutRoom(int idGuest);

	public Room getRoomInLiveGuest(int idGuest);

	public List<Guest> printGuestsSortedByName();

	public int getAmountGuest();

	public String importGuestsList();

	public String exportGuestsList();

	// R O O M

	public String createRoom(Room room);

	public List<Room> getListRoom();

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

	public String changeRoomStatus(int idRoom, String status);

	public String changeRoomPrice(int idRoom, int price);

	public String cloneRoom(int idRoom);

	public String importRoomsList();

	public String exportRoomsList();

	// S E R V I C E

	public String createService(Service service);

	public Service getServiceById(int idService);

	public List<Service> getListService();

	public List<Service> printServicesSortedByName();

	public List<Service> printServicesSortedByPrice();

	public List<Service> getGuestThemServices(int idGuest);

	public String changeServicePrice(int idService, int price);

	public String importServicesList();

	public String exportServicesList();
	
	// C H E C K
	
	public String createChek(Chek chek);

	public Chek getChekById(int idChek);

	public List<Chek> getListChek();
	
	public List<Chek> getListChekSortedByDateOutSettle();
	
	public int getSumChek(int idGuest);

}
