package com.danco.api.backend;

import org.hibernate.Session;

import com.danco.model.entity.Audit;

public interface IControllerAudit {
	
	public void save(Session session,Audit audit);

}
