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

import com.roditeli.api.service.IMessageThemeService;
import com.roditeli.api.service.IMessageUserService;
import com.roditeli.model.MessageUser;
import com.roditeli.model.Theme;

@Controller
public class MessageRestService {

	@Autowired
	private IMessageUserService service;

	private Map<String, Object> rezult;

	private static final String OBJECT = "object";
	private static final String MESSAGE = "message";
	private static final String MESSAGE_NOT_FOUND = "Message not found";
	private static final String ERROR = "Error, try it again";

	@RequestMapping(value = "/messageUser/", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, Object> createMessage(
			@RequestBody MessageUser message) {

		rezult = new HashMap<String, Object>();

		boolean isCompleted = service.create(message);
		if (!isCompleted) {
			rezult.put(MESSAGE, ERROR);
		}
		return rezult;
	}

	@RequestMapping(value = "/messageUser/", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody Map<String, Object> deleteMessage(
			@RequestBody MessageUser message) {

		rezult = new HashMap<String, Object>();

		boolean isCompleted = service.delete(message);
		if (!isCompleted) {
			rezult.put(MESSAGE, ERROR);
		}
		return rezult;
	}

	@RequestMapping(value = "/messageUser/{idUser}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, Object> getDialogsUser(
			@PathVariable("idUser") String idUser) {

		rezult = new HashMap<String, Object>();
		List<MessageUser> list = null;
		list = service.getDialogsUser(idUser);

		if (list != null) {
			rezult.put(OBJECT, list);
		} else {
			rezult.put(MESSAGE, MESSAGE_NOT_FOUND);
		}
		return rezult;
	}

	@RequestMapping(value = "/messageUser/{idUser}/dialog/{interlocutor}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, Object> getMessageDialog(
			@PathVariable("idUser") String idUser,
			@PathVariable("interlocutor") String interlocutor) {
		rezult = new HashMap<String, Object>();
		List<MessageUser> list = null;
		list = service.getMessageDialog(idUser, interlocutor);

		if (list != null) {
			rezult.put(OBJECT, list);
		} else {
			rezult.put(MESSAGE, MESSAGE_NOT_FOUND);
		}

		return rezult;

	}

}
