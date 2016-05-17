package com.danco.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.danco.annotation.Injection;
import com.danco.api.backend.IControllerAudit;
import com.danco.api.dao.IAuditDao;
import com.danco.model.entity.Audit;

public class ControllerAudit implements IControllerAudit{

	private static final Logger LOGGER = LogManager
			.getLogger(ControllerAudit.class);
	
	@Injection
	private IAuditDao auditDao;
	
	@Override
	public void save(Session session, Audit audit) {
		try {
			auditDao.create(session, audit);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

}
