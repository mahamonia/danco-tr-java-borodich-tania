package com.roditeli.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IEventDao;
import com.roditeli.model.Event;

@Repository
public class EventDao extends BaseDao<Event> implements IEventDao{

	@Override
	@Transactional
	public Event getById(int idModel) {
		return (Event)sessionFactory.getCurrentSession().get(Event.class, idModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Event> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Event.class);
		return criteria.list();
	}



}
