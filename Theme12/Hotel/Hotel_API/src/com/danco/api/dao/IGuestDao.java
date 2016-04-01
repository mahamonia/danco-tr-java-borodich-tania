package com.danco.api.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import com.danco.model.entity.Guest;

public interface IGuestDao extends IBaseDao<Guest>{

	public List<Guest> getListGuestSorted(Session session, String param) throws SQLException;

}
