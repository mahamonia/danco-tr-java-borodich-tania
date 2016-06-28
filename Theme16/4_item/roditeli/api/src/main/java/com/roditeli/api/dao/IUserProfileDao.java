package com.roditeli.api.dao;

import org.hibernate.HibernateException;

import com.roditeli.model.UserProfile;


public interface IUserProfileDao extends IBaseDao<UserProfile>{
	
	public UserProfile getUserProfileByUser(int userId) throws Exception;

}
