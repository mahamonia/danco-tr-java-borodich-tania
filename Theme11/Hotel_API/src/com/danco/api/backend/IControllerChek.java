package com.danco.api.backend;

import java.sql.Connection;
import java.util.List;

import com.danco.model.entity.Chek;

public interface IControllerChek {
	
	public void createChek(Connection connect,Chek chek);

	public void updateChek(Connection connect, Chek chek);

	public void deleteChek(Connection connect, int idChek);
	
	public Chek getChek(Connection connect, int idChek);
	
	public Chek getChekForIdGuest(Connection connect, int idGuest);

	public List<Chek> getListChek(Connection connect);
	
	public List<Chek> getListChekSortedBy(Connection connect, String param);
	
	public void changeStatusChek(Connection connect, int idChek, String status);
	
	public int getRoomInLiveGuest(Connection connect, int idGuest);

}
