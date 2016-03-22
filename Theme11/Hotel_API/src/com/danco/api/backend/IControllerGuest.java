package com.danco.api.backend;

import java.util.List;

import com.danco.model.entity.Guest;

public interface IControllerGuest {

	public void createGuest(Guest guest);

	public void updateGuest(int idGuest);

	public void deleteGuest(int idGuest);

	public Guest getGuest(int idGuest);

	public List<Guest> getListGuest();

	public List<Guest> getListGuestSortedByName();

	public List<Guest> getListGuestSortedByDateOutSettle();

	public int getAmountGuest();

	public List<Guest> importGuestsList();

	public void exportGuestsList();

}
