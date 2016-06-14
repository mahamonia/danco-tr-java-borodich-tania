package com.roditeli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.roditeli.api.dao.ICommentEventDao;
import com.roditeli.api.service.ICommentEventService;
import com.roditeli.model.CommentEvent;

@Service
public class CommentEventService extends BaseService<CommentEvent> implements ICommentEventService{
	
	private ICommentEventDao dao;
	
	@Required
	@Autowired
	public void setDao(ICommentEventDao dao) {
		this.dao = dao;
	}

	public CommentEventService() {
	}

}
