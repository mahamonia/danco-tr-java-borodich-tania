package com.danco.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.danco.api.dao.IChekDao;
import com.danco.model.entity.Chek;

public class ChekDao implements IChekDao {

	public ChekDao() {
	}

	@Override
	public Chek getById(Session session, int idModel) throws Exception {
		Chek chek = (Chek) session.get(Chek.class, idModel);
		return chek;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chek> getList(Session session, String param)
			throws Exception {
		Criteria crit = session.createCriteria(Chek.class);
		List<Chek> chekList = crit.addOrder(Order.asc(param)).list();
		return chekList;
	}

	@Override
	public Chek getChekForIdGuest(Session session, int idGuest)
			throws Exception {
		Criteria crit = session.createCriteria(Chek.class);

		Chek chek = (Chek) crit.add(Restrictions.and(
				Restrictions.eq("Guest_idGuest", idGuest),
				Restrictions.eq("status", false)));

		return chek;
	}
}
