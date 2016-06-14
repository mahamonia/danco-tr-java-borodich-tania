package com.roditeli.api.service;

import java.util.List;

import com.roditeli.model.Theme;
import com.roditeli.model.User;

public interface IUserService extends IBaseService<User> {
	
	public User getUser(String login);

	public List<Theme> getThemeRubricUser(int userId, int rubricId);

}
