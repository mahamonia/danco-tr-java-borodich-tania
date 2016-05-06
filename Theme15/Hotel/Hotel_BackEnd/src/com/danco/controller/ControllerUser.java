package com.danco.controller;

import java.util.List;

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

	@Override
	public List<User> getListUser(Session session) {
		List<User> userList = null;
		try {
			userList =  userDao.getList(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return userList;
	}

}
