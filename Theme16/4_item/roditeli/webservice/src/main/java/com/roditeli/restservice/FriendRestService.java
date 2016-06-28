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

import com.roditeli.api.service.IAuthenticationService;
import com.roditeli.api.service.IUserProfileService;
import com.roditeli.api.service.IUserService;
import com.roditeli.model.Authentication;
import com.roditeli.model.User;
import com.roditeli.model.UserProfile;

@Controller
public class FriendRestService {

	@Autowired
	private IUserService userService;

	private Map<String, Object> rezult;

	private static final String OBJECT = "object";
	private static final String MESSAGE = "message";
	private static final String USER_NOT_FOUND = "User not found";
	private static final String SERVER_ERROR = "Server error";
	private static final String SUCCESSFULLY = "Successfully";

	@RequestMapping(value = "friend/amountNewFriends/{userId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, Object> getAmountNewFriends(
			@PathVariable("userId") int userId) {

		rezult = new HashMap<String, Object>();

		rezult.put(OBJECT, userService.amountNewFriends(userId));

		return rezult;
	}

	@RequestMapping(value = "/friendsUser/{login}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, Object> getFriendsCurrentUser(
			@PathVariable("userId") int userId) {

		rezult = new HashMap<String, Object>();
		List<User> list = userService.getFriendsList(userId);

		if (list != null) {
			rezult.put(OBJECT, list);
		} else {
			rezult.put(MESSAGE, USER_NOT_FOUND);
		}
		return rezult;
	}

	@RequestMapping(value = "friend/delete/{userId}/{friendId}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody Map<String, Object> deleteFriend(
			@PathVariable("userId") int userId,
			@PathVariable("friendId") int friendId) {

		rezult = new HashMap<String, Object>();

		if (userService.deleteFriend(userId, friendId)) {
			rezult.put(MESSAGE, SUCCESSFULLY);
		} else {
			rezult.put(MESSAGE, SERVER_ERROR);
		}
		return rezult;
	}

	@RequestMapping(value = "/friend/add/{userId}/{friendId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, Object> addFriend(
			@PathVariable("userId") int userId,
			@PathVariable("friendId") int friendId) {

		rezult = new HashMap<String, Object>();

		if (userService.addFriend(userId, friendId)) {
			rezult.put(MESSAGE, SUCCESSFULLY);
		} else {
			rezult.put(MESSAGE, SERVER_ERROR);
		}
		return rezult;
	}

}
