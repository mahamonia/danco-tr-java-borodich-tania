package com.danco.api.backend;

import java.util.List;

import org.hibernate.Session;

import com.danco.model.entity.Guest;

public interface IControllerGuest {

	public void createGuest(Session session, Guest guest);

	public void updateGuest(Session session, Guest guest);

	public void deleteGuest(Session session, int idGuest);

	public Guest getGuest(Session session, int idGuest);

	public List<Guest> getListGuest(Session session, String param);

	public int getAmountGuest(Session session);

	public List<Guest> importGuestsList(String nameFile);

	public void exportGuestsList(Session session, String nameFile);

}
