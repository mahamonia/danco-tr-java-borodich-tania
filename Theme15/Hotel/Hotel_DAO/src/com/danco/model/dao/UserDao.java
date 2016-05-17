package com.danco.model.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.danco.api.dao.IUserDao;
import com.danco.model.entity.User;

public class UserDao implements IUserDao{

	public UserDao() {
	}
	
	@Override
	public User getById(Session session, int idModel) throws Exception {
		User user = (User)session.get(User.class, idModel);
		return user;
	}

	@Override
	public User getUserByLogin(Session session, String login) throws Exception {
		Criteria crit = session.createCriteria(User.class);
		User user = (User)crit.add(Restrictions.eq("login", login)).list().get(0);
		return user;
	}

}
