package com.roditeli.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IRubricDao;
import com.roditeli.model.Rubric;

@Repository
public class RubricDao extends BaseDao<Rubric> implements IRubricDao {
	
	public RubricDao() {
	}

	@Transactional
	@Override
	public Rubric getById(int idModel) {
		return (Rubric)sessionFactory.getCurrentSession().get(Rubric.class, idModel);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<Rubric> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Rubric.class);
		return criteria.list();
	}

}
