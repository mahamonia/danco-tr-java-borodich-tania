package com.danco.training.comparators;

import java.util.Comparator;
import com.danco.training.entity.Room;

public class SortRoomByContent implements Comparator<Room> {

	@Override
	public int compare(Room room1, Room room2) {
		int content1 = room1.getContent();
		int content2 = room2.getContent();

		if (content1 > content2) {
			return 1;
		} else if (content1 < content2) {
			return -1;
		} else {
			return 0;
		}
	}
}
