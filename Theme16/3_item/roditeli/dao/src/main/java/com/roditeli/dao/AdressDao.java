package com.roditeli.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IAdressDao;
import com.roditeli.model.Adress;

@Repository
public class AdressDao extends BaseDao<Adress> implements IAdressDao{

	@Override
	@Transactional
	public Adress getById(int idModel) {
		return (Adress)sessionFactory.getCurrentSession().get(Adress.class, idModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Adress> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Adress.class);
		return criteria.list();
	}


}
