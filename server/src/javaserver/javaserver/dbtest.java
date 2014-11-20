package javaserver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class dbtest {
	
	DB db = new DB();
	
	
	public void get_player(){
		ResultSet player = db.getPlayer(3);
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
	
	public void insert_game(){
		System.out.println(db.insertGame());
	}
	
	public void getPlayerByToken(){
		System.out.println(db.getPlayerByToken("asdfas"));
	}
	
	public void getGamePlayerShotsByPlayerToken(){
		ResultSet test = db.getGamePlayerShotsByPlayerToken("asdfas");
		try{
			while(test.next()){
			System.out.println(test.getString("coord"));
			}
		}
		catch (SQLException ex) {
			db.sqlError(ex);
		}
	}
	
	public void getGamePlayerShipsByPlayerToken(){
		ResultSet test = db.getGamePlayerShipsByPlayerToken("asdfas");
		try{
			while(test.next()){
			System.out.println(test.getString("coord"));
			}
		}
		catch (SQLException ex) {
			db.sqlError(ex);
		}
	}
	

	public static void main(String[] args) {
		dbtest ausgabe = new dbtest();
		/*
		ausgabe.get_player();
		ausgabe.insert_player();
		ausgabe.insert_game();
		ausgabe.getPlayerByToken();
		*/
		//ausgabe.getGamePlayerShotsByPlayerToken();
		ausgabe.getGamePlayerShipsByPlayerToken();
	}

}
