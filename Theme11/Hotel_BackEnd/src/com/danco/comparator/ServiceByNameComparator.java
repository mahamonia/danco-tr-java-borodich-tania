package com.danco.comparator;

import java.util.Comparator;

import com.danco.training.entity.DailService;

public class ServiceByNameComparator implements Comparator<DailService> {

	@Override
	public int compare(DailService service1, DailService service2) {
		String name1 = service1.getName();
		String name2 = service2.getName();

		return name1.compareTo(name2);
	}

}
