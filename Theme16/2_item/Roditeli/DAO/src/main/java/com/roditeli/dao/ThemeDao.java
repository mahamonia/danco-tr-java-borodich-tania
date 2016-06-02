package com.roditeli.dao;

import org.hibernate.Session;

import com.roditeli.api.dao.IBaseDao;
import com.roditeli.model.Theme;

public class ThemeDao implements IBaseDao<Theme>{

	@Override
	public Theme getById(Session session, int idModel) throws Exception {
		return (Theme)session.get(Theme.class, idModel);
	}

}
