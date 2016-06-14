package com.roditeli.api.service;

import java.util.List;

import com.roditeli.model.BaseModel;

public interface IBaseService <T extends BaseModel>{
	
	public void create(T model);

	public void update(T model);

	public void delete(T model);

	public T getById(int idModel);

	public List<T> getAll();

}
