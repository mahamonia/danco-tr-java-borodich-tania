package com.danco.api.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import com.danco.model.entity.Chek;

public interface IChekDao extends IBaseDao<Chek>{

	public Chek getChekForIdGuest(Session session, int idGuest)throws SQLException ;
	
	public List<Chek> getListChekSorted(Session session, String param)throws SQLException ;
}
