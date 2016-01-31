package com.danco.comparator;

import java.util.Comparator;
import com.danco.training.entity.Room;

public class RoomByPriceComparator implements Comparator<Room> {

	@Override
	public int compare(Room room1, Room room2) {
		int price1 = room1.getPrice();
		int price2 = room2.getPrice();

		if (price1 > price2) {
			return 1;
		} else if (price1 < price2) {
			return -1;
		} else {
			return 0;
		}
	}

}
