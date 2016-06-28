package com.roditeli.api.service;

import java.util.List;

import com.roditeli.model.MessageUser;

public interface IMessageUserService extends IBaseService<MessageUser>{
	
public List<MessageUser> getDialogsUser(String idUser);
	
	public List<MessageUser> getMessageDialog(String idUser, String interlocutor);
	
	public int amountNewMessage(String idUser);

}
