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
		chekDao.update(connect, chek);
	}

	@Override
	public void deleteChek(Connection connect, int idChek) {
		chekDao.delete(connect, idChek);
	}

	@Override
	public List<Chek> getListChek(Connection connect) {
		return chekDao.getList(connect);
	}
	public List<Chek> getListChekSortedByDateOutSettle(Connection connect) {
		return chekDao.getListChekSortedByDateOutSettle(connect);
	}
	
	@Override
	public Chek getChek(Connection connect, int idChek) {
		return chekDao.getById(connect, idChek);
	}

	@Override
	public Chek getChekForIdGuest(Connection connect, int idGuest) {		
		return chekDao.getChekForIdGuest(connect, idGuest);
	}

	@Override
	public void changeStatusChek(Connection connect, int idCheck, String status) {
		Chek check = chekDao.getById(connect, idCheck);
		check.setStatus(Boolean.getBoolean(status));
	}

	@Override
	public int getRoomInLiveGuest(Connection connect, int idGuest) {
		Chek check = chekDao.getChekForIdGuest(connect, idGuest);
		return check.getRoom().getId();
	}

}
