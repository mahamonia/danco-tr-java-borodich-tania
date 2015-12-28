package com.danco.training.services;


import com.danco.training.controller.ControllerGuest;
import com.danco.training.controller.ControllerRoom;
import com.danco.training.controller.ControllerService;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Hotel;
import com.danco.training.entity.Room;
import com.danco.training.entity.Service;
import com.danco.training.entity.Status;

public class ServiceAdmin {
	
	private ControllerRoom contRoom;
	private ControllerGuest contGuest;
	private ControllerService contService;
	
	public ServiceAdmin (ControllerRoom contRoom, ControllerGuest contGuest, ControllerService contService){
		this.contRoom = contRoom;
		this.contGuest = contGuest;
		this.contService = contService;
		
	}
	
	public void inSettleGuestRoom(Guest Guest, Room Room) {
		contGuest.addRoomForGuest(Guest, Room);
		contRoom.changeRoomStatus(Room, Status.NOTFREE);
		System.out.println("settle guest in room");
	}
	
	public void outSettleGuestRoom(Guest Guest, Room Room) {
		//1. guest pay the order
		//2. change Room Status 
		contRoom.changeRoomStatus(Room, Status.FREE);
		System.out.println("settle guest out room");
	}
	
	public void changeRoomStatus (Room Room, Status Status){
		contRoom.changeRoomStatus(Room, Status);
		System.out.println("change Room Status");
	}
	
	public void changeRoomPrice(Room Room, int Price) {	
		System.out.println("change Room Price");
		
	}
	
	public void changeServicePrice(Service Service, int Price) {
		System.out.println("change Service Price");
	}
	
	public void printGuestThemRoom(Guest [] Guests, Room [] Rooms){
		contGuest.printGuestThemRoom(Guests, Rooms);
	}
	
	public void printRoom(Room[] Rooms) {
		System.out.println("List rooms: ");
		contRoom.printRoom(Rooms);

	}
		
	public void printFreeRoom(Room [] Rooms){
		System.out.println("Free room: "+contRoom.printRoomFree(Rooms));
	}

}
