package com.danco.api.dao;

import org.hibernate.Session;

import com.danco.model.entity.BaseModel;

public interface IBaseDao<T extends BaseModel> {

	default void create(Session session, T model)throws Exception {
		session.save(model);
	}

	default void update(Session session, T model)throws Exception {
		session.update(model);
	}

	default void delete(Session session, T model)throws Exception {
		session.delete(model);
	}

	public T getById(Session session, int idModel)throws Exception ;

}
