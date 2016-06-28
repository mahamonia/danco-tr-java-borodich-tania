package com.roditeli.api.dao;

import java.util.List;

import com.roditeli.model.Event;


public interface IEventDao extends IBaseDao<Event>{
	
	public List<Event> getEvetsListForUser(int userId) throws Exception;
	

}
