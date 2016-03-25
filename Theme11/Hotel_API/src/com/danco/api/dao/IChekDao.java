package com.danco.api.dao;

import java.sql.Connection;
import java.util.List;

import com.danco.model.entity.Chek;

public interface IChekDao extends IBaseDao<Chek>{

	public Chek getChekForIdGuest(Connection connect, int idGuest);
	
	public List<Chek> getListChekSortedByDateOutSettle(Connection connect);
}
