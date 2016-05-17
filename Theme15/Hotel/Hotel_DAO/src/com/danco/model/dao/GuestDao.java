package com.danco.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.danco.api.dao.IGuestDao;
import com.danco.model.entity.Guest;

public class GuestDao implements IGuestDao {
	
	public GuestDao() {
	}

	@Override
	public Guest getById(Session session, int idModel) throws Exception {
		return (Guest) session.get(Guest.class, idModel);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Guest> getList(Session session, String param) throws Exception {
		Criteria crit = session.createCriteria(Guest.class);
		List<Guest> guestList = (List<Guest>) crit.addOrder(Order.asc(param)).list();
		return guestList;
	}

}
