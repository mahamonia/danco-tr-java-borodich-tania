package com.roditeli.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IBaseDao;
import com.roditeli.model.BaseModel;

@Repository
public abstract class BaseDao <T extends BaseModel> implements IBaseDao<T> {
		
	protected SessionFactory sessionFactory;
	
	@Required
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void create(T model)throws Exception {
		sessionFactory.getCurrentSession().save(model);
	}
	@Transactional
	public void update(T model)throws Exception {
		sessionFactory.getCurrentSession().update(model);
	}
	@Transactional
	public void delete(T model)throws Exception {
		sessionFactory.getCurrentSession().delete(model);
	}	

}
