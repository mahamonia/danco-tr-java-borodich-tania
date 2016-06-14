package com.roditeli.api.service;

import com.roditeli.model.UserProfile;

public interface IUserProfileService extends IBaseService<UserProfile>{
	
	public UserProfile getUserProfileByUser(int userId);
	
	
	

}
