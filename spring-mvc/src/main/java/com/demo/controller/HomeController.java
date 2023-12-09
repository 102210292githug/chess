package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.AI.HandlerMessage;
import com.demo.AI.ServerAI;
import com.demo.dao.GameDAO;
import com.demo.dao.MoveDAO;
import com.demo.dao.UserDAO;
import com.demo.model.Game;
import com.demo.model.Move;
import com.demo.model.ServerStatus;
import com.demo.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
	UserDAO userDAO = new UserDAO();
	MoveDAO moveDAO = new MoveDAO();
	GameDAO gameDAO = new GameDAO();
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView infomation() {
		return new ModelAndView("infomation");
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage(HttpServletRequest request) {
		HttpSession session = request.getSession(false); // Get the current session if it exists, but don't create a new one
		ModelAndView mav;
		if (session != null && session.getAttribute("userID") != null) {
			mav = new ModelAndView("homeLogged");
		} else {
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
	
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView managerPage(@RequestParam(required = false) String action, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userID") == null) {
			return new ModelAndView("redirect:/login");
		}
		User user = userDAO.getUserByID(Integer.parseInt(session.getAttribute("userID").toString()));
		if(!user.getUsername().equals("admin")) {
			return new ModelAndView("redirect:/home");
		}
			
		ModelAndView mav = new ModelAndView("managerPage");
		List<ServerStatus> listStatus = ServerAI.getAllStatus();
		Map<String, Integer> pvpCounts = new HashMap<>();
		Map<String, Integer> pveCounts = new HashMap<>();
		for (ServerStatus status : listStatus) {
		    pvpCounts.put(status.getServerName(), status.getGamePVP());
		    pveCounts.put(status.getServerName(), status.getGamePVE());
		}

		mav.addObject("pvpCounts", pvpCounts);
		mav.addObject("pveCounts", pveCounts);
		mav.addObject("listStatus", listStatus);
		return mav;
	}
	
	@RequestMapping(value = "/getServerData", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getServerData() {
	    List<ServerStatus> listStatus = ServerAI.getAllStatus();
	    Map<String, Integer> pvpCounts = new HashMap<>();
	    Map<String, Integer> pveCounts = new HashMap<>();
	    
	    for (ServerStatus status : listStatus) {
	        pvpCounts.put(status.getServerName(), status.getGamePVP());
	        pveCounts.put(status.getServerName(), status.getGamePVE());
	    }

	    Map<String, Object> responseData = new HashMap<>();
	    responseData.put("listStatus", listStatus);
	    responseData.put("pvpCounts", pvpCounts);
	    responseData.put("pveCounts", pveCounts);

	    return responseData;
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
		// Nếu không có trận nào chưa hoàn thành
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
			return mav;

		}
		
		// Note: Đây chỉ là các trận đấu với máy thôi, các trận đấu với người việc rời khỏi hoặc reload trang thi đấu đồng nghĩa với bỏ cuộc
		// Nếu có trận đấu chưa hoàn thành 
		// B1: Lấy move từ database:
		List<Move> prevMove = moveDAO.getMovesByGameId(gameID);
		// B2: Kiểm tra xem ván đấu có tồn tại trong các serverAI hiện tại hay không
		if(HandlerMessage.Check(gameID)) {
			// nếu có không cần làm gì
			System.err.println("có game id");
		}
		else {
			// Nếu không có :
			System.err.println("không có gameid");
			Game game = gameDAO.getGame(gameID);
			HandlerMessage.PushGameIN_PROGRESS(gameID, game.getPlayer1ID(), game.getPlayer2ID(), prevMove.toString());
		}
		// B3: Truyền các Move lên lại cho bàn cờ mới 
		mav.addObject("prevMove", prevMove);
		// B4: Get legal:
		String legal = HandlerMessage.getLegal(gameID);
		mav.addObject("legal", legal);
		return mav;
	}


}
