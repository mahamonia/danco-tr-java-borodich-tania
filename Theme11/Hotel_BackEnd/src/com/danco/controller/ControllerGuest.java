package com.danco.controller;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	public void createGuest(Connection connect, Guest guest) {
		try {
			guestDao.create(connect, guest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void updateGuest(Connection connect, Guest guest) {
		try {
			guestDao.update(connect, guest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

	}

	@Override
	public void deleteGuest(Connection connect, int idGuest) {
		try {
			guestDao.delete(connect, idGuest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public Guest getGuest(Connection connect, int idGuest) {
		return guestDao.getById(connect, idGuest);
	}

	@Override
	public List<Guest> getListGuest(Connection connect) {
		return guestDao.getList(connect);
	}

	@Override
	public List<Guest> getListGuestSortedByName(Connection connect) {
		return guestDao.getListGuestSortedByName(connect);
	}

	@Override
	public int getAmountGuest(Connection connect) {
		return guestDao.getList(connect).size()+1;
	}

	@Override
	public List<Guest> importGuestsList(Connection connect) {
		return utility.importData();
	}

	@Override
	public void exportGuestsList(Connection connect) {
		utility.exportData(guestDao.getList(connect));

	}

}
