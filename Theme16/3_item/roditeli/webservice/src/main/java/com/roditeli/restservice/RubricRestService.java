package com.roditeli.restservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roditeli.api.service.IRubricService;
import com.roditeli.api.service.IUserProfileService;
import com.roditeli.model.Rubric;
import com.roditeli.model.UserProfile;

@Controller
public class RubricRestService {
	
	private IRubricService service;
	
	private IUserProfileService servisUser;

	@Required
	@Autowired
	public void setRubricService(IRubricService service) {
		this.service = service;
	}
		
	@Required
	@Autowired
	public void setServisUser(IUserProfileService servisUser) {
		this.servisUser = servisUser;
	}

	public RubricRestService() {
		System.out.println("rest sevise create ~ " + service);
	}

	@RequestMapping(value = "/rubric/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Rubric getRubricById(@PathVariable("id") int id) {
		
		System.out.println("rest service START");
		Rubric rubric = service.getById(id);
	        
		return rubric;

	}
	
	@RequestMapping(value = "/rubrics", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Rubric> getListRubric(){
		List<Rubric> list = null;
		list = service.getAll();
		return list;		
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody UserProfile getUser(){
		
		UserProfile user = servisUser.getById(1);
		System.out.println("rubric whit id 1 -- " +user.getName());
		return user;
		
	}

}
