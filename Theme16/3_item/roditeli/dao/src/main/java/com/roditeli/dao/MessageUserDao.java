package com.roditeli.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IMessageUserDao;
import com.roditeli.model.MessageUser;

@Repository
public class MessageUserDao extends BaseDao<MessageUser> implements IMessageUserDao{

	@Override
	@Transactional
	public MessageUser getById(int idModel) {
		return (MessageUser)sessionFactory.getCurrentSession().get(MessageUser.class, idModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<MessageUser> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MessageUser.class);
		return criteria.list();
	}



}
