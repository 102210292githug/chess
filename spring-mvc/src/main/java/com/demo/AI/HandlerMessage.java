package com.demo.AI;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;


import com.demo.controller.WebSocket;
import com.demo.dao.MoveDAO;

import com.demo.model.Move;

public class HandlerMessage {
	private static MoveDAO moveDAO = new MoveDAO();
    private static RoomManager roomManager = new RoomManager();
    private static Queue<Integer> waitList = new LinkedList<>();
    private static Map<Integer, Integer> userGameMap = new ConcurrentHashMap<>();

    public static void MessageHandler(int userID, String message) {
    	System.err.println("MessHandler");
        // Phân tách message để xác định loại yêu cầu
        String[] parts = message.split(" ");
        String command = parts[0];

        switch (command) {
            case "create":
                handleCreate(userID, parts[1]);
                break;
            case "move":
                handleMove(userID, parts[1]);
                break;
            case "draw":
                handleDrawRequest(userID);
                break;
            case "resign":
                handleResign(userID);
                break;
            case "analyze":
                handleAnalyze(userID);
                break;
            default:
                System.out.println("Unrecognized command: " + command);
        }
    }

    public static int handleCreate(int userID, String type) {
    	int gameID = -1;
        if ("0".equals(type)) {
            // Tạo phòng chơi với máy
        	gameID = roomManager.createRoomWithAI(userID);
            userGameMap.put(userID, gameID);
        } else if ("1".equals(type)) {
            // Kiểm tra và tạo phòng chơi với người chơi khác
        	
        	if(waitList.size() != 0) {
        		int userID_oth = waitList.poll();
        		gameID = roomManager.createRoomWithPlayer(userID, userID_oth);
        		userGameMap.put(userID, gameID);
        		userGameMap.put(userID_oth, gameID);
        	}
        	else {
        		waitList.add(userID);
        	}
        } else if("2".equals(type)) {
        	// chơi với bạn
        }
       return gameID;
    }
    
    public static String handleGET(int gameID) {
    	String board = roomManager.getBoard(gameID);
    	
    	
    	
    	return board;
    }
    
    private static void handleMove(int userID, String move) {
        // Xử lý nước đi
    	
    	int gameID = userGameMap.get(userID);
    	Move newMove = new Move(gameID, userID, move);
        moveDAO.addMove(newMove);
        String move_oth =  roomManager.sendMove(gameID, move, userID);
        System.err.println(move_oth);
        WebSocket.sendMessageToUser(userID, move_oth);
    }

    private static void handleDrawRequest(int userID) {
        // Xử lý yêu cầu cầu hòa
    	int gameID = userGameMap.get(userID);
        roomManager.requestDraw(gameID, userID);
    }

    private static void handleResign(int userID) {
        // Xử lý đầu hàng
    	int gameID = userGameMap.get(userID);
        roomManager.resignGame(gameID, userID);
    }

    private static void handleAnalyze(int gameID) {
        // Phân tích trận đấu
        roomManager.analyzeGame(gameID);
    }
}
