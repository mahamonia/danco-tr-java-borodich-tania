package com.roditeli.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IMessageThemeDao;
import com.roditeli.model.MessageTheme;

@Repository
public class MessageThemeDao extends BaseDao<MessageTheme>implements IMessageThemeDao{

	@Override
	@Transactional
	public MessageTheme getById(int idModel) {

		return (MessageTheme)sessionFactory.getCurrentSession().get(MessageTheme.class, idModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<MessageTheme> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MessageTheme.class);
		return criteria.list();
	}



}
