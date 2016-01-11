package com.danco.training.comparator;


public class Comparator {
	
	public static final RoomByContentComparator ROOM_BY_CONTENT = new RoomByContentComparator();
	public static final RoomByNumberComparator ROOM_BY_NUMBER = new RoomByNumberComparator();
	public static final RoomByPriceComparator ROOM_BY_PRICE = new RoomByPriceComparator();
	public static final RoomByStarsComparator ROOM_BY_STARS = new RoomByStarsComparator();
	
	public static final GuestByDateOutSettleComparator GUEST_BY_DATE = new GuestByDateOutSettleComparator();
	public static final GuestByNameComparator GUEST_BY_NAME = new GuestByNameComparator();
	
	public static final ServiceByNameComparator SERVICE_BY_NAME = new ServiceByNameComparator();
	public static final ServiceByPriceComparator SERVICE_BY_PRICE = new ServiceByPriceComparator();
	
}
