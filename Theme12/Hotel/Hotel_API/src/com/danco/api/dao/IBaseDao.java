package com.danco.api.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import com.danco.model.entity.BaseModel;

public interface IBaseDao<T extends BaseModel> {

	public void create(Session session, T model)throws SQLException;

	public void update(Session session, T model)throws SQLException;

	public void delete(Session session, int idModel)throws SQLException;

	public T getById(Session session, int idModel)throws SQLException;

	public List<T> getList(Session session)throws SQLException;

}
