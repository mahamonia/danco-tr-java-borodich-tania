package com.danco.controller;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerChek;
import com.danco.api.dao.IChekDao;
import com.danco.model.entity.Chek;

public class ControllerChek implements IControllerChek {

	private static final Logger LOGGER = LogManager
			.getLogger(ControllerChek.class);

	@Injection
	private IChekDao chekDao;

	public ControllerChek() {
	}

	@Override
	public void createChek(Connection connect, Chek chek) {
		try {
			chekDao.create(connect, chek);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void updateChek(Connection connect, Chek chek) {
		try {
			chekDao.update(connect, chek);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void deleteChek(Connection connect, int idChek) {
		try {
			chekDao.delete(connect, idChek);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public List<Chek> getListChek(Connection connect) {
		List<Chek> checkList = null;
		try {
			checkList =  chekDao.getList(connect);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return checkList;
	}
	public List<Chek> getListChekSortedBy(Connection connect, String param) {
		List<Chek> checkList = null;
		try {
			checkList = chekDao.getListChekSorted(connect, param);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return checkList;
	}
	
	@Override
	public Chek getChek(Connection connect, int idChek) {
		Chek check = null;
		try {
			check = chekDao.getById(connect, idChek);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return check;
	}

	@Override
	public Chek getChekForIdGuest(Connection connect, int idGuest) {		
		Chek check = null;
		try {
			check = chekDao.getChekForIdGuest(connect, idGuest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return check;
	}

	@Override
	public void changeStatusChek(Connection connect, int idCheck, String status) {
		Chek check = null;
		try {
			check = chekDao.getById(connect, idCheck);
			check.setStatus(Boolean.getBoolean(status));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
	}

	@Override
	public int getRoomInLiveGuest(Connection connect, int idGuest) {
		Chek check = null;
		try {
			check = chekDao.getChekForIdGuest(connect, idGuest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return check.getRoom().getId();
	}

}
