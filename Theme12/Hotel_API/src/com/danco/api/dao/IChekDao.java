package com.danco.api.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.danco.model.entity.Chek;

public interface IChekDao extends IBaseDao<Chek>{

	public Chek getChekForIdGuest(Connection connect, int idGuest)throws SQLException ;
	
	public List<Chek> getListChekSorted(Connection connect, String param)throws SQLException ;
}
