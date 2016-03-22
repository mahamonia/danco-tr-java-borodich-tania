package com.danco.model.dao;

import java.sql.ResultSet;
import java.util.List;

import com.danco.model.entity.BaseModel;

public interface BaseDao<T extends BaseModel> {

	public void create(DataSource source, T model);
	
	public int getIdForNewModel(DataSource source);

	public void update(DataSource source, int idModel);

	public void delete(DataSource source, int idModel);

	public T getById(DataSource source, int idModel);

	public List<T> getList(DataSource source);
	
	public T parseResultSet (ResultSet result);

}
