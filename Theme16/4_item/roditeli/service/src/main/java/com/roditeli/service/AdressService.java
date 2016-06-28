package com.roditeli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roditeli.api.dao.IAdressDao;
import com.roditeli.api.service.IAdressService;
import com.roditeli.model.Adress;

@Service
public class AdressService extends BaseService<Adress> implements IAdressService {
	@Autowired
	private IAdressDao dao;

	public AdressService() {
	}

	
}
