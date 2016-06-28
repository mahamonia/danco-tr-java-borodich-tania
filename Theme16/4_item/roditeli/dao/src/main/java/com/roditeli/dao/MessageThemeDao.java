package com.roditeli.dao;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.roditeli.api.dao.IMessageThemeDao;
import com.roditeli.model.MessageTheme;
import com.roditeli.model.Theme;

@Repository
public class MessageThemeDao extends BaseDao<MessageTheme> implements
		IMessageThemeDao {

	private final static String SORT_PARAMETR = "date";
	private final static String RESTRICTIVE_PARAMETR = "theme.id";

	public MessageThemeDao() {
		super(MessageTheme.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageTheme> getMessageCurrentTheme(int themeId)
			throws Exception {
		List<MessageTheme> list = null;
		list = getCriteria()
				.add(Restrictions.eq(RESTRICTIVE_PARAMETR, themeId))
				.addOrder(Order.asc(SORT_PARAMETR)).list();
		return list;
	}

}
