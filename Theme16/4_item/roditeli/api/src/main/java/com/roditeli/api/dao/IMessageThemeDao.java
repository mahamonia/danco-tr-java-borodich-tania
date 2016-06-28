package com.roditeli.api.dao;

import java.util.List;

import com.roditeli.model.MessageTheme;
import com.roditeli.model.Theme;


public interface IMessageThemeDao extends IBaseDao<MessageTheme>{
	
	public List<MessageTheme> getMessageCurrentTheme(int themeId)throws Exception;

}
