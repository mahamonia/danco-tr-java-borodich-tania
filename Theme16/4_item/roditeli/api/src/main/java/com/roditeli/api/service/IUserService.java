package com.roditeli.api.service;

import java.util.List;

import com.roditeli.model.Theme;
import com.roditeli.model.User;

public interface IUserService extends IBaseService<User> {

	public User getUser(String login);

	public User logOut(String login);

	public int amountNewFriends(int userId);

	public List<User> getFriendsList(int userId);

	public boolean addFriend(int userId, int friendId);
	
	public boolean deleteFriend(int userId, int friendId);

}
