package com.danco.api.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.danco.model.entity.BaseModel;

public interface BaseDao<T extends BaseModel> {

	public void create(Connection connect, T model);
	
	public int getIdForNewModel(Connection con);

	public void update(Connection con, T model);

	public void delete(Connection con, int idModel);

	public T getById(Connection con, int idModel);

	public List<T> getList(Connection con);
	
	public T parseResultSet (ResultSet result);

}
