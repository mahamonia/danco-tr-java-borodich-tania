package com.roditeli.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roditeli.api.dao.IBaseDao;
import com.roditeli.model.BaseModel;

@Repository
public abstract class BaseDao <T extends BaseModel> implements IBaseDao<T> {
	@Autowired
	protected SessionFactory sessionFactory;
	
	protected Class<T> clazz;
	
	public BaseDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	protected Criteria getCriteria (){
		return sessionFactory.getCurrentSession().createCriteria(clazz);
	}
	
	public void create(T model) throws Exception{
		sessionFactory.getCurrentSession().save(model);
	}
	
	public void update(T model)throws Exception {
		sessionFactory.getCurrentSession().update(model);
	}
	
	public void delete(T model)throws Exception{
		sessionFactory.getCurrentSession().delete(model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(int idModel) throws Exception {
		return (T)sessionFactory.getCurrentSession().get(clazz, idModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() throws Exception{
		return getCriteria().list();
	}	

}
