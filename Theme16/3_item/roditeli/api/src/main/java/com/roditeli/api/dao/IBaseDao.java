package com.roditeli.api.dao;

import java.util.List;

import com.roditeli.model.BaseModel;

public interface IBaseDao <T extends BaseModel>{

	public void create(T model) throws Exception;

	public void update(T model) throws Exception;

	public void delete(T model) throws Exception;

	public T getById(int idModel) throws Exception;

	public List<T> getAll() throws Exception;

}
