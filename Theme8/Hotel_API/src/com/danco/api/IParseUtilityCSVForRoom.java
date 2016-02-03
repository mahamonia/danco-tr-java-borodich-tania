package com.danco.api;

import java.util.List;
import com.danco.training.entity.Room;

public interface IParseUtilityCSVForRoom {
	public List<Room> importData();
	public void exportData(List<Room> list);

}
