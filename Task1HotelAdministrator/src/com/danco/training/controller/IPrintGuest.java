package com.danco.training.controller;

import com.danco.training.entity.Guest;


public interface IPrintGuest {
	public void printGuest (Guest [] Guests);
	public void printGuestThemRoom (Guest [] Guests);
	public void printGuestThemService (Guest [] Guests);

}
