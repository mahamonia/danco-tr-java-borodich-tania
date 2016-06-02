package com.roditeli.dao;

import org.hibernate.Session;

import com.roditeli.api.dao.IMessageThemeDao;
import com.roditeli.model.MessageTheme;

public class MessageThemeDao implements IMessageThemeDao{

	@Override
	public MessageTheme getById(Session session, int idModel) throws Exception {
		return (MessageTheme)session.get(MessageTheme.class, idModel);
	}

}
