//package com.demo.controller;
//
//import javax.websocket.OnClose;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.ServerEndpoint;
//
//import com.demo.AI.ServerAI;
//
//import java.io.IOException;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@ServerEndpoint("/websocket")
//public class MyWebSocket {
//
//    // Khởi tạo thread pool
//    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);
//
//    private static Map<String, Session> waitingPlayers = new ConcurrentHashMap<>();
//    private static Map<String, ChessRoom> activeRooms = new ConcurrentHashMap<>();
//    private static Map<String, String> activeRoomComputers = new ConcurrentHashMap<>();
//
//    @OnOpen
//    public void onOpen(Session session) {
//        System.out.println("New connection with client: " + session.getId());
//    }
//
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        // Đẩy việc xử lý message vào thread pool
//        executorService.submit(() -> {
//            try {
//                processMessage(message, session);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    private void processMessage(String message, Session session) throws IOException {
//        System.out.println("Message from client " + session.getId() + ": " + message);
//
//        if ("play".equals(message)) {
//            if (waitingPlayers.isEmpty() || waitingPlayers.containsKey(session.getId())) {
//                if (!activeRooms.containsKey(session.getId())) {
//                    waitingPlayers.put(session.getId(), session);
//                }
//            } else {
//                String opponentId = waitingPlayers.keySet().iterator().next();
//                Session opponentSession = waitingPlayers.remove(opponentId);
//
//                ChessRoom room = new ChessRoom(session, opponentSession);
//                activeRooms.put(session.getId(), room);
//                activeRooms.put(opponentSession.getId(), room);
//            }
//        } else if("playComputer".equals(message)){
//        	String roomID = ServerAI.CreateNewRoom("playComputer", session.getId(), "computer");
//        	activeRoomComputers.put(session.getId(), roomID);
//        	System.out.println("rooms coputer size : " + activeRoomComputers.size() + " " +  roomID);
//        }
//        else{
//            ChessRoom room = activeRooms.get(session.getId());
//            if (room != null) {
//                if (!room.forwardMove(session, message)) {
//                    activeRooms.remove(room.player1.getId());
//                    activeRooms.remove(room.player2.getId());
//                }
//            }
//            else {
//            	System.out.println("here");
//            	String roomId = activeRoomComputers.get(session.getId());
//            	System.out.println(roomId);
//            	if(roomId != null) {
//            		String res = ServerAI.sendMove(roomId, message);
//            		ParsedData parsed = parseData(res);
//            		System.out.println(parsed.toString());
//            		session.getBasicRemote().sendText(parsed.toString());
//            	}
//            }
//        }
//    }
//
//    public static ParsedData parseData(String input) {
//        Map<String, List<String>> legalMoves = new HashMap<>();
//
//        // Tách chuỗi để lấy thông tin computer moved
//        Pattern movePattern = Pattern.compile("Computer moved: (.*?),");
//        Matcher moveMatcher = movePattern.matcher(input);
//        String computer = moveMatcher.find() ? moveMatcher.group(1) : null;
//
//        // Tách chuỗi để lấy thông tin legal moves
//        Pattern legalPattern = Pattern.compile("'([A-Z]@)(\\w+)': \\[([^]]+)\\]");
//        Matcher legalMatcher = legalPattern.matcher(input);
//        while (legalMatcher.find()) {
//            String position = legalMatcher.group(2);
//            String[] moves = legalMatcher.group(3).replace("'", "").split(", ");
//            for (int i = 0; i < moves.length; i++) {
//                moves[i] = moves[i].substring(moves[i].length() - 2);
//            }
//            legalMoves.put(position, Arrays.asList(moves));
//        }
//
//        return new ParsedData(computer, legalMoves);
//    }
//
//    static class ParsedData {
//        public final String computer;
//        public final Map<String, List<String>> legalMoves;
//
//        public ParsedData(String computer, Map<String, List<String>> legalMoves) {
//            this.computer = computer;
//            this.legalMoves = legalMoves;
//        }
//
//        @Override
//        public String toString() {
//            return computer + " AND " + legalMoves;
//        }
//
//    }
//    @OnClose
//    public void onClose(Session session) {
//        System.out.println("Closed connection with client: " + session.getId());
//        waitingPlayers.remove(session.getId());
//        activeRooms.remove(session.getId());
//    }
//}
