package com.roditeli.dao;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.roditeli.api.dao.IEventDao;
import com.roditeli.model.Event;

@Repository
public class EventDao extends BaseDao<Event> implements IEventDao {

	private final static String SORT_PARAMETR_DATE = "date";
	private final static String RESTRICTIVE_PARAMETR_RECIPIENT = "recipient";

	public EventDao() {
		super(Event.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEvetsListForUser(int userId) throws Exception {
		List<Event> list = null;
		list = getCriteria()
				.add(Restrictions.eq(RESTRICTIVE_PARAMETR_RECIPIENT, userId))
				.addOrder(Order.asc(SORT_PARAMETR_DATE)).list();

		return list;
	}

}
