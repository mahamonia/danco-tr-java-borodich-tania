package com.roditeli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IUserDao;
import com.roditeli.api.service.IUserService;
import com.roditeli.model.User;

@Service
public class UserService extends BaseService<User> implements IUserService {

	@Autowired
	private IUserDao dao;

	public UserService() {
	}

	@Override
	@Transactional
	public User getUser(String login) {
		User user = null;
		try {
			user = dao.getUserbyLogin(login);
			user.setStatus(true);
			dao.update(user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return user;
	}

	@Override
	@Transactional
	public User logOut(String login) {
		User user = null;
		try {
			user = dao.getUserbyLogin(login);
			user.setStatus(false);
			dao.update(user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return user;
	}

	@Override
	@Transactional
	public int amountNewFriends(int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public List<User> getFriendsList(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean addFriend(int userId, int friendId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean deleteFriend(int userId, int friendId) {
		// TODO Auto-generated method stub
		return false;
	}

}
