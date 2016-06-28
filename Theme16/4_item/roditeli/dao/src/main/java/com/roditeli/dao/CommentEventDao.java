package com.roditeli.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.ICommentEventDao;
import com.roditeli.model.CommentEvent;

@Repository
public class CommentEventDao extends BaseDao<CommentEvent> implements ICommentEventDao{

	public CommentEventDao() {
		super(CommentEvent.class);
	}



}
