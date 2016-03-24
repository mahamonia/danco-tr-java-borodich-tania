package com.danco.api.dao;

import java.sql.Connection;
import java.util.List;

import com.danco.model.entity.Room;

public interface IRoomDao extends BaseDao<Room> {

	public List<Room> getListRoomSortedByContetn(Connection connect);

	public List<Room> getListRoomSortedByNumber(Connection connect);

	public List<Room> getListRoomSortedByPrice(Connection connect);

	public List<Room> getListRoomSortedByStars(Connection connect);

	public List<Room> getListFreeRoom(Connection connect);

	public List<Room> getListRoomFreeSortedByContetn(Connection connect);

	public List<Room> getListRoomFreeSortedByNumber(Connection connect);

	public List<Room> getListRoomFreeSortedByPrice(Connection connect);

	public List<Room> getListRoomFreeSortedByStars(Connection connect);

}
