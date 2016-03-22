package com.danco.api.backend;

import java.sql.Connection;
import java.util.List;

import com.danco.model.entity.Check;

public interface IControllerCheck {
	
	public void createCheck(Connection connect,Check check);

	public void updateCheck(Connection connect, int idCheck);

	public void deleteCheck(Connection connect, int idCheck);
	
	public Check getCheck(Connection connect, int idCheck);
	
	public Check getIdCheckForIdGuest(Connection connect, int idGuest);

	public List<Check> getListCheck(Connection connect);
	
	public void addDateInSettle(Connection connect, int idCheck, String dateInSettle);
	
	public void addDateOutSettle(Connection connect, int idCheck, String dateOutSettle);
	
	public void addRoomInCheck(Connection connect, int idCheck, int idRoom);
	
	public void changeStatusCheck(Connection connect, int idCheck, String status);
	
	public int getRoomInLiveGuest(Connection connect, int idGuest);

}
