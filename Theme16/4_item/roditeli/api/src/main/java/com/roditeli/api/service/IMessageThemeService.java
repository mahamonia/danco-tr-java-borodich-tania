package com.roditeli.api.service;

import java.util.List;

import com.roditeli.model.MessageTheme;
import com.roditeli.model.Theme;

public interface IMessageThemeService extends IBaseService<MessageTheme>{ 
	
	public List<MessageTheme> getMessageCurrentTheme(int themeId);

}
