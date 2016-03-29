package com.danco.api.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.danco.model.entity.BaseModel;

public interface IBaseDao<T extends BaseModel> {

	public void create(Connection connect, T model)throws SQLException;

	public void update(Connection con, T model)throws SQLException;

	public void delete(Connection con, int idModel)throws SQLException;

	public T getById(Connection con, int idModel)throws SQLException;

	public List<T> getList(Connection con)throws SQLException;

}
