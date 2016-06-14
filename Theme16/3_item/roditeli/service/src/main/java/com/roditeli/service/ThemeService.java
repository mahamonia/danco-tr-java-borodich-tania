package com.roditeli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.roditeli.api.dao.IThemeDao;
import com.roditeli.api.service.IThemeService;
import com.roditeli.model.Theme;

@Service
public class ThemeService extends BaseService<Theme> implements IThemeService{
	
	private IThemeDao dao;

	@Required
	@Autowired
	public void setDao(IThemeDao dao) {
		this.dao = dao;
	}
	
	public ThemeService() {
	}

	@Override
	public List<Theme> getThemeCurrentRubric(int rubricId) {
		return dao.getThemeCurrentRubric(rubricId);
	}

}
