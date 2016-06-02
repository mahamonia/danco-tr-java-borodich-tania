package com.roditeli.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.roditeli.api.dao.IRubricDao;
import com.roditeli.model.Rubric;

@Repository
public class RubricDao implements IRubricDao{

	public RubricDao() {
	}
	
	@Override
	public Rubric getById(Session session, int idModel) throws Exception {
		return (Rubric)session.get(Rubric.class, idModel);
	}


}
