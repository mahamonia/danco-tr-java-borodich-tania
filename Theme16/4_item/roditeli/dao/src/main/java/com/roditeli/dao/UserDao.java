package com.roditeli.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IUserDao;
import com.roditeli.model.Theme;
import com.roditeli.model.User;

@Repository
public class UserDao extends BaseDao<User> implements IUserDao {

	public UserDao() {
		super(User.class);
	}

	@Override
	public User getUserbyLogin(String login) throws HibernateException {
		User user = (User) getCriteria()
				.createCriteria("authen", JoinType.RIGHT_OUTER_JOIN)
				.add(Restrictions.eq("login", login)).list().get(0);

		return user;
	}

	@Override
	public int amountNewFriends(int userId) throws Exception {

		List list = getCriteria()
				.createCriteria("userInvitesList", JoinType.RIGHT_OUTER_JOIN)
				.add(Restrictions.eq("userId", userId)).list();
		return list.size();
	}

	@Override
	public List<User> getFriendsList(int userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFriend(int userId, int friendId) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteFriend(int userId, int friendId) throws Exception {
		// TODO Auto-generated method stub

	}

}
