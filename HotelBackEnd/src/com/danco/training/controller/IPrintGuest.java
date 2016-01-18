package com.danco.training.controller;

import java.util.List;

import com.danco.training.entity.Guest;

public interface IPrintGuest {
	public List<Guest>  printGuestsSortedByName (List<Guest>  guestsList);
	public List<Guest>  printGuestsSortedByDateOutSettle (List<Guest>  guestsList);
	

}
