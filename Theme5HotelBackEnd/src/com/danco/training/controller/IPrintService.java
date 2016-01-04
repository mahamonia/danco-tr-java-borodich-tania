package com.danco.training.controller;

import java.util.List;

import com.danco.training.entity.Service;

public interface IPrintService {
	
	public List<Service> printServicesSortedByName(List<Service> servicesList) ;
	public List<Service> printServicesSortedByPrice(List<Service> servicesList) ;


}
