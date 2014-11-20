package javaserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	
	Connection conn = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	
	
	public DB(){
		DBconn dbconn = new DBconn();
		conn = dbconn.conn();
	}
	
	
	private void closeSelect(){
		if (rs != null) {
	        try {rs.close();} 
	        catch (SQLException sqlEx) { }
	        rs = null;
	    }
	    if (stmt != null) {
	        try {stmt.close();} 
	        catch (SQLException sqlEx) { }
	        stmt = null;
	    }
	}
	
	private int getId(PreparedStatement stmt){
		int rid = 0;
		try{
			ResultSet id = stmt.getGeneratedKeys();
			if(id.next()){
				rid = id.getInt(1);
			}
			return rid;
		}
		catch (SQLException ex)
		{
			sqlError(ex);
			return rid;
		}
	}
	
	public void sqlError(SQLException ex){
		System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}

	//Tabelle Player
	public int insertPlayer(String name, String token, int gameId){
		try{
		  String query = "INSERT INTO Player (name, token, gameId) values (?, ?, ?)";
		 
	      PreparedStatement prep_stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	      prep_stmt.setString (1, name);
	      prep_stmt.setString (2, token);
	      prep_stmt.setInt (3, gameId);
	      //preparedStmt.setDate   (3, startDate);
	      //preparedStmt.setBoolean(4, false);
	      //preparedStmt.setInt    (5, 5000);
		 
	      prep_stmt.execute();
	      return getId(prep_stmt);
	      
	    }
	    catch (SQLException ex)
	    {
	      sqlError(ex);
	      return 0;
	    }
	}

	public ResultSet getPlayer(int uid){
	    
		try {
			String query = "SELECT * FROM Player WHERE uid = ?";
			
			PreparedStatement prep_stmt = conn.prepareStatement(query);
			prep_stmt.setInt(1, uid);
			
			rs = prep_stmt.executeQuery();

    	    return rs;
		}	
		catch (SQLException ex){
    	    sqlError(ex);
    	    return rs;
    	}
	}
	

	public boolean updatePlayer(int uid, String name, String token){
		try{
		  String query = "UPDATE Player SET name = ?, token = ? WHERE uid = ?";
		 
	      PreparedStatement prep_stmt = conn.prepareStatement(query);
	      prep_stmt.setString (1, name);
	      prep_stmt.setString (2, token);
	      prep_stmt.setInt (3, uid);
		 
	      prep_stmt.executeUpdate();
	      
	      return true;
	    }
	    catch (SQLException ex)
	    {
	      sqlError(ex);
	      return false;
	    }
	}
	

	// Tabelle Game
	public int insertGame(){
		try{
		  String query = "INSERT INTO Game VALUES ()";
		 
	      PreparedStatement prep_stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		 
	      prep_stmt.execute();
	      return getId(prep_stmt);
	      
	    }
	    catch (SQLException ex)
	    {
	      sqlError(ex);
	      return 0;
	    }
	}

	public ResultSet getGame(int uid){
	    
		try {
			String query = "SELECT * FROM Game WHERE uid = ?";
			
			PreparedStatement prep_stmt = conn.prepareStatement(query);
			prep_stmt.setInt(1, uid);
			
			rs = prep_stmt.executeQuery();

    	    return rs;
		}	
		catch (SQLException ex){
    	    sqlError(ex);
    	    return rs;
    	}
	}
	

	public boolean updateGame(int uid, int winnerPlayerId){
		try{
		  String query = "UPDATE Game SET winnerPlayerId = ? WHERE uid = ?";
		 
	      PreparedStatement prep_stmt = conn.prepareStatement(query);
	      prep_stmt.setInt (1, winnerPlayerId);
	      prep_stmt.setInt (2, uid);
		 
	      prep_stmt.executeUpdate();
	      
	      return true;
	    }
	    catch (SQLException ex)
	    {
	      sqlError(ex);
	      return false;
	    }
	}
	
	//Tabelle Ships
	public ResultSet getShips(){
	    
		try {
			String query = "SELECT * FROM Ship";
			
			PreparedStatement prep_stmt = conn.prepareStatement(query);
			
			rs = prep_stmt.executeQuery();

    	    return rs;
		}	
		catch (SQLException ex){
    	    sqlError(ex);
    	    return rs;
    	}
	}
	


}
