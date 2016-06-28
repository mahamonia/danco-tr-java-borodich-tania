package com.roditeli.dao;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.roditeli.api.dao.IMessageUserDao;
import com.roditeli.model.MessageUser;

@Repository
public class MessageUserDao extends BaseDao<MessageUser> implements
		IMessageUserDao {
	private final static String SORT_PARAMETR_DATE = "date";
	private final static String SORT_PARAMETR_STATUS = "STATUS";
	private final static String RESTRICTIVE_PARAMETR_AUTHOR = "author.id";
	private final static String RESTRICTIVE_PARAMETR_RECIPIENT = "recipient";

	public MessageUserDao() {
		super(MessageUser.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageUser> getDialogsUser(String idUser) throws Exception {
		List<MessageUser> list = null;
		list = (List<MessageUser>) getCriteria()
				.add(Restrictions.eq(RESTRICTIVE_PARAMETR_AUTHOR, idUser))
				.addOrder(Order.asc(SORT_PARAMETR_DATE)).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageUser> getMessageDialog(String idUser, String interlocutor)
			throws Exception {
		List<MessageUser> list = null;
		list = (List<MessageUser>) getCriteria()
				.add(Restrictions.eq(RESTRICTIVE_PARAMETR_AUTHOR, idUser))
				.add(Restrictions.eq(RESTRICTIVE_PARAMETR_RECIPIENT,
						interlocutor)).addOrder(Order.asc(SORT_PARAMETR_DATE))
				.list();
		return list;
	}

	@Override
	public int amountNewMessage(String idUser) throws Exception {
		int amount = 0;
		getCriteria().setProjection(Projections.rowCount())
				.add(Restrictions.eq(RESTRICTIVE_PARAMETR_RECIPIENT, idUser))
				.add(Restrictions.eq(SORT_PARAMETR_STATUS, false));

		amount = ((Long) getCriteria().uniqueResult()).intValue();
		return amount;
	}

}
