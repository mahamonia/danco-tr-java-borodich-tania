package com.roditeli.restservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roditeli.api.service.IAuthenticationService;
import com.roditeli.api.service.IUserProfileService;
import com.roditeli.api.service.IUserService;
import com.roditeli.model.Authentication;
import com.roditeli.model.Theme;
import com.roditeli.model.User;
import com.roditeli.model.UserProfile;

@Controller
public class UserRestService {

	private IUserService userService;

	private IAuthenticationService authenService;

	private IUserProfileService userProfService;

	@Required
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Required
	@Autowired
	public void setAuthenService(IAuthenticationService authenService) {
		this.authenService = authenService;
	}

	@Required
	@Autowired
	public void setUserProfService(IUserProfileService userProfService) {
		this.userProfService = userProfService;
	}

	@RequestMapping(value = "/login/{login}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Authentication getAuthenticationUser(
			@PathVariable("login") String login) {
		System.out.println("auth");
		Authentication authenticationUser = authenService.getByLogin(login);
		return authenticationUser;
	}

	@RequestMapping(value = "/user/{login}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody User getUser(@PathVariable("login") String login) {
		User user = userService.getUser(login);
		UserProfile userProfile = userProfService.getUserProfileByUser(user.getId());
		user.setProfile(userProfile);
		return user;

	}
	
	@RequestMapping(value = "/user/themes/{userId}/{rubriId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Theme> getThemeRubricUser(@PathVariable("userId") int userId, @PathVariable("rubricId") int rubricId) {
		List<Theme> list = null;
		list = userService.getThemeRubricUser(userId, rubricId);
		return list;
	}
}
