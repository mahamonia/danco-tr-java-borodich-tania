package com.danco.api.dao;

import java.sql.Connection;
import java.util.List;

import com.danco.model.entity.Guest;

public interface IGuestDao extends BaseDao<Guest>{

	public List<Guest> getListGuestSortedByName(Connection connect) ;

}
