package com.roditeli.dao;

import org.hibernate.Session;

import com.roditeli.api.dao.IChildrenDao;
import com.roditeli.model.Children;

public class ChildrenDao implements IChildrenDao{

	@Override
	public Children getById(Session session, int idModel) throws Exception {
		return (Children)session.get(Children.class, idModel);
	}

}
