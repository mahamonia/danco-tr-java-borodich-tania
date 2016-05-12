package com.danco.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.danco.api.dao.IRoomDao;
import com.danco.model.entity.Room;
import com.danco.model.entity.Status;

public class RoomDao implements IRoomDao {

	public RoomDao() {
	}

	@Override
	public Room getById(Session session, int idModel) throws Exception {
		return (Room) session.get(Room.class, idModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getList(Session session, String status, String param)
			throws Exception {
		Criteria crit = session.createCriteria(Room.class);
		List<Room> roomList = null;
		if (status.isEmpty()) {
			roomList = (List<Room>) crit.addOrder(Order.asc(param)).list();
		} else {
			roomList = (List<Room>) crit.add(Restrictions.eq("status", Status.valueOf(status)))
					.addOrder(Order.asc(param)).list();
		}

		return roomList;
	}
}
