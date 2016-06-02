package com.roditeli.dao;

import org.hibernate.Session;

import com.roditeli.api.dao.IAdressDao;
import com.roditeli.model.Adress;

public class AdressDao implements IAdressDao{

	@Override
	public Adress getById(Session session, int idModel) throws Exception {
		return (Adress)session.get(Adress.class, idModel);
	}

}
