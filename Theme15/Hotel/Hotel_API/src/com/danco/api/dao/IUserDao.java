package com.danco.api.dao;

import java.util.List;

import org.hibernate.Session;

import com.danco.model.entity.User;

public interface IUserDao extends IBaseDao<User>{
	
	public List<User> getList(Session session) throws Exception;

}
