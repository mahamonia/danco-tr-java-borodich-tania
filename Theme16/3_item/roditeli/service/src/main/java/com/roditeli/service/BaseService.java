package com.roditeli.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.roditeli.api.dao.IBaseDao;
import com.roditeli.api.service.IBaseService;
import com.roditeli.model.BaseModel;

@Service
public abstract class BaseService <T extends BaseModel> implements IBaseService<T>{
	
	protected static final Logger LOGGER = LogManager
			.getLogger(BaseService.class);
	
	private IBaseDao<T> dao;

	@Required
	@Autowired
	public void setDao(IBaseDao<T> dao) {
		this.dao = dao;
	}

	@Override
	public void create(T model) {
		try {
			dao.create(model);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}		
	}

	@Override
	public void update(T model) {
		try {
			dao.update(model);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
	}

	@Override
	public void delete(T model) {
		try {
			dao.delete(model);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
	}

	@Override
	public T getById(int idModel) {
		T model = null;
		try {
			model = dao.getById(idModel);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return model;
	}

	@Override
	public List<T> getAll() {
		List<T> list = null;
		
		try {
			list = dao.getAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}

}
