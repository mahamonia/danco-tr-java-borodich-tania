package com.roditeli.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IRubricDao;
import com.roditeli.api.service.IRubricService;

@Service
public class RubricService implements IRubricService{
	
	@Autowired
	private IRubricDao dao;

	@Autowired
	private SessionFactory sessionFactory;

	@Required
	public void setDao(IRubricDao dao) {
		this.dao = dao;
	}

	@Required
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Transactional
	@Override
	public Object getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Object object = null;
		try {
			object = dao.getById(session, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

}
