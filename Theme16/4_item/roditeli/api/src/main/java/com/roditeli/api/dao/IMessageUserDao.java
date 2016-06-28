package com.roditeli.api.dao;

import java.util.List;

import com.roditeli.model.MessageUser;

public interface IMessageUserDao extends IBaseDao<MessageUser>{
	
	public List<MessageUser> getDialogsUser(String idUser)throws Exception;
	
	public List<MessageUser> getMessageDialog(String idUser, String interlocutor)throws Exception;
	
	public int amountNewMessage(String idUser) throws Exception;

}
