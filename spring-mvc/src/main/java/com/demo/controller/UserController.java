package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.User;
import com.demo.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletRequest request) {

		int userID = userService.checkLogin(username, password);

		if (userID != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("userID", userID);
			return new ModelAndView("redirect:" + "/home");
		} else {
			ModelAndView modelAndView = new ModelAndView("login");
			modelAndView.addObject("error", "Invalid username or password");
			modelAndView.addObject("username", username);
			return modelAndView;
		}
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signup(@RequestParam("username") String username, 
								@RequestParam("password") String password,
								@RequestParam("email") String email,
								HttpServletRequest request) {
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setEmail(email);
		System.err.println(username + password + email);
		boolean isCreated = userService.createUser(newUser);
		System.err.println(isCreated);
		if (isCreated) {
			// Redirect to the login page after successful registration
			return new ModelAndView("redirect:/login");
		} 
		
		ModelAndView modelAndView = new ModelAndView("signup");
		modelAndView.addObject("error", "Signup failed. Please try again.");
		return modelAndView;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); // Invalidate the session
		}
		return new ModelAndView("redirect:/login");
	}
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public ModelAndView updateProfile(@RequestParam("firstname") String firstname, 
									  @RequestParam("lastname") String lastname, 
									  @RequestParam("location") String location,
									  @RequestParam("userID") int userID,
									  HttpServletRequest request) {
		userService.updateUser1(lastname, firstname, location, userID);
		return new ModelAndView("redirect: /spring-mvc/profile");
	}
	
	@RequestMapping(value = "/findFriend", method = RequestMethod.GET)
	public ModelAndView findFriend(@RequestParam("friendID") String friendID,
	                               HttpServletRequest request) {
	    User user1 = userService.getUser(friendID);

	    // Assuming "user" is the object you want to pass to the redirected URL
	    ModelAndView modelAndView = new ModelAndView("findFriend");
	    modelAndView.addObject("user", user1);

	    return modelAndView;
	}
}
