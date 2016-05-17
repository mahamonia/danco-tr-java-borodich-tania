package com.danco.api.dao;

import org.hibernate.Session;

import com.danco.model.entity.User;

public interface IUserDao extends IBaseDao<User>{
	
	public User getUserByLogin(Session session, String login) throws Exception;
	

}
