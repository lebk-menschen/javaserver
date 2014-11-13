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
	
	
	private void close_select(){
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
			sql_error(ex);
			return rid;
		}
	}
	
	public void sql_error(SQLException ex){
		System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}

	public int insert_player(String name, String token){
		try{
		  String query = "INSERT INTO Player (name, token) values (?, ?)";
		 
	      PreparedStatement prep_stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	      prep_stmt.setString (1, name);
	      prep_stmt.setString (2, token);
	      //preparedStmt.setDate   (3, startDate);
	      //preparedStmt.setBoolean(4, false);
	      //preparedStmt.setInt    (5, 5000);
		 
	      prep_stmt.execute();
	      return getId(prep_stmt);
	      
	    }
	    catch (SQLException ex)
	    {
	      sql_error(ex);
	      return 0;
	    }
	}

	public ResultSet get_player(int uid){
	    
		try {
			String query = "SELECT * FROM Player WHERE uid = ?";
			
			PreparedStatement prep_stmt = conn.prepareStatement(query);
			prep_stmt.setInt(1, uid);
			
			rs = prep_stmt.executeQuery();

    	    return rs;
		}	
		catch (SQLException ex){
    	    sql_error(ex);
    	    return rs;
    	}
	}
	

	public boolean update_player(int uid, String name, String token){
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
	      sql_error(ex);
	      return false;
	    }
	}

}
