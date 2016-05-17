package com.danco.api.dao;

import java.util.List;

import org.hibernate.Session;

import com.danco.model.entity.Chek;

public interface IChekDao extends IBaseDao<Chek> {

	public List<Chek> getChekListForIdGuest(Session session, int idGuest)
			throws Exception;

	public List<Chek> getList(Session session, String param) throws Exception;

}
