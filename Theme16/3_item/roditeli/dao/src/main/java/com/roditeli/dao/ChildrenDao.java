package com.roditeli.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IChildrenDao;
import com.roditeli.model.Children;
@Repository
public class ChildrenDao extends BaseDao<Children> implements IChildrenDao{

	@Override
	@Transactional
	public Children getById(int idModel) {
		return (Children)sessionFactory.getCurrentSession().get(Children.class, idModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Children> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Children.class);
		return criteria.list();
	}

}
