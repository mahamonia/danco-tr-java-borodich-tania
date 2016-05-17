package com.danco.api.backend;

import java.util.List;

import org.hibernate.Session;

import com.danco.model.entity.Chek;

public interface IControllerChek {
	
	public void createChek(Session session,Chek chek);

	public void updateChek(Session session, Chek chek);

	public void deleteChek(Session session, int idChek);
	
	public Chek getChek(Session session, int idChek);
	
	public List<Chek> getChekListForIdGuest(Session session, int idGuest);

	public List<Chek> getListChek(Session session, String param);
	
	public void changeStatusChek(Session session, int idChek, String status);
	
	public int getRoomInLiveGuest(Session session, int idGuest);

}
