package com.demo.dao;

import com.demo.model.Move;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoveDAO {

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/chess", "root", "");
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    // 1. Get Moves by Game ID
    public List<Move> getMovesByGameId(int gameId) {
        List<Move> moves = new ArrayList<>();
        String sql = "SELECT * FROM moves WHERE gameID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, gameId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Move move = new Move();
                    move.setMoveID(rs.getInt("moveID"));
                    move.setGameID(rs.getInt("gameID"));
                    move.setPlayerID(rs.getInt("playerID"));
                    move.setMoveNotation(rs.getString("moveNotation"));
                    move.setMoveNumber(rs.getInt("moveNumber"));
                    move.setMoveQuality(Move.MoveQuality.valueOf(rs.getString("moveQuality")));
                    move.setBetterMove(rs.getString("betterMove"));
                    moves.add(move);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving moves by game ID", e);
        }
        return moves;
    }

    // 2. Add a Move
    public void addMove(Move move) {
    	
        String sql = "INSERT INTO moves (gameID, playerID, moveNotation, moveNumber, moveQuality, betterMove) VALUES (?, ?, ?, ?, ?, ?)";
        System.err.println(move);
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	//System.err.println(move);
            stmt.setInt(1, move.getGameID());
            //System.err.println("1");
            stmt.setInt(2, move.getPlayerID());
            //System.err.println("2");
            stmt.setString(3, move.getMoveNotation());
            //System.err.println("3");
            stmt.setInt(4, move.getMoveNumber());
            //System.err.println("4");
            //System.err.println(move.getMoveQuality().toString());
            stmt.setString(5, move.getMoveQuality().toString());
            //System.err.println("5");
            stmt.setString(6, move.getBetterMove());
            //System.err.println("6");
            stmt.executeUpdate();
          System.err.println("addmove done");
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new RuntimeException("Error adding move", e);
        }
    }

    // 3. Update a Move
    public void updateMove(Move move) {
        String sql = "UPDATE moves SET gameID = ?, playerID = ?, moveNotation = ?, moveNumber = ?, moveQuality = ?, betterMove = ? WHERE moveID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, move.getGameID());
            stmt.setInt(2, move.getPlayerID());
            stmt.setString(3, move.getMoveNotation());
            stmt.setInt(4, move.getMoveNumber());
            stmt.setString(5, move.getMoveQuality().toString());
            stmt.setString(6, move.getBetterMove());
            stmt.setInt(7, move.getMoveID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating move", e);
        }
    }

    // 4. Delete a Move
    public void deleteMove(int moveID) {
        String sql = "DELETE FROM moves WHERE moveID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, moveID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting move", e);
        }
    }
}
