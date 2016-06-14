package com.roditeli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.roditeli.api.dao.IRubricDao;
import com.roditeli.api.service.IRubricService;
import com.roditeli.model.Rubric;


@Service
public class RubricService extends BaseService<Rubric> implements IRubricService{
		
	private IRubricDao dao;

	@Required
	@Autowired
	public void setRubricDao(IRubricDao dao) {
		this.dao = dao;
	}

	public RubricService() {
	}
	
}
