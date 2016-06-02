package com.roditeli.dao;

import org.hibernate.Session;

import com.roditeli.api.dao.IUserDao;
import com.roditeli.model.User;

public class UserDao implements IUserDao{

	@Override
	public User getById(Session session, int idModel) throws Exception {

		return (User)session.get(User.class, idModel);
	}

}
