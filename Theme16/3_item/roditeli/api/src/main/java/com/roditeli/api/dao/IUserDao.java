package com.roditeli.api.dao;

import java.util.List;

import com.roditeli.model.Theme;
import com.roditeli.model.User;


public interface IUserDao extends IBaseDao<User>{
	
	public User getUser(String login);
	
	public List<Theme> getThemeRubricUser(int userId, int rubricId);

}
