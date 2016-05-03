package com.danco.api.backend;

import java.util.List;

import com.danco.model.entity.Service;

public interface IParseUtilityCSVForService {
	public List<Service> importData();
	public void exportData(List<Service> list);

}
