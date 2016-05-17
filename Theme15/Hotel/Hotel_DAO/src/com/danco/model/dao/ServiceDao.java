package com.danco.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.danco.api.dao.IServiceDao;
import com.danco.model.entity.Service;

public class ServiceDao implements IServiceDao {

	@Override
	public Service getById(Session session, int idModel) throws Exception {
		return (Service) session.get(Service.class, idModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Service> getList(Session session, String param)
			throws Exception {
		Criteria crit = session.createCriteria(Service.class);
		List<Service> serviceList = (List<Service>) crit.addOrder(
				Order.asc(param)).list();

		return serviceList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Service> getGuestThemServices(Session session, int idGuest)
			throws Exception {

		Criteria crit = session.createCriteria(Service.class);
		List<Service> serviceList = (List<Service>) crit.createCriteria("chek", JoinType.RIGHT_OUTER_JOIN)
				.add(Restrictions.eq("guest.id", idGuest)).add(Restrictions.eq("status", false)).list();
		return serviceList;
	}

	@Override
	public int getSumServiceForGuest(Session session, int idGuest)
			throws Exception {

		Criteria crit = session.createCriteria(Service.class);
		crit.setProjection(Projections.sum("price"))
				.createCriteria("chek", JoinType.RIGHT_OUTER_JOIN)
				.add(Restrictions.eq("guest.id", idGuest)).add(Restrictions.eq("status", false));
		crit.uniqueResult();
		Long summ = (Long) crit.uniqueResult();
		int sum = summ.intValue();

		return sum;
	}
}
