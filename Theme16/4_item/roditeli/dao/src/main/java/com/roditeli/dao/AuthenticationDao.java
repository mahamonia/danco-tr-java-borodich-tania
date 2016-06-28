package com.roditeli.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IAuthenticationDao;
import com.roditeli.model.Authentication;

;

@Repository
public class AuthenticationDao extends BaseDao<Authentication> implements
		IAuthenticationDao {

	private final static String RESTRICTIVE_PARAMETR = "login";

	public AuthenticationDao() {
		super(Authentication.class);
	}

	@Override
	public Authentication getByLogin(String login) throws Exception {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(clazz);
		Authentication auth = (Authentication) crit
				.add(Restrictions.eq(RESTRICTIVE_PARAMETR, login)).list()
				.get(0);
		return auth;
	}

}
