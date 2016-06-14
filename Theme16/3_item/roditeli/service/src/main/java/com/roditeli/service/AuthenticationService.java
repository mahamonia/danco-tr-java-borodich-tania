package com.roditeli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.roditeli.api.dao.IAuthenticationDao;
import com.roditeli.api.service.IAuthenticationService;
import com.roditeli.model.Authentication;

@Service
public class AuthenticationService extends BaseService<Authentication> implements IAuthenticationService{
	
	private IAuthenticationDao dao;

	@Required
	@Autowired
	public void setDao(IAuthenticationDao dao) {
		this.dao = dao;
	}
	
	public AuthenticationService() {
	}

	@Override
	public Authentication getByLogin(String login) {
		return dao.getByLogin(login);
	}

	
}
