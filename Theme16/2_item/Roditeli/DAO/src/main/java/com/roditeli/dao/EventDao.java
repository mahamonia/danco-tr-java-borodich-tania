package com.roditeli.dao;

import org.hibernate.Session;

import com.roditeli.api.dao.IEventDao;
import com.roditeli.model.Event;

public class EventDao implements IEventDao{

	@Override
	public Event getById(Session session, int idModel) throws Exception {
		return (Event)session.get(Event.class, idModel);
	}

}
