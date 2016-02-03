package com.danco.comparator;

import java.util.Comparator;
import com.danco.training.entity.Room;

public class RoomByNumberComparator implements Comparator<Room> {

	@Override
	public int compare(Room room1, Room room2) {
		int number1 = room1.getNumber();
		int number2 = room2.getNumber();

		if (number1 > number2) {
			return 1;
		} else if (number1 < number2) {
			return -1;
		} else {
			return 0;
		}
	}
}
