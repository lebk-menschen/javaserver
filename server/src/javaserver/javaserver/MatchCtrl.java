package javaserver;

import java.math.*;
import java.sql.ResultSet;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import org.json.simple.JSONObject;

import sun.org.mozilla.javascript.internal.annotations.JSConstructor;
import javaserver.DB;
//import com.mysql.jdbc.UpdatableResultSet;

public class MatchCtrl extends DB {

	public JSONObject createMatch(String playerName, String opponentName) {
		JSONObject response = new JSONObject();
		int gameID;
		int playerID;
		int opponentID;
		String playerToken;
		String opponentToken;

		gameID = insertGame(); 
		playerToken = createToken();
		opponentToken = createToken();
		playerID = insert_player(playerName, playerToken);
		opponentID = insert_player(opponentName, opponentToken);
		
		response.put(playerID, intPlayerToken);
		response.put("token_opponent", intOpponentToken);
		
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
