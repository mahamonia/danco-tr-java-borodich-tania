package com.danco.comparator;

import java.util.Comparator;
import com.danco.training.entity.Guest;

public class GuestByDateOutSettleComparator implements Comparator<Guest> {

	@Override
	public int compare(Guest guest1, Guest guest2) {
		String date1 = guest1.getDateOutSettle();
		String date2 = guest2.getDateOutSettle();

		return date1.compareTo(date2);
	}
}
