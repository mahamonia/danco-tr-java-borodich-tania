package com.roditeli.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IUserDao;
import com.roditeli.model.Theme;
import com.roditeli.model.User;

@Repository
public class UserDao extends BaseDao<User>implements IUserDao{

	@Override
	@Transactional
	public User getById(int idModel) {
		return (User)sessionFactory.getCurrentSession().get(User.class, idModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		return criteria.list();
	}

	@Override
	@Transactional
	public User getUser(String login) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		User user = (User)criteria.createCriteria("authen", JoinType.RIGHT_OUTER_JOIN).add(Restrictions.eq("login", login)).list().get(0);
		
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Theme> getThemeRubricUser(int userId, int rubricId) {
		List<Theme> list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Theme.class);
		list = criteria.createCriteria("author", JoinType.RIGHT_OUTER_JOIN).add(Restrictions.eq("user.id", userId)).add(Restrictions.eq("rubric_fk", rubricId)).list();
		return list;
	}


}
