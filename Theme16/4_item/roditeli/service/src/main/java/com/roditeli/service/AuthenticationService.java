package com.roditeli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IAuthenticationDao;
import com.roditeli.api.service.IAuthenticationService;
import com.roditeli.model.Authentication;

@Service
public class AuthenticationService extends BaseService<Authentication>
		implements IAuthenticationService {
	@Autowired
	private IAuthenticationDao dao;

	public AuthenticationService() {
	}

	@Override
	@Transactional
	public Authentication getByLogin(String login) {
		Authentication auth = null;
		try {
			auth = dao.getByLogin(login);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return auth;
	}

}
