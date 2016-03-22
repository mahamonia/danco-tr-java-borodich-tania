package com.danco.api.backend;

import java.sql.Connection;
import java.util.List;

import com.danco.model.entity.Guest;

public interface IControllerGuest {

	public void createGuest(Connection con, Guest guest);

	public void updateGuest(Connection con, int idGuest);

	public void deleteGuest(Connection con, int idGuest);

	public Guest getGuest(Connection con, int idGuest);

	public List<Guest> getListGuest(Connection con);

	public List<Guest> getListGuestSortedByName(Connection con);

	public List<Guest> getListGuestSortedByDateOutSettle(Connection con);

	public int getAmountGuest(Connection con);

	public List<Guest> importGuestsList(Connection con);

	public void exportGuestsList(Connection con);

}
