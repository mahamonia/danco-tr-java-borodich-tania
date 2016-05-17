package com.danco.api.backend;

import java.util.List;

import com.danco.model.entity.Audit;
import com.danco.model.entity.Chek;
import com.danco.model.entity.Guest;
import com.danco.model.entity.Room;
import com.danco.model.entity.Service;
import com.danco.model.entity.User;

public interface IServiceAdmin {

	public void initData();
	
	public void saveData();

	// G U E S T

	public Boolean createGuest(Guest guest);

	public Guest getGuestById(int id);

	public List<Guest> getListGuest();

	public Boolean settleGuestInRoom(int idGuest, int idRoom, String dateInSettle,
			String dateOutSettle);

	public Boolean addServiceForGuest(int idGuest, int idService);

	public Boolean settleGuestOutRoom(int idGuest);

	public Room getRoomInLiveGuest(int idGuest);

	public List<Guest> printGuestsSortedByName();

	public int getAmountGuest();

	public Boolean importGuestsList(String nameFile);

	public Boolean exportGuestsList(String nameFile);

	// R O O M

	public Boolean createRoom(Room room);

	public List<Room> getListRoom(String status, String param);

	public int getAmountRoomFree();

	public Boolean changeRoomStatus(int idRoom, String status);

	public Boolean changeRoomPrice(int idRoom, int price);

	public Boolean cloneRoom(int idRoom);

	public Boolean importRoomsList(String nameFile);

	public Boolean exportRoomsList(String nameFile);

	// S E R V I C E

	public Boolean createService(Service service);

	public Service getServiceById(int idService);

	public List<Service> getListService(String param);

	public List<Service> getGuestThemServices(int idGuest);

	public Boolean changeServicePrice(int idService, int price);

	public Boolean importServicesList(String nameFile);

	public Boolean exportServicesList(String nameFile);
	
	// C H E C K
	
	public Boolean createChek(Chek chek);

	public Chek getChekById(int idChek);

	public List<Chek> getListChek();
	
	public List<Chek> getChekListForIdGuest(int idGuest);
	
	public List<Chek> getListChekSortedByDateOutSettle();
	
	public int getSumChek(int idGuest);
	
	// U S E R
	
	public User getUserByLogin(String login);
	
	// A U D I T
	
	public void save(Audit audit);

}
