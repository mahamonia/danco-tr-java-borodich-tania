package com.roditeli.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.roditeli.api.dao.IAdressDao;
import com.roditeli.api.service.IAdressService;
import com.roditeli.model.Adress;

@Service
public class AdressService extends BaseService<Adress> implements IAdressService {

	private IAdressDao dao;

	@Required
	@Autowired
	public void setDao(IAdressDao dao) {
		this.dao = dao;
	}

	public AdressService() {
	}

	
}
