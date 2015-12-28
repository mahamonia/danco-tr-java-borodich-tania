package com.danco.training.controller;

import com.danco.training.comparators.TypeSort;
import com.danco.training.entity.Service;

public interface IPrintService {
	
	public void printServices(Service [] servicesList, TypeSort type) ;
	public void printServicesPrice(Service [] servicesList, TypeSort type) ;

}
