package javaserver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class dbtest {
	
	DB db = new DB();
	
	
	public void get_player(){
		ResultSet player = db.get_player(1);
		try{
		   while(player.next()){
			   System.out.println(player.getString("name"));
		   }
		}
		catch (SQLException ex) {
			db.sql_error(ex);
		}
	}
	

	public static void main(String[] args) {
		dbtest ausgabe = new dbtest();
		ausgabe.get_player();
	}

}
