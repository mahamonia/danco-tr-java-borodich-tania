package com.roditeli.api.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.roditeli.model.Theme;


public interface IThemeDao extends IBaseDao<Theme>{
	
	public List<Theme> getThemeCurrentRubric (int rubricId) throws Exception;
	
	public List<Theme> getThemeRubricForUser(int userId, int rubricId) throws Exception;

}
