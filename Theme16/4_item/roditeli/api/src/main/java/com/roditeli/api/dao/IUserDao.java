package com.roditeli.api.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.roditeli.model.Theme;
import com.roditeli.model.User;


public interface IUserDao extends IBaseDao<User>{
	
	public User getUserbyLogin(String login) throws Exception;
	
	public int amountNewFriends (int userId) throws Exception;
	
	public List<User> getFriendsList(int userId) throws Exception;
	
	public void addFriend(int userId, int friendId)throws Exception;
	
	public void deleteFriend(int userId, int friendId)throws Exception;
	
	
	
	

}
