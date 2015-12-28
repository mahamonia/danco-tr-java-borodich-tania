package com.danco.training.controller;

import com.danco.training.comparators.TypeSort;
import com.danco.training.entity.Guest;
import com.danco.training.entity.Room;


public interface IPrintGuest {
	public void printGuests (Guest [] Guests, TypeSort type);
	public void printGuestsThemRoom (Guest[] guestsList, Room[] roomsList, TypeSort type);
	

}
