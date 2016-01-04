package com.danco.training.controller;

import com.danco.training.entity.Service;

public interface IPrintService {
	
	public Service [] printServicesSortedByName(Service [] servicesList) ;
	public Service [] printServicesSortedByPrice(Service [] servicesList) ;


}
