package com.demo.AI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;


public class ServerAI {
    private static final int PORT = 8888;
    private static ConcurrentLinkedQueue<ClientConnection> availablePythonServers = new ConcurrentLinkedQueue<>();

    /**
     * Main method to start the server.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connected to: " + socket.getInetAddress().getHostAddress());

                ClientConnection connection = new ClientConnection(socket);
                addAvailablePythonServer(connection);
                
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Adds a Python server (client connection) to the available list.
     * @param connection The client connection to add.
     */
    public static synchronized void addAvailablePythonServer(ClientConnection connection) {
        availablePythonServers.add(connection);
        System.out.println("Python server added. Total available: " + availablePythonServers.size());
    }

    /**
     * Removes a Python server (client connection) from the available list.
     * @param connection The client connection to remove.
     */
    public static synchronized void removeAvailablePythonServer(ClientConnection connection) {
        availablePythonServers.remove(connection);
        System.out.println("Python server removed. Total available: " + availablePythonServers.size());
    }

    /**
     * Gets an available Python server (client connection) from the queue.
     * @return An available client connection or null if none are available.
     */
    public static synchronized ClientConnection getAvailablePythonServer() {
    	ClientConnection connection = availablePythonServers.poll();
    	availablePythonServers.add(connection);
        return connection;
    }
}
