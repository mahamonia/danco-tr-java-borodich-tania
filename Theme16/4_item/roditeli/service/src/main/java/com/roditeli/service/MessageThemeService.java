package com.roditeli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IMessageThemeDao;
import com.roditeli.api.service.IMessageThemeService;
import com.roditeli.model.MessageTheme;

@Service
public class MessageThemeService extends BaseService<MessageTheme> implements
		IMessageThemeService {
	@Autowired
	private IMessageThemeDao dao;

	public MessageThemeService() {
	}

	@Override
	@Transactional
	public List<MessageTheme> getMessageCurrentTheme(int themeId) {
		List<MessageTheme> list = null;

		try {
			list = dao.getMessageCurrentTheme(themeId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}
}
