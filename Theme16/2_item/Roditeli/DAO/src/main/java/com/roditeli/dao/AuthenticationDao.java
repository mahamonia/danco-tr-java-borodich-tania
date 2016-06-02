package com.roditeli.dao;

import org.hibernate.Session;

import com.roditeli.api.dao.IAuthenticationDao;
import com.roditeli.model.Authentication;

public class AuthenticationDao implements IAuthenticationDao{

	@Override
	public Authentication getById(Session session, int idModel)
			throws Exception {
		return (Authentication)session.get(Authentication.class, idModel);
	}

}
