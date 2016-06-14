package com.roditeli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.roditeli.api.dao.IMessageThemeDao;
import com.roditeli.api.service.IMessageThemeService;
import com.roditeli.model.MessageTheme;

@Service
public class MessageThemeService extends BaseService<MessageTheme> implements IMessageThemeService{

	private IMessageThemeDao dao;

	@Required
	@Autowired
	public void setDao(IMessageThemeDao dao) {
		this.dao = dao;
	}
	
	public MessageThemeService() {
	}
}
