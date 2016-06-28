package com.roditeli.restservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roditeli.api.service.IEventService;
import com.roditeli.api.service.IUserService;
import com.roditeli.model.Event;
import com.roditeli.model.User;

@Controller
public class EventRestService {

	@Autowired
	private IEventService service;

	private Map<String, Object> rezult;

	private static final String OBJECT = "object";
	private static final String MESSAGE = "message";
	private static final String SERVER_ERROR = "Server error";
	private static final String SUCCESSFULLY = "Successfully";

	@RequestMapping(value = "/events/{userId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Map<String, Object> getEventList(
			@PathVariable("userId") int userId) {

		rezult = new HashMap<String, Object>();
		List<Event> list = service.getEvetsListForUser(userId);

		if (list != null) {
			rezult.put(OBJECT, list);
		} else {
			rezult.put(MESSAGE, SERVER_ERROR);
		}

		return rezult;
	}

	@RequestMapping(value = "/event/", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, Object> createEvent(
			@RequestBody Event event) {

		rezult = new HashMap<String, Object>();
		boolean isCompleted = service.delete(event);
		if (!isCompleted) {
			rezult.put(MESSAGE, SERVER_ERROR);
		} else {
			rezult.put(MESSAGE, SUCCESSFULLY);
		}

		return rezult;
	}

	@RequestMapping(value = "/event/", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody Map<String, Object> deleteEvent(
			@RequestBody Event event) {

		rezult = new HashMap<String, Object>();
		boolean isCompleted = service.create(event);
		if (!isCompleted) {
			rezult.put(MESSAGE, SERVER_ERROR);
		} else {
			rezult.put(MESSAGE, SUCCESSFULLY);
		}

		return rezult;
	}

}
