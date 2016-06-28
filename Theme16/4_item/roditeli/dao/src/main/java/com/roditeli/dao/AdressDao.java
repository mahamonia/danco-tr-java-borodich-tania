package com.roditeli.dao;

import org.springframework.stereotype.Repository;


import com.roditeli.api.dao.IAdressDao;
import com.roditeli.model.Adress;

@Repository
public class AdressDao extends BaseDao<Adress> implements IAdressDao{
	
	public AdressDao() {
		super(Adress.class);
	}

}
