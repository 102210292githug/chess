package com.demo.AI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import com.demo.model.ServerStatus;
import com.mysql.cj.xdevapi.Client;

@SuppressWarnings("unused")
public class ServerAI {
    private static final int PORT = 8888;
    private static ConcurrentLinkedQueue<Connection> availablePythonServers = new ConcurrentLinkedQueue<>();
    private static ConcurrentHashMap<ClientConnection, ServerStatus> serverStatusMap = new ConcurrentHashMap<>();
    
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                String newServerName = socket.getInetAddress().getHostAddress();
                System.out.println("Connected to: " + newServerName);

                ClientConnection connection = new ClientConnection(socket);
                Connection newConn = new Connection(newServerName, connection);
                addAvailablePythonServer(newConn);
                // Check if a server with this name already exists
                ServerStatus existingServer = findServerByName(newServerName);
                if (existingServer != null) {
                    
                } else {
                    // Create and add new server status
                    ServerStatus newServer = new ServerStatus();
                    newServer.setServerName(newServerName);
                    newServer.setConnection(connection);
                    newServer.setGamePVE(0);
                    newServer.setGamePVP(0);
                    newServer.setStatus("connected");
                    serverStatusMap.put(connection, newServer);
                }
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Method to find a server by its name
    private static ServerStatus findServerByName(String serverName) {
        for (ServerStatus status : serverStatusMap.values()) {
            if (status.getServerName().equals(serverName)) {
            	status.setStatus("connected");
                return status;
            }
        }
        return null;
    }

    public static List<ServerStatus> getAllStatus() {
        return new ArrayList<>(serverStatusMap.values());
    }
    
    public static void updatePVE(ClientConnection connection, int value) {
        ServerStatus serverS = serverStatusMap.get(connection);
        if (serverS != null) {
            serverS.setGamePVE(serverS.getGamePVE() + value);
            serverStatusMap.put(connection, serverS);
        } else {
            System.out.println("Connection not found in serverStatusMap.");
        }
    }
    public static void updatePVP(ClientConnection connection, int value) {
        ServerStatus serverS = serverStatusMap.get(connection);
        if (serverS != null) {
            serverS.setGamePVP(serverS.getGamePVP() + value);
            serverStatusMap.put(connection, serverS);
        } else {
            System.out.println("Connection not found in serverStatusMap.");
        }
    }
    public static void updateStatus(ClientConnection connection, String status) {
        ServerStatus serverS = serverStatusMap.get(connection);
        if (serverS != null) {
            serverS.setStatus(status);
            serverStatusMap.put(connection, serverS);
        } else {
            System.out.println("Connection not found in serverStatusMap.");
        }
    }
    
    public static synchronized void addAvailablePythonServer(Connection connection) {
    	int cnt = availablePythonServers.size();
    	boolean f = false;
    	while(cnt != 0) {
    		Connection conn = availablePythonServers.poll();
    		if(conn.getName().equals(connection.getName())) {
    			ServerStatus serverS = serverStatusMap.get(conn.getConn());
    			serverStatusMap.remove(conn.getConn());
    			conn.setConn(connection.getConn());
    			serverS.setConnection(connection.getConn());
    			serverStatusMap.put(conn.getConn(), serverS);
    			f = true;
    		}
    		availablePythonServers.add(conn);
    		cnt--;
    	}
    	if(!f) availablePythonServers.add(connection);
        System.out.println("Python server added. Total available: " + availablePythonServers.size());
    }

   
    public static synchronized void removeAvailablePythonServer(ClientConnection connection) {
        int cnt = availablePythonServers.size();
    	while(cnt != 0) {
    		Connection conn = availablePythonServers.poll();
    		if(conn.getConn().equals(connection)) {
    			
    		}
    		else availablePythonServers.add(conn);
    		cnt--;
    	}
        System.out.println("Python server removed. Total available: " + availablePythonServers.size());
        ServerStatus serverS = serverStatusMap.get(connection);
        serverS.setStatus("disconnect");
    }

    
    public static synchronized ClientConnection getAvailablePythonServer() {
    	Connection connection = availablePythonServers.poll();
    	availablePythonServers.add(connection);
        return connection.getConn();
    }
    static class Connection{
    	String name = "";
    	ClientConnection conn;
    	void setName(String name) {
    		this.name = name;
    	}
    	String getName() {
    		return this.name;
    	}
    	void setConn(ClientConnection connection) {
    		this.conn = connection;
    	}
    	ClientConnection getConn() {
    		return this.conn;
    	}
    	public Connection(String name, ClientConnection conn) {
    		this.name =name;
    		this.conn = conn;
    	}
    }
	public static boolean disconnectTheServer(String serverName) throws IOException {
		Connection serverDis = null, serverPull = null;
		if(availablePythonServers.size() > 1) {
			System.err.println("disconnectTheServer" + " true");
			int cnt = availablePythonServers.size();
			while(cnt != 0) {
				Connection topCon = availablePythonServers.poll();
				if(topCon.getName().equals(serverName)) {
					serverDis = topCon;
				}
				else if(serverPull == null) {
					serverPull = topCon;
				}
				availablePythonServers.add(topCon);
				cnt--;
			}
			RoomManager.PullGame(serverDis.getConn(), serverPull.getConn());
			return true;
		}
		System.err.println("disconnectTheServer" + " false");
		return false;
	}
}
