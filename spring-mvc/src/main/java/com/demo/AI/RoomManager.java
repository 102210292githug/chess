package com.demo.AI;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import com.demo.dao.GameDAO;
import com.demo.dao.MoveDAO;
import com.demo.model.Game;
import com.demo.model.Move;
import com.demo.model.Game.GameOutcome;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RoomManager {
	private ConcurrentHashMap<Integer, ClientConnection> activeGames = new ConcurrentHashMap<>();
	private ConcurrentHashMap<Integer, Game> games = new ConcurrentHashMap<>();
	private GameDAO gameDAO = new GameDAO();
	private static MoveDAO moveDAO = new MoveDAO();

	
	
	public int createGame(int mode, int player1ID, int player2ID) throws IOException {
		Game newGame = new Game();
		newGame.setAnalyzed(false);
		newGame.setOutcome(GameOutcome.IN_PROGRESS);
		newGame.setType(mode);
		newGame.setPlayer1ID(player1ID);
		newGame.setPlayer2ID(player2ID);
		newGame.setStatus(0);
		int gameId = gameDAO.addGame(newGame);
	
		
		ClientConnection connection = ServerAI.getAvailablePythonServer();
		if (connection != null) {
			connection.sendMessage("createGame mode=" + mode + " " + player1ID + " " + player2ID + " " + gameId);
			activeGames.put(gameId, connection);
			games.put(gameId, newGame);
		}
		
		return gameId;
	}

	public String sendMove(int gameId, String move, int userID) {
		
		ClientConnection connection = activeGames.get(gameId);
		if (connection != null) {
			try {
				String res = connection.sendMessage("move gameId=" + gameId + " move=" + move);
				System.err.println(res);
				ParsedData parse = parseData(res, gameId);
				if(parse.isE()) {
					Game game = gameDAO.getGame(gameId);
					game.setStatus(1);
					if(userID == game.getPlayer2ID()) {
						game.setOutcome(Game.GameOutcome.WIN);
					}
					else {
						game.setOutcome(Game.GameOutcome.LOSE);
					}
					gameDAO.updateGame(game);
				}
				res = parse.toString();
				System.err.println(res);
				return res;
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		return null;
	}

	public void removeGame(int gameId, GameOutcome outcome) {
		Game game = games.get(gameId);
		if (game != null) {
			game.setStatus(1); // Assuming 1 means completed
			game.setOutcome(outcome);
			gameDAO.updateGame(game); // Update game in the database
			activeGames.remove(gameId);
			games.remove(gameId);
		}
	}

	public Game getGame(int gameId) {
		return games.get(gameId);
	}

	public ClientConnection getActiveGame(int gameId) {
		return activeGames.get(gameId);
	}

	public int createRoomWithAI(int userID) {
		// TODO Auto-generated method stub
		try {
			return createGame(0, userID, 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public int createRoomWithPlayer(int userID, int userID_oth) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			return createGame(0, userID, userID_oth);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public void requestDraw(int gameID, int userID) {
		// TODO Auto-generated method stub

	}

	public void resignGame(int gameID, int userID) {
		// TODO Auto-generated method stub

	}

	public void analyzeGame(int gameID) {
		// TODO Auto-generated method stub

	}

	public static ParsedData parseData(String input, int gameId) {
		Map<String, List<String>> legalMoves = new HashMap<>();

		// Tách chuỗi để lấy thông tin computer moved
		Pattern movePattern = Pattern.compile("Computer moved: (.*?),");
		Matcher moveMatcher = movePattern.matcher(input);
		String computer = moveMatcher.find() ? moveMatcher.group(1) : null;
		Move newMove = new Move(gameId, 0, computer);
		System.err.println("computer " + newMove);
        moveDAO.addMove(newMove);
		// Tách chuỗi để lấy thông tin legal moves
		Pattern legalPattern = Pattern.compile("'([A-Z]@)(\\w+)': \\[([^]]+)\\]");
		Matcher legalMatcher = legalPattern.matcher(input);
		while (legalMatcher.find()) {
			String position = legalMatcher.group(2);
			String[] moves = legalMatcher.group(3).replace("'", "").split(", ");
			for (int i = 0; i < moves.length; i++) {
				moves[i] = moves[i].substring(moves[i].length() - 2);
			}
			legalMoves.put(position, Arrays.asList(moves));
		}

		return new ParsedData(computer, legalMoves);
	}

	static class ParsedData {
		public final String computer;
		public final Map<String, List<String>> legalMoves;

		public ParsedData(String computer, Map<String, List<String>> legalMoves) {
			this.computer = computer;
			this.legalMoves = legalMoves;
		}
		public boolean isE() {
			return legalMoves.isEmpty();
		}
		@Override
		public String toString() {
			if(!isE()) return computer + " AND " + legalMoves;
			return computer +  " AND YOU LOSE";
		}

	}

	public String getBoard(int gameID) {
		// TODO Auto-generated method stub
		ClientConnection connection = activeGames.get(gameID);
		try {
			return connection.sendMessage("get " + gameID);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
