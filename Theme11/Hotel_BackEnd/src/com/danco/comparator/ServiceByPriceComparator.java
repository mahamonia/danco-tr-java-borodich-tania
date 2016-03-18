package com.danco.comparator;

import java.util.Comparator;
import com.danco.training.entity.DailService;

public class ServiceByPriceComparator implements Comparator<DailService> {

	@Override
	public int compare(DailService service1, DailService service2) {
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
