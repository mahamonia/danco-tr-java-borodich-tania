package com.roditeli.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.roditeli.api.dao.IChildrenDao;
import com.roditeli.api.service.IChildrenService;
import com.roditeli.model.Children;

@Service
public class ChildrenService extends BaseService<Children>implements IChildrenService{
		
	private IChildrenDao dao;
	
	@Required
	@Autowired
	public void setDao(IChildrenDao dao) {
		this.dao = dao;
	}


}
