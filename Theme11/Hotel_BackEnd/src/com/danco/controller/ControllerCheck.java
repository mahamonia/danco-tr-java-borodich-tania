package com.danco.controller;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.backend.IControllerCheck;
import com.danco.model.dao.CheckDao;
import com.danco.model.entity.Check;

public class ControllerCheck implements IControllerCheck {

	private static final Logger LOGGER = LogManager
			.getLogger(ControllerCheck.class);

	private CheckDao checkDao;

	public ControllerCheck(CheckDao checkDao) {

		this.checkDao = checkDao;

	}

	@Override
	public void createCheck(Connection connect, Check check) {
		try {
			checkDao.create(connect, check);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void updateCheck(Connection connect, int idCheck) {
		checkDao.update(connect, idCheck);

	}

	@Override
	public void deleteCheck(Connection connect, int idCheck) {
		checkDao.delete(connect, idCheck);

	}

	@Override
	public List<Check> getListCheck(Connection connect) {
		return checkDao.getList(connect);
	}

	@Override
	public Check getCheck(Connection connect, int idCheck) {
		return checkDao.getById(connect, idCheck);
	}

	@Override
	public Check getIdCheckForIdGuest(Connection connect, int idGuest) {
		
		return checkDao.getById(connect, checkDao.getIdCheckForIdGuest(connect, idGuest));

	}

	@Override
	public void addDateInSettle(Connection connect, int idCheck, String dateInSettle) {
		Check check = checkDao.getById(connect, idCheck);
		if (check.getDateInSettle() != null) {
			check.setDateInSettle(LocalDateTime.parse(dateInSettle));
			checkDao.update(connect, idCheck);
		}
	}

	@Override
	public void addDateOutSettle(Connection connect, int idCheck, String dateOutSettle) {
		Check check = checkDao.getById(connect, idCheck);
		if (check.getDateOutSettle() != null) {
			check.setDateInSettle(LocalDateTime.parse(dateOutSettle));
			checkDao.update(connect, idCheck);
		}

	}

	@Override
	public void addRoomInCheck(Connection connect, int idCheck, int idRoom) {
		Check check = checkDao.getById(connect, idCheck);
		if (check.getIdRoom() != 0) {
			check.setIdRoom(idRoom);
			checkDao.update(connect, idCheck);
		}
	}

	@Override
	public void changeStatusCheck(Connection connect, int idCheck, String status) {
		Check check = checkDao.getById(connect, idCheck);
		check.setStatus(Boolean.getBoolean(status));
	}

	@Override
	public int getRoomInLiveGuest(Connection connect, int idGuest) {

		int idCheck = checkDao.getIdCheckForIdGuest(connect, idGuest);
		Check check = checkDao.getById(connect, idCheck);
		return check.getIdRoom();
	}

}
