package com.roditeli.api.service;

import com.roditeli.model.Authentication;

public interface IAuthenticationService extends IBaseService<Authentication>{
	
	public Authentication getByLogin(String login);

}
