package com.danco.api.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.danco.model.entity.Room;

public interface IRoomDao extends IBaseDao<Room> {
	
	public List<Room> getListRoomSorted(Connection connect, String status, String param)throws SQLException;

}
