package com.roditeli.dao;

import org.springframework.stereotype.Repository;

import com.roditeli.api.dao.IChildrenDao;
import com.roditeli.model.Children;
@Repository
public class ChildrenDao extends BaseDao<Children> implements IChildrenDao{

	public ChildrenDao() {
		super(Children.class);
	}




}
