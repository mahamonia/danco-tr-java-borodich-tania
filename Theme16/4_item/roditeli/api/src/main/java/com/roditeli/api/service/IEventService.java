package com.roditeli.api.service;

import java.util.List;

import com.roditeli.model.Event;

public interface IEventService extends IBaseService<Event>{
	
	public List<Event> getEvetsListForUser(int userId);

}
