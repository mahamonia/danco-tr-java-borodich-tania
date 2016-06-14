package com.roditeli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.roditeli.api.dao.IUserProfileDao;
import com.roditeli.api.service.IUserProfileService;
import com.roditeli.model.Authentication;
import com.roditeli.model.UserProfile;

@Service
public class UserProfileService extends BaseService<UserProfile>implements IUserProfileService{
	
private IUserProfileDao dao;
	
	@Required
	@Autowired
	public void setDao(IUserProfileDao dao) {
		this.dao = dao;
	}
	
	public UserProfileService() {
	}

	@Override
	public UserProfile getUserProfileByUser(int userId) {
		return dao.getUserProfileByUser(userId);
	}



}
