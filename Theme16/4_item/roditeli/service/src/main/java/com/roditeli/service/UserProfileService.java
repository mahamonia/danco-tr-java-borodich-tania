package com.roditeli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IUserProfileDao;
import com.roditeli.api.service.IUserProfileService;
import com.roditeli.model.UserProfile;

@Service
public class UserProfileService extends BaseService<UserProfile> implements
		IUserProfileService {

	@Autowired
	private IUserProfileDao dao;

	public UserProfileService() {
	}

	@Override
	@Transactional
	public UserProfile getUserProfileByUser(int userId) {
		UserProfile userProfile = null;
		try {
			userProfile = dao.getUserProfileByUser(userId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return userProfile;

	}

}
