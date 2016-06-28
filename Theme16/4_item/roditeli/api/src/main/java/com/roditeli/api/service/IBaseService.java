package com.roditeli.api.service;

import java.util.List;

import com.roditeli.model.BaseModel;

public interface IBaseService <T extends BaseModel>{
	
	public boolean create(T model);

	public boolean update(T model);

	public boolean delete(T model);

	public T getById(int idModel);

	public List<T> getAll();

}
