package javaserver;

import java.math.*;
import java.sql.ResultSet;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import org.json.simple.JSONObject;

import sun.org.mozilla.javascript.internal.annotations.JSConstructor;
import javaserver.DB;
//import com.mysql.jdbc.UpdatableResultSet;

public class MatchCtrl extends DB {

	public JSONObject createMatch(String nameFirstPlayer, String nameSecondPlayer) {
		JSONObject response = new JSONObject();
		int intGameID;
		int intFirstPlayerID;
		int intSecondPlayerID;
		String intFirstPlayerToken;
		String intSecondPlayerToken;
		
		intFirstPlayerToken = createToken();
		intSecondPlayerToken = createToken();
		intGameID = insertGame(); 
		intFirstPlayerID = insert_player(nameFirstPlayer, intFirstPlayerToken);
		intSecondPlayerID = insert_player(nameFirstPlayer, intSecondPlayerToken);
		
		response.put(intFirstPlayerID, intFirstPlayerToken);
		response.put("token_opponent", intSecondPlayerToken);
		
		return response;
	}
	
	public JSONObject shotCrtl(int posX, int posY, int playerID){
		ResultSet rsAllShots;
		
		rsAllShots = getShots(playerID);
		
		
		
		/** Alternative:
		 * rsAllShots = insertShot(posX, posY, playerID);
		 */
		
	}
	
	public static String createToken() {
		String token = "" + (Math.random() * 100000000000000L);
		return token;
	}
}
