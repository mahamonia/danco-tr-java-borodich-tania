package com.roditeli.dao;

import org.hibernate.Session;

import com.roditeli.api.dao.ICommentEventDao;
import com.roditeli.model.CommentEvent;

public class CommentEventDao implements ICommentEventDao{

	@Override
	public CommentEvent getById(Session session, int idModel) throws Exception {
		return (CommentEvent)session.get(CommentEvent.class, idModel);
	}

}
