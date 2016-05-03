package com.danco.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerChek;
import com.danco.api.dao.IChekDao;
import com.danco.model.entity.Chek;

public class ControllerChek implements IControllerChek {

//	private static final Logger LOGGER = LogManager
//			.getLogger(ControllerChek.class);

	@Injection
	private IChekDao chekDao;

	public ControllerChek() {
	}

	@Override
	public void createChek(Session session, Chek chek) {
		try {
			chekDao.create(session, chek);
		} catch (Exception e) {
			//LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void updateChek(Session session, Chek chek) {
		try {
			chekDao.update(session, chek);
		} catch (Exception e) {
			//LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void deleteChek(Session session, int idChek) {
		try {
			chekDao.delete(session, chekDao.getById(session, idChek));
		} catch (Exception e) {
			//LOGGER.error(e.getMessage());
		}
	}

	@Override
	public List<Chek> getListChek(Session session, String param) {
		List<Chek> checkList = null;
		try {
			checkList =  chekDao.getList(session, param);
		} catch (Exception e) {
			//LOGGER.error(e.getMessage());
		}
		return checkList;
	}
	
	@Override
	public Chek getChek(Session session, int idChek) {
		Chek check = null;
		try {
			check = chekDao.getById(session, idChek);
		} catch (Exception e) {
			//LOGGER.error(e.getMessage());
		}
		return check;
	}

	@Override
	public Chek getChekForIdGuest(Session session, int idGuest) {		
		Chek check = null;
		try {
			check = chekDao.getChekForIdGuest(session, idGuest);
		} catch (Exception e) {
			//LOGGER.error(e.getMessage());
		}
		return check;
	}

	@Override
	public void changeStatusChek(Session session, int idCheck, String status) {
		Chek check = null;
		try {
			check = chekDao.getById(session, idCheck);
			check.setStatus(Boolean.getBoolean(status));
		} catch (Exception e) {
			//LOGGER.error(e.getMessage());
		}		
	}

	@Override
	public int getRoomInLiveGuest(Session session, int idGuest) {
		Chek check = null;
		try {
			check = chekDao.getChekForIdGuest(session, idGuest);
		} catch (Exception e) {
			//LOGGER.error(e.getMessage());
		}
		return check.getRoom().getId();
	}

}
