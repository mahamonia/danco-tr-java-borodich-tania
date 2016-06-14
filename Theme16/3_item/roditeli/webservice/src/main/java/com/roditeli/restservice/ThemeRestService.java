package com.roditeli.restservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roditeli.api.service.IThemeService;
import com.roditeli.model.Theme;

@Controller
public class ThemeRestService {
	
	private IThemeService service;
	
	@Required
	@Autowired
	public void setService(IThemeService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/themes/{rubricId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Theme> getThemeCurrentRubric(@PathVariable("rubricId") int rubricId) {
		List<Theme> list = null;
		list = service.getThemeCurrentRubric(rubricId);
		return list;

	}
	
	@RequestMapping(value = "/theme/", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody void createTheme( @RequestBody Theme theme) {
		service.create(theme);	

	}

}
