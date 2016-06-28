package com.roditeli.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IThemeDao;
import com.roditeli.model.Theme;

@Repository
public class ThemeDao extends BaseDao<Theme> implements IThemeDao {

	private final static String SORT_PARAMETR_DATE = "date";
	private final static String RESTRICTIVE_PARAMETR_RUBRIC = "rubric.id";
	private final static String RESTRICTIVE_PARAMETR_USER = "user.id";

	public ThemeDao() {
		super(Theme.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Theme> getThemeCurrentRubric(int rubricId) {
		List<Theme> list = null;
		// Criteria criteria =
		// sessionFactory.getCurrentSession().createCriteria(clazz);
		list = getCriteria()
				.add(Restrictions.eq(RESTRICTIVE_PARAMETR_RUBRIC, rubricId))
				.addOrder(Order.asc(SORT_PARAMETR_DATE)).list();
		System.out.println(list);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Theme> getThemeRubricForUser(int userId, int rubricId)
			throws Exception {
		List<Theme> list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				clazz);
		list = criteria.createCriteria("author", JoinType.RIGHT_OUTER_JOIN)
				.add(Restrictions.eq(RESTRICTIVE_PARAMETR_USER, userId))
				.add(Restrictions.eq(RESTRICTIVE_PARAMETR_RUBRIC, rubricId))
				.list();
		return list;
	}

}
