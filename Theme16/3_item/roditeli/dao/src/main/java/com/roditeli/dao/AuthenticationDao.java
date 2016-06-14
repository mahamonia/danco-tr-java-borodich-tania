package com.roditeli.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IAuthenticationDao;
import com.roditeli.model.Authentication;

@Repository
public class AuthenticationDao extends BaseDao<Authentication> implements IAuthenticationDao{

	@Override
	@Transactional
	public Authentication getById(int idModel) {
		return (Authentication)sessionFactory.getCurrentSession().get(Authentication.class, idModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Authentication> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Authentication.class);
		return criteria.list();
	}

	@Override
	@Transactional
	public Authentication getByLogin(String login) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Authentication.class);
		Authentication auth = (Authentication)crit.add(Restrictions.eq("login", login)).list().get(0);
		return auth;
	}


}
