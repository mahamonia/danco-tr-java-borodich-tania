package com.roditeli.api.dao;

import org.hibernate.HibernateException;

import com.roditeli.model.Authentication;

public interface IAuthenticationDao extends IBaseDao<Authentication>{
	
	public Authentication getByLogin(String login) throws Exception;

}
