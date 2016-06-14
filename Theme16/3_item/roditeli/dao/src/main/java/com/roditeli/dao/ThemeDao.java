package com.roditeli.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IThemeDao;
import com.roditeli.model.Theme;

@Repository
public class ThemeDao extends BaseDao<Theme> implements IThemeDao{

	@Override
	@Transactional
	public Theme getById(int idModel) {
		return (Theme)sessionFactory.getCurrentSession().get(Theme.class, idModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Theme> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Theme.class);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Theme> getThemeCurrentRubric(int rubricId) {
		List<Theme> list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Theme.class);
		list = criteria.add(Restrictions.eq("rubric.id", rubricId)).list();
		return list;
	}



}
