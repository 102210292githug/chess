package com.demo.controller;

import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;

public class ServerAI {
	private static ConcurrentLinkedQueue<ClientConnection> availablePythonServers = new ConcurrentLinkedQueue<>();
	private static ConcurrentHashMap<String, ClientConnection> activeGames = new ConcurrentHashMap<>();

	public static void addAvailablePythonServer(ClientConnection connection) {
		availablePythonServers.add(connection);
		System.out.println("client AI cnt = " + availablePythonServers.size());
	}

	public static void removeAvailablePythonServer(ClientConnection connection) {
		availablePythonServers.remove(connection);
		activeGames.entrySet().removeIf(entry -> entry.getValue() == connection);
		System.out.println("client AI cnt = " + availablePythonServers.size());
	}

	public static void main(String[] args) {
		int port = 8888;

		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Server is listening on port " + port);

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Connected to: " + socket.getInetAddress().getHostAddress());

				new Thread(new ClientHandler(socket)).start();
			}
		} catch (IOException ex) {
			System.out.println("Server exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public static String handleCreateRoomMessage(String message, ClientConnection connection) throws IOException {
		System.out.println(message);
		String[] players = message.split(" and ");
		String player1ID = players[0].trim();
		String player2ID = players[1].trim();

		if ("computer".equals(player2ID)) {
			connection.getOutput().println(player1ID + " and computer");
		} else {
			connection.getOutput().println(player1ID + " and " + player2ID);
		}

		String roomId = waitForMessageFromQueue(connection.getMessageQueue());
		activeGames.put(roomId, connection);
		return roomId;
	}

	public static String handleMoveMessage(String message, ClientConnection connection) throws IOException {
		String[] parts = message.split(" move: ");
		String gameId = parts[0].trim();
		String move = parts[1].trim();

		connection = activeGames.get(gameId);
		if (connection != null) {
			connection.getOutput().println(gameId + " move: " + move);
		} else {
			System.out.println("No active game found for ID: " + gameId);
		}

		String allLegalMove = waitForMessageFromQueue(connection.getMessageQueue());
		return allLegalMove;
	}

	private static String waitForMessageFromQueue(ConcurrentLinkedQueue<String> queue) throws IOException {
		while (true) {
			String message = queue.poll();
			if (message != null) {
				return message;
			}

			// Consider adding a sleep here to prevent busy-waiting
			try {
				Thread.sleep(50); // Sleep for 50ms before checking again
			} catch (InterruptedException e) {
				throw new IOException("Waiting for message was interrupted", e);
			}
		}
	}

	public static String CreateNewRoom(String GameMode, String play1ID, String play2ID) throws IOException {
		if (availablePythonServers.isEmpty()) {
			System.out.println("No available Python server to handle the request.");
			return null;
		}

		ClientConnection connection = availablePythonServers.poll();
		availablePythonServers.add(connection);
		String roomId = null;
		if ("playOnline".equals(GameMode)) {
			roomId = handleCreateRoomMessage(play1ID + " and " + play2ID, connection);
			activeGames.put(roomId, connection);
			return roomId;
		} else if ("playComputer".equals(GameMode)) {
			roomId = handleCreateRoomMessage(play1ID + " and computer", connection);
			activeGames.put(roomId, connection);
			return roomId;
		}

		return roomId;
	}

	public static String sendMove(String gameId, String move) throws IOException {
		ClientConnection connection = activeGames.get(gameId);
		String AllLegalMove = null;
		if (connection != null) {
			AllLegalMove = handleMoveMessage(gameId + " move: " + move, connection);
		} else {
			System.out.println("No active game found for ID: " + gameId);
		}
		return AllLegalMove;
	}

}

class ClientConnection {
	private final PrintWriter output;
	private final BufferedReader input;
	private final ConcurrentLinkedQueue<String> messageQueue = new ConcurrentLinkedQueue<>();

	public ClientConnection(Socket socket) throws IOException {
		this.output = new PrintWriter(socket.getOutputStream(), true);
		this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	public PrintWriter getOutput() {
		return output;
	}

	public BufferedReader getInput() {
		return input;
	}

	public PrintWriter getOutputStream() {
		return output;
	}

	public BufferedReader getInputStream() {
		return input;
	}

	public ConcurrentLinkedQueue<String> getMessageQueue() {
		return messageQueue;
	}
}

class ClientHandler implements Runnable {
	private final Socket socket;
	private final ClientConnection connection;

	public ClientHandler(Socket socket) throws IOException {
		this.socket = socket;
		this.connection = new ClientConnection(socket);
		ServerAI.addAvailablePythonServer(connection);
	}

	@Override
	public void run() {
		try {
			String incomingMessage;
			while ((incomingMessage = connection.getInputStream().readLine()) != null) {
				connection.getMessageQueue().add(incomingMessage);
			}
		} catch (IOException e) {
			System.out.println("Server exception: " + e.getMessage());
		} finally {
			ServerAI.removeAvailablePythonServer(connection);
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
