package com.danco.server;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.api.backend.IServiceAdmin;
import com.danco.model.entity.Guest;
import com.danco.model.entity.Room;
import com.danco.model.entity.Service;
import com.danco.model.entity.Status;

public class ProtocolFromUiToBackEnd {

	private static final Logger LOGGER = LogManager
			.getLogger(ProtocolFromUiToBackEnd.class);

	private static final String ONE_OPTION = "1";
	private static final String TWO_OPTION = "2";
	private static final String TWO_DIFFERENT_OPTION = "2Dif";
	private static final String FOUR_OPTION = "4";
	private static final String OPTION_OBGECT_GUEST = "guest";
	private static final String OPTION_OBGECT_ROOM = "room";
	private static final String OPTION_OBGECT_SERVICE = "service";

	private IServiceAdmin admin;

	public ProtocolFromUiToBackEnd(IServiceAdmin admin) {
		this.admin = admin;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object parseStringToObject(String str) {

		String[] arrayStr = str.split(";");
		String type = arrayStr[0];
		String nameMetod = arrayStr[1];
		Object rezult = new Object();

		try {
			Class cl = this.admin.getClass();
			Class[] paramTypes = new Class[] {};
			Object[] args = new Object[] {};

			switch (type) {
			case ONE_OPTION:
				paramTypes = new Class[] { int.class };
				args = new Object[] { Integer.parseInt(arrayStr[2]) };
				break;
			case TWO_OPTION:
				paramTypes = new Class[] { int.class, int.class };
				args = new Object[] { Integer.parseInt(arrayStr[2]),
						Integer.parseInt(arrayStr[3]) };
				break;
			case TWO_DIFFERENT_OPTION:
				paramTypes = new Class[] { int.class, String.class };
				args = new Object[] { Integer.parseInt(arrayStr[2]),
						Integer.parseInt(arrayStr[3]) };
				break;
			case FOUR_OPTION:
				paramTypes = new Class[] { int.class, int.class, String.class,
						String.class };
				args = new Object[] { Integer.parseInt(arrayStr[2]),
						Integer.parseInt(arrayStr[3]), arrayStr[4], arrayStr[5] };
				break;
			case OPTION_OBGECT_GUEST:
				paramTypes = new Class[] { Guest.class };
				args = new Object[] { StringToGuest(arrayStr) };
				break;
			case OPTION_OBGECT_ROOM:
				paramTypes = new Class[] { Room.class };
				args = new Object[] { StringToRoom(arrayStr) };
				break;
			case OPTION_OBGECT_SERVICE:
				paramTypes = new Class[] { Service.class };
				args = new Object[] { StringToService(arrayStr) };
				break;
			default:
				break;
			}
			Method m = cl.getMethod(nameMetod, paramTypes);
			rezult = (Object)m.invoke(this.admin, args);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return rezult;
	}

	public Guest StringToGuest(String[] str) {
		Guest guest = new Guest(str[2], str[3]);

		return guest;
	}

	public Room StringToRoom(String[] str) {
		Room room = new Room(Integer.parseInt(str[2]),
				Integer.parseInt(str[3]), Status.FREE,
				Integer.parseInt(str[4]), Integer.parseInt(str[5]));
		return room;
	}

	public Service StringToService(String[] str) {
		Service service = new Service(str[2], Integer.parseInt(str[3]));
		return service;
	}

	

}
