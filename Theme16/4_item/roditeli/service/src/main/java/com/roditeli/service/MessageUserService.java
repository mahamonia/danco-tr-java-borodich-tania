package com.roditeli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IMessageUserDao;
import com.roditeli.api.service.IMessageUserService;
import com.roditeli.model.MessageUser;

@Service
public class MessageUserService extends BaseService<MessageUser> implements
		IMessageUserService {
	@Autowired
	private IMessageUserDao dao;

	public MessageUserService() {
	}

	@Transactional
	@Override
	public List<MessageUser> getDialogsUser(String idUser) {

		List<MessageUser> list = null;
		try {
			list = dao.getDialogsUser(idUser);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}

	@Override
	@Transactional
	public List<MessageUser> getMessageDialog(String idUser, String interlocutor) {
		List<MessageUser> list = null;
		try {
			list = dao.getMessageDialog(idUser, interlocutor);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}

	@Override
	@Transactional
	public int amountNewMessage(String idUser) {
		int amount = 0;
		try {
			amount = dao.amountNewMessage(idUser);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return amount;
	}

}
