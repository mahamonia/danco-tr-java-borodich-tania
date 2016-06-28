package com.roditeli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.roditeli.api.dao.IThemeDao;
import com.roditeli.api.service.IThemeService;
import com.roditeli.model.Theme;

@Service
public class ThemeService extends BaseService<Theme> implements IThemeService {
	@Autowired
	private IThemeDao dao;

	public ThemeService() {
	}

	@Override
	@Transactional
	public List<Theme> getThemeCurrentRubric(int rubricId) {
		List<Theme> list = null;
		try {
			dao.getThemeCurrentRubric(rubricId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;

	}

	@Override
	@Transactional
	public List<Theme> getThemeRubricForUser(int userId, int rubricId) {
		List<Theme> list = null;
		try {
			dao.getThemeRubricForUser(userId, rubricId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return list;

	}
}
