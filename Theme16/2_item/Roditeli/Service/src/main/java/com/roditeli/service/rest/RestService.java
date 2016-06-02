package com.roditeli.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roditeli.api.service.IRubricService;
import com.roditeli.model.Rubric;

public class RestService {
	
	@Autowired
	private IRubricService service;
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<Rubric> getRubricById(@PathVariable("id") int id){
		
		Rubric rubric = (Rubric)service.getById(id);
		return new ResponseEntity<Rubric>(rubric, HttpStatus.OK);
		
	}

}
