package com.roditeli.api.dao;

import java.util.List;

import com.roditeli.model.Theme;


public interface IThemeDao extends IBaseDao<Theme>{
	
	public List<Theme> getThemeCurrentRubric (int rubricId);

}
