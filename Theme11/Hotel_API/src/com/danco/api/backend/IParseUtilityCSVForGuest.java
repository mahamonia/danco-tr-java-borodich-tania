package com.danco.api.backend;

import java.util.List;
import com.danco.training.entity.Guest;

public interface IParseUtilityCSVForGuest {
	public List<Guest> importData();
	public void exportData(List<Guest> list);

}
