package com.roditeli.dao;

import org.hibernate.Session;

import com.roditeli.api.dao.IUserProfileDao;
import com.roditeli.model.UserProfile;

public class UserProfileDao implements IUserProfileDao{

	@Override
	public UserProfile getById(Session session, int idModel) throws Exception {
		return (UserProfile)session.get(UserProfile.class, idModel);
	}

}
