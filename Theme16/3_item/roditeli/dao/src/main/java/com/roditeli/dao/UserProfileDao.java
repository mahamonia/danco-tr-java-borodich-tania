package com.roditeli.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IUserProfileDao;
import com.roditeli.model.Authentication;
import com.roditeli.model.User;
import com.roditeli.model.UserProfile;

@Repository
public class UserProfileDao extends BaseDao<UserProfile>implements IUserProfileDao{

	@Override
	@Transactional
	public UserProfile getById(int idModel) {
		return (UserProfile)sessionFactory.getCurrentSession().get(UserProfile.class, idModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<UserProfile> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserProfile.class);
		return criteria.list();
	}

	@Override
	@Transactional
	public UserProfile getUserProfileByUser(int userId) {
		UserProfile userProfile = null;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(UserProfile.class);
		userProfile = (UserProfile)crit.createCriteria("user", JoinType.RIGHT_OUTER_JOIN)
				.add(Restrictions.eq("id", userId)).list().get(0);
		return userProfile;
	}




}
