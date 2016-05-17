package com.danco.api.backend;

import java.util.List;

import com.danco.model.entity.Room;

public interface IParseUtilityCSVForRoom {
	
	public List<Room> importData(String nameFile);

	public void exportData(List<Room> list, String nameFile);

}
