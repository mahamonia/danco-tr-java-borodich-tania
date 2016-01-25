package com.danco.training.services;

import java.util.List;

import com.danco.training.entity.AdditionalService;
import com.danco.training.entity.DailService;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Order;
import com.danco.training.entity.Room;
import com.danco.training.entity.Service;
import com.danco.training.entity.Status;

public interface IServiceAdmin {
	
	public void createGuest(Guest guest);
	public Guest getGuestById(int id) ;
	public List<Guest> getListGuest() ;
	public void deleteGuest(int id);
	public void updateGuest(Guest guest) ;
	public Order getOrderById(int id);
	public void settleGuestInRoom(Guest guest, Room room, String dateInSettle,
			String dateOutSettle) ;
	public void addServiceForGuest(Guest guest, Service service);
	public void settleGuestOutRoom(Guest guest) ;
	public Room getRoomInLiveGuest(Guest guest);
	public int getSumOrderGuest(Guest guest);
	public List<Guest> printListGuestRoom(Room room) ;
	public List<Guest> printGuestsSortedByName();
	public List<Guest> printGuestsSortedByDateOutSettle();
	public int getAmountGuest() ;
	public void importGuestsList();
	public void createRoom(Room room) ;
	public Room getRoomByNumber(int number);
	public List<Room> getListRoom() ;
	public List<Guest> getListGuestRoom(Room room);
	public void updateRoom(Room room) ;
	public List<Room> printSortedRoomsByContent();
	public List<Room> printSortedRoomsByNumber();
	public List<Room> printSortedRoomsByPrice();
	public List<Room> printSortedRoomByStars();
	public List<Room> printRoomFreeSortetdByContent();
	public List<Room> printRoomFreeSortetdByPrice();
	public List<Room> printRoomFreeSortetdByNumber();
	public List<Room> printRoomFreeSortetdByStars();
	public int getAmountFreeRoom();
	public void changeRoomStatus(Room room, Status status);
	public void changeRoomPrice(Room room, int price);
	public void cloneRoom(Room room);
	public void createService(Service service);
	public void createAdditionalService(AdditionalService service);
	public Service getServiceById(int id);
	public List<DailService> getListDailService();
	public List<AdditionalService> getListAdditionalService();
	public void updateService(Service service);
	public List<DailService> printDailServicesSortedByName();
	public List<AdditionalService> printAdditionalServicesSortedByName();
	public List<DailService> printDailServicesSortedByPrice();
	public List<AdditionalService> printAdditionalServicesSortedByPrice();
	public void changeServicePrice(Service service, int price);
	

}
