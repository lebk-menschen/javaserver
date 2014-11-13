package javaserver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class dbtest {
	
	DB db = new DB();
	
	
	public void get_player(){
		ResultSet player = db.getPlayer(1);
		try{
		   while(player.next()){
			   System.out.println(player.getString("name"));
		   }
		}
		catch (SQLException ex) {
			db.sqlError(ex);
		}
	}
	
	public void insert_player(){
		System.out.println(db.insertPlayer("test", "asdfas", 2));
	}
	

	public static void main(String[] args) {
		dbtest ausgabe = new dbtest();
		ausgabe.get_player();
		ausgabe.insert_player();
	}

}
