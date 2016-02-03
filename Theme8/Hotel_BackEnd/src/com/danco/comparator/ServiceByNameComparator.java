package com.danco.comparator;

import java.util.Comparator;

import com.danco.training.entity.Service;

public class ServiceByNameComparator implements Comparator<Service> {

	@Override
	public int compare(Service service1, Service service2) {
		String name1 = service1.getName();
		String name2 = service2.getName();

		return name1.compareTo(name2);
	}

}
