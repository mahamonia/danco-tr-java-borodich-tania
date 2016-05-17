package com.danco.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerUser;
import com.danco.api.dao.IUserDao;
import com.danco.model.entity.User;

public class ControllerUser implements IControllerUser{
	private static final Logger LOGGER = LogManager
			.getLogger(ControllerUser.class);
	
	@Injection
	private IUserDao userDao;
	
	public ControllerUser() {
	}

	@Override
	public User getUserByLogin(Session session, String login) {
		User user = null;
		try {
			user =  userDao.getUserByLogin(session, login);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return user;
	}

}
