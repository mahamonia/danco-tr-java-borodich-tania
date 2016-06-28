package com.roditeli.restservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roditeli.api.service.IAuthenticationService;
import com.roditeli.api.service.IUserProfileService;
import com.roditeli.api.service.IUserService;
import com.roditeli.model.Authentication;
import com.roditeli.model.User;
import com.roditeli.model.UserProfile;

@Controller
public class UserRestService {
	@Autowired
	private IUserService userService;
	@Autowired
	private IAuthenticationService authenService;
	@Autowired
	private IUserProfileService userProfService;

	private Map<String, Object> rezult;

	private static final String OBJECT = "object";
	private static final String MESSAGE = "message";
	private static final String USER_NOT_FOUND = "User not found";
	private static final String NOT_AUTHORIZED = "User is not authorized";
	private static final String SERVER_ERROR = "Server error";

	@RequestMapping(value = "/login/{login}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, Object> getAuthentication(
			@PathVariable("login") String login) {

		rezult = new HashMap<String, Object>();
		Authentication authenticationUser = authenService.getByLogin(login);

		if (authenticationUser != null) {
			rezult.put(OBJECT, authenticationUser);
		} else {
			rezult.put(MESSAGE, USER_NOT_FOUND);
		}
		return rezult;
	}

	@RequestMapping(value = "/logOut/{login}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, Object> getLogOut(
			@PathVariable("login") String login) {

		rezult = new HashMap<String, Object>();
		User user = userService.logOut(login);

		if (user != null) {
			rezult.put(OBJECT, user);
		} else {
			rezult.put(MESSAGE, USER_NOT_FOUND);
		}
		return rezult;
	}

	@RequestMapping(value = "/user/{login}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, Object> getAuthenticationUser(
			@PathVariable("login") String login) {

		rezult = new HashMap<String, Object>();
		User user = userService.getUser(login);

		if (user != null) {
			UserProfile userProfile = userProfService.getUserProfileByUser(user
					.getId());
			user.setProfile(userProfile);
			rezult.put(OBJECT, user);
		} else {
			rezult.put(MESSAGE, USER_NOT_FOUND);
		}
		return rezult;
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, Object> createUser(
			@RequestBody User newUser) {

		rezult = new HashMap<String, Object>();

		if (userService.create(newUser)) {
			rezult.put(OBJECT, newUser);
		} else {
			rezult.put(MESSAGE, SERVER_ERROR);
		}
		return rezult;
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody Map<String, Object> updateUser(
			@RequestBody User newUser) {

		rezult = new HashMap<String, Object>();

		if (userService.update(newUser)) {
			rezult.put(OBJECT, newUser);
		} else {
			rezult.put(MESSAGE, SERVER_ERROR);
		}
		return rezult;
	}

	@RequestMapping(value = "/userProfile/{login}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, Object> getUserProfile(
			@PathVariable("login") String login) {
		rezult = new HashMap<String, Object>();
		User user = userService.getUser(login);

		if (user != null) {
			UserProfile userProfile = userProfService.getUserProfileByUser(user
					.getId());
			user.setProfile(userProfile);
			rezult.put(OBJECT, user);
		} else {
			rezult.put(OBJECT, NOT_AUTHORIZED);
		}
		return rezult;
	}

}
