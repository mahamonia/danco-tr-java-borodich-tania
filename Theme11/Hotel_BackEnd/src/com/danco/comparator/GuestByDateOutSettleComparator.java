package com.danco.comparator;

import java.time.LocalDateTime;
import java.util.Comparator;

import com.danco.training.entity.Guest;

public class GuestByDateOutSettleComparator implements Comparator<Guest> {

	@Override
	public int compare(Guest guest1, Guest guest2) {
		
		LocalDateTime date1 = guest1.getCheck().getDateOutSettle();
		LocalDateTime date2 = guest2.getCheck().getDateOutSettle();

		return date1.compareTo(date2);
	}
}
