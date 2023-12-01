package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demo.AI.HandlerMessage;
import com.demo.dao.MoveDAO;
import com.demo.dao.UserDAO;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
	UserDAO userDAO = new UserDAO();
	MoveDAO moveDAO = new MoveDAO();

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage(HttpServletRequest request) {
		HttpSession session = request.getSession(false); // Get the current session if it exists, but don't create a new
															// one
		ModelAndView mav;
		System.err.println(session.getAttribute("userID"));
		if (session != null && session.getAttribute("userID") != null) {
			// User is logged in, so show home1
			System.err.println("This connect is logged");
			mav = new ModelAndView("homeLogged");
		} else {
			// No user session, show home2
			System.err.println("This connect is not logged");
			mav = new ModelAndView("home");
		}

		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public ModelAndView historyPage(@RequestParam(required = false) String action, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userID") == null) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("history");
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signupPage() {
		return new ModelAndView("signup");
	}

	@RequestMapping(value = "/play", method = RequestMethod.GET)
	public ModelAndView play(@RequestParam(required = false) String action, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userID") == null) {
			return new ModelAndView("redirect:/login");
		}

		ModelAndView mav = new ModelAndView("play");
		int userID = Integer.parseInt(session.getAttribute("userID").toString());

		int gameID = userDAO.checkGameIN_PROGRESS(userID);
		// nếu k có trận nào chưa hoàn thành
		if (gameID == -1) {
			if ("online".equals(action)) {
				System.err.println("online");
				gameID = HandlerMessage.handleCreate(userID, "1");
				// Handle Play Online
			} else if ("computer".equals(action)) {
				System.err.println("computer");
				// Handle Play Computer
				gameID = HandlerMessage.handleCreate(userID, "0");
			} else if ("friend".equals(action)) {
				System.err.println("friend");
				// Handle Play with Friend
				gameID = HandlerMessage.handleCreate(userID, "1");
			}

		}
		String chessboard = HandlerMessage.handleGET(gameID);
		mav.addObject("chessboard", chessboard);
		return mav;
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView infomation() {
		return new ModelAndView("infomation");
	}
}
