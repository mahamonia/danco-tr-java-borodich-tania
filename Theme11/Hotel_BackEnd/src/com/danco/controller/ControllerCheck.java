package com.danco.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.backend.IControllerCheck;
import com.danco.model.dao.CheckDao;
import com.danco.model.dao.DataSource;
import com.danco.model.entity.Check;

public class ControllerCheck implements IControllerCheck {

	private static final Logger LOGGER = LogManager
			.getLogger(ControllerCheck.class);

	private CheckDao checkDao;
	private DataSource source;

	public ControllerCheck(DataSource source, CheckDao checkDao) {
		this.source = source;
		this.checkDao = checkDao;

	}

	@Override
	public void createCheck(Check check) {
		try {
			checkDao.create(source, check);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void updateCheck(int idCheck) {
		checkDao.update(source, idCheck);

	}

	@Override
	public void deleteCheck(int idCheck) {
		checkDao.delete(source, idCheck);

	}

	@Override
	public List<Check> getListCheck() {
		return checkDao.getList(source);
	}

	@Override
	public Check getCheck(int idCheck) {
		return checkDao.getById(source, idCheck);
	}

	@Override
	public Check getIdCheckForIdGuest(int idGuest) {
		
		return checkDao.getById(source, checkDao.getIdCheckForIdGuest(source, idGuest));

	}

	@Override
	public void addDateInSettle(int idCheck, String dateInSettle) {
		Check check = checkDao.getById(source, idCheck);
		if (check.getDateInSettle() != null) {
			check.setDateInSettle(LocalDateTime.parse(dateInSettle));
			checkDao.update(source, idCheck);
		}
	}

	@Override
	public void addDateOutSettle(int idCheck, String dateOutSettle) {
		Check check = checkDao.getById(source, idCheck);
		if (check.getDateOutSettle() != null) {
			check.setDateInSettle(LocalDateTime.parse(dateOutSettle));
			checkDao.update(source, idCheck);
		}

	}

	@Override
	public void addRoomInCheck(int idCheck, int idRoom) {
		Check check = checkDao.getById(source, idCheck);
		if (check.getIdRoom() != 0) {
			check.setIdRoom(idRoom);
			checkDao.update(source, idCheck);
		}
	}

	@Override
	public void changeStatusCheck(int idCheck, String status) {
		Check check = checkDao.getById(source, idCheck);
		check.setStatus(Boolean.getBoolean(status));
	}

	@Override
	public int getRoomInLiveGuest(int idGuest) {

		int idCheck = checkDao.getIdCheckForIdGuest(source, idGuest);
		Check check = checkDao.getById(source, idCheck);
		return check.getIdRoom();
	}

}
