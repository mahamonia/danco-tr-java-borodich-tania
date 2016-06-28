package com.roditeli.api.service;

import java.util.List;

import com.roditeli.model.Theme;

public interface IThemeService extends IBaseService<Theme>{
	
	public List<Theme> getThemeCurrentRubric (int rubricId);
	
	public List<Theme> getThemeRubricForUser(int userId, int rubricId);

}
