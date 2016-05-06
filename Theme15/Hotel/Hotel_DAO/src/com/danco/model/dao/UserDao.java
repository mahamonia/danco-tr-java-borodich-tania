package com.danco.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getList(Session session) throws Exception {
		Criteria crit = session.createCriteria(User.class);
		List<User> userList = crit.addOrder(Order.asc("id")).list();
		return userList;
	}

}
