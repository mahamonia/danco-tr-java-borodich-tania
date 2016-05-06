package com.danco.api.backend;

import java.util.List;

import org.hibernate.Session;

import com.danco.model.entity.User;

public interface IControllerUser {
	
	public List<User> getListUser(Session session);

}
