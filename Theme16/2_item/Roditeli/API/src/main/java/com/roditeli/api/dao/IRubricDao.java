package com.roditeli.api.dao;

import org.hibernate.Session;

public interface IRubricDao{
	default void create(Session session, int model)throws Exception {
		session.save(model);
	}

	default void update(Session session, int model)throws Exception {
		session.update(model);
	}

	default void delete(Session session, int model)throws Exception {
		session.delete(model);
	}

	public Object getById(Session session, int idModel)throws Exception ;

}
