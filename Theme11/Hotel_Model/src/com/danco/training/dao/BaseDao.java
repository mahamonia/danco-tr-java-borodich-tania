package com.danco.training.dao;

import com.danco.training.entity.BaseModel;

public interface BaseDao <T extends BaseModel>{
	
	public void create (T baseModel);
	public void update(T baseModel);
	public void delete(T baseModel);
	public T getById();
	

}
