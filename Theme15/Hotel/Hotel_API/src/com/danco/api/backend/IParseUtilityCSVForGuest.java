package com.danco.api.backend;

import java.util.List;

import com.danco.model.entity.Guest;

public interface IParseUtilityCSVForGuest {
	
	public List<Guest> importData(String nameFile);

	public void exportData(List<Guest> list, String nameFile);

}
