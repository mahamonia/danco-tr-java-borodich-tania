package com.danco.api.backend;

import java.util.List;

import com.danco.model.entity.Check;

public interface IControllerCheck {
	
	public void createCheck(Check check);

	public void updateCheck(int idCheck);

	public void deleteCheck(int idCheck);
	
	public Check getCheck(int idCheck);
	
	public Check getIdCheckForIdGuest(int idGuest);

	public List<Check> getListCheck();
	
	public void addDateInSettle(int idCheck, String dateInSettle);
	
	public void addDateOutSettle(int idCheck, String dateOutSettle);
	
	public void addRoomInCheck(int idCheck, int idRoom);
	
	public void changeStatusCheck(int idCheck, String status);
	
	public int getRoomInLiveGuest(int idGuest);

}
