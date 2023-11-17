package com.demo.service;

import org.springframework.stereotype.Service;

import com.demo.dao.UserDAO;
import com.demo.model.User;

@Service
public class UserService {
	UserDAO userDAO = new UserDAO();
    public int checkLogin(String username, String password) {
    	int userID = userDAO.checkLogin(username, password).getUserID();
    	return  userID;
    }
    
    public boolean createUser(User user) {
    	return userDAO.addUser(user);
    }
    
    public User getUser(int userID) {
    	return userDAO.getUserByID(userID);
    }
    
    
}
