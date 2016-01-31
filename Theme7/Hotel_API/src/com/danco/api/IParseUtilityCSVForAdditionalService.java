package com.danco.api;

import java.util.List;

import com.danco.training.entity.AdditionalService;

public interface IParseUtilityCSVForAdditionalService {
	public List<AdditionalService> importData();
	public void exportData(List<AdditionalService> list);

}
