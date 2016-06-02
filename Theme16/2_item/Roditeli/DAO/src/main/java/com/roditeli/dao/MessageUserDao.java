package com.roditeli.dao;

import org.hibernate.Session;

import com.roditeli.api.dao.IBaseDao;
import com.roditeli.model.MessageUser;

public class MessageUserDao implements IBaseDao<MessageUser>{

	@Override
	public MessageUser getById(Session session, int idModel) throws Exception {
		return (MessageUser)session.get(MessageUser.class, idModel);
	}

}
