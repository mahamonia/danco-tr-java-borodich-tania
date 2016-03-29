package com.danco.api.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.danco.model.entity.Guest;

public interface IGuestDao extends IBaseDao<Guest>{

	public List<Guest> getListGuestSorted(Connection connect, String param) throws SQLException;

}
