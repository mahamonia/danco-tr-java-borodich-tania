package com.roditeli.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IUserProfileDao;
import com.roditeli.model.User;
import com.roditeli.model.UserProfile;

@Repository
public class UserProfileDao extends BaseDao<UserProfile> implements
		IUserProfileDao {

	public UserProfileDao() {
		super(UserProfile.class);
	}

	@Override
	public UserProfile getUserProfileByUser(int userId)
			throws HibernateException {
		UserProfile userProfile = null;
		userProfile = (UserProfile) getCriteria()
				.createCriteria("user", JoinType.RIGHT_OUTER_JOIN)
				.add(Restrictions.eq("id", userId)).list().get(0);
		return userProfile;
	}

}
