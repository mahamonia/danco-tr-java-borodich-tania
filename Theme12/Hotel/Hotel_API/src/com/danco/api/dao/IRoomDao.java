package com.danco.api.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import com.danco.model.entity.Room;

public interface IRoomDao extends IBaseDao<Room> {
	
	public List<Room> getListRoomSorted(Session session, String status, String param)throws SQLException;

}
