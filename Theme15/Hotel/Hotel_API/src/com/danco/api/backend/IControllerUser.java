package com.danco.api.backend;

import org.hibernate.Session;

import com.danco.model.entity.User;

public interface IControllerUser {
	
	public User getUserByLogin(Session session, String login);

}
