package com.danco.api;

import java.util.List;

import com.danco.training.entity.DailService;

public interface IParseUtilityCSVForDailService {
	public List<DailService> importData();
	public void exportData(List<DailService> list);

}
