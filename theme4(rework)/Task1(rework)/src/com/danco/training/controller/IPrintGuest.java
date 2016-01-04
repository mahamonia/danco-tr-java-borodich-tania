package com.danco.training.controller;

import com.danco.training.entity.Guest;

public interface IPrintGuest {
	public Guest [] printGuestsSortedByName (Guest [] guestsList);
	public Guest [] printGuestsSortedByDateOutSettle (Guest[] guestsList);
	

}
