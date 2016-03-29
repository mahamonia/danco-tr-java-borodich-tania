package com.danco.api.backend;

import java.sql.Connection;
import java.util.List;

import com.danco.model.entity.Guest;

public interface IControllerGuest {

	public void createGuest(Connection con, Guest guest);

	public void updateGuest(Connection con, Guest guest);

	public void deleteGuest(Connection con, int idGuest);

	public Guest getGuest(Connection con, int idGuest);

	public List<Guest> getListGuest(Connection con);

	public List<Guest> getListGuestSorted(Connection connect, String param);

	public int getAmountGuest(Connection con);

	public List<Guest> importGuestsList();

	public void exportGuestsList(Connection con);

}
