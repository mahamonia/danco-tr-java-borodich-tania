package com.danco.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerGuest;
import com.danco.api.backend.IParseUtilityCSVForGuest;
import com.danco.api.dao.IGuestDao;
import com.danco.model.entity.*;

public class ControllerGuest implements IControllerGuest {

	private static final Logger LOGGER = LogManager
			.getLogger(ControllerGuest.class);

	@Injection
	private IParseUtilityCSVForGuest utility;
	@Injection
	private IGuestDao guestDao;

	public ControllerGuest() {

	}

	@Override
	public void createGuest(Session session, Guest guest) {
		try {
			guestDao.create(session, guest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void updateGuest(Session session, Guest guest) {
		try {
			guestDao.update(session, guest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

	}

	@Override
	public void deleteGuest(Session session, int idGuest) {
		try {
			guestDao.delete(session, guestDao.getById(session, idGuest));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public Guest getGuest(Session session, int idGuest) {
		Guest guest = null;
		try {
			guest = guestDao.getById(session, idGuest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return guest;
	}

	@Override
	public List<Guest> getListGuest(Session session, String param) {
		List<Guest> list = null;
		try {
			list = guestDao.getList(session, param);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}

	@Override
	public int getAmountGuest(Session session) {
		int amount = 0;
		try {
			amount = guestDao.getList(session, "id").size() + 1;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return amount;
	}

	@Override
	public List<Guest> importGuestsList() {
		return utility.importData();
	}

	@Override
	public void exportGuestsList(Session session) {
		try {
			utility.exportData(guestDao.getList(session, "id"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
