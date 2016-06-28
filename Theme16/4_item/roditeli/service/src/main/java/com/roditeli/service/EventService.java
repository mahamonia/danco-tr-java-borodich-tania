package com.roditeli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IEventDao;
import com.roditeli.api.service.IEventService;
import com.roditeli.model.Event;

@Service
public class EventService extends BaseService<Event> implements IEventService {
	@Autowired
	private IEventDao dao;

	public EventService() {
	}

	@Override
	@Transactional
	public List<Event> getEvetsListForUser(int userId) {
		List<Event> list = null;

		try {
			list = dao.getEvetsListForUser(userId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}

}
