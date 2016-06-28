package com.roditeli.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IRubricDao;
import com.roditeli.model.Rubric;

@Repository
public class RubricDao extends BaseDao<Rubric> implements IRubricDao {

	public RubricDao() {
		super(Rubric.class);
	}
	



}
