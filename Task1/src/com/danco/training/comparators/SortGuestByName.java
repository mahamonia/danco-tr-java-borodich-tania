package com.danco.training.comparators;

import java.util.Comparator;
import com.danco.training.entity.Guest;

public class SortGuestByName implements Comparator<Guest> {

	@Override
	public int compare(Guest guest1, Guest guest2) {
		String name1 = guest1.getName();
		String name2 = guest2.getName();

		return name1.compareTo(name2);
	}
}
