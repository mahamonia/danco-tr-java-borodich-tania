package com.danco.training.controller;


import com.danco.training.entity.*;

public class ControllerGuest implements IPrintGuest{
	
	private Guest [] Guests;
	private final int MAX_GUESTS;
	
	public ControllerGuest(Guest [] Guests){
		MAX_GUESTS = Guests.length;
		this.Guests = Guests;
		
	}
		
	public void createGuests(Guest Guest) {

		int i = getNumberForNewGuest();
		Guests[i] = Guest;

	}
	
	private int getNumberForNewGuest() {

		for (int i = 0; i < Guests.length; i++) {
			if (Guests[i] == null) {
				return i;
			}
		}return -1;
	}
	
	public void deleteGuest(Guest Guest) {
		int i = getNumberGuest(Guest);
		if (i != -1) {
			Guests[i] = null;
		}
	}
	
	private int getNumberGuest(Guest Guest) {

		int NumberGuest = getNumberGuestById(Guest.getId());
		return NumberGuest;
	}

	private int getNumberGuestById(int Id) {
		for (int i = 0; i < this.Guests.length; i++) {
			if (Guests[i] != null && Guests[i].getId() == Id) {
				return i;
			}
		}return -1;

	}
	public void addRoomForGuest(Guest Guest, Room Room) {
		Room.setIdGuest(Guest.getId());
		
	}
	
	@Override
	public void printGuest(Guest[] Guests) {
		for (int i = 0; i < Guests.length; i++) {
			System.out.println(Guests[i].getName());
			
		}		
	}
	public int amountGuest(Guest[] Guests) {
		int amountGuest = 0;
		for (int i = 0; i < Guests.length; i++) {
			amountGuest++;;		
		}
		return amountGuest;		
	}
	
	
	public void printGuestThemRoom (Guest [] Guests, Room [] Rooms){
		for (int i = 0; i < Guests.length; i++) {
			for (int j = 0; j < Rooms.length; j++) {
				if (Guests[i].getId()==Rooms[j].getIdGuest()){			
					System.out.println(Guests[i].getName()+" live in "+Rooms[i].getNumber()+" room");}
			}	
		}		
	}
	
	@Override
	public void printGuestThemRoom(Guest[] Guests) {
		for (int i = 0; i < Guests.length; i++) {
			System.out.println(Guests[i].getName()+" in room");
			
		}
	}
	@Override
	public void printGuestThemService(Guest[] Guests) {
		for (int i = 0; i < Guests.length; i++) {		
		
		System.out.println( Guests[i].getName()+"service");
		}
	}

	public int getMAX_GUESTS() {
		return MAX_GUESTS;
	}

}
