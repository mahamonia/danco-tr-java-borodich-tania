package com.danco.training.comparator;

import java.util.Comparator;
import com.danco.training.entity.Service;

public class ServiceByPriceComparator implements Comparator<Service> {

	@Override
	public int compare(Service service1, Service service2) {
		int price1 = service1.getPrice();
		int price2 = service2.getPrice();

		if (price1 > price2) {
			return 1;
		} else if (price1 < price2) {
			return -1;
		} else {
			return 0;
		}
	}

}
