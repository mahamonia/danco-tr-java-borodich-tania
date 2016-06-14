package com.roditeli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.roditeli.api.dao.IUserDao;
import com.roditeli.api.service.IUserService;
import com.roditeli.model.Theme;
import com.roditeli.model.User;

@Service
public class UserService extends BaseService<User> implements IUserService{
	
	private IUserDao dao;
	
	@Required
	@Autowired
	public void setDao(IUserDao dao) {
		this.dao = dao;
	}
	
	public UserService() {
	}

	@Override
	public User getUser(String login) {
		return dao.getUser(login);
	}

	@Override
	public List<Theme> getThemeRubricUser(int userId, int rubricId) {
		return dao.getThemeRubricUser(userId, rubricId);
	}

}
