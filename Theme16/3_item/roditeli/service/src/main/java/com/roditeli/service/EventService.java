package com.roditeli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.roditeli.api.dao.IEventDao;
import com.roditeli.api.service.IEventService;
import com.roditeli.model.Event;

@Service
public class EventService extends BaseService<Event> implements IEventService{
	
	private IEventDao dao;
	
	@Required
	@Autowired
	public void setDao(IEventDao dao) {
		this.dao = dao;
	}


	public EventService() {
	}

}
