package javaserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconn {
  public Connection conn(){
	  Connection conn = null;
	  try {
		    conn = DriverManager.getConnection("jdbc:mysql://130.255.75.168:443/Battleship?user=battleship&password=itfo2battleship");
		    return conn;
		   
	  } catch (SQLException ex) {
		    //return "conn";
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
	  }
	  return conn;
  }
}
