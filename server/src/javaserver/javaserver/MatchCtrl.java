package javaserver;

import java.math.*;
import java.sql.ResultSet;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import org.json.simple.JSONObject;

import sun.org.mozilla.javascript.internal.annotations.JSConstructor;
import javaserver.DB;
//import com.mysql.jdbc.UpdatableResultSet;

public class MatchCtrl extends DB {

	public static JSONObject createMatch() {
		JSONObject response = new JSONObject();
		
		Match match = Match.create();
		
		response.put("token_player", match.getPlayerToken());
		response.put("token_opponent", match.getOpponentToken());
		
		return response;
	}
	
	public static JSONObject shoot(int posX, int posY, String playerToken) {
		ResultSet rsAllShots;
		
		Player player = Player.getPlayer(playerToken);
		
		if (player != null) {
			Match match = player.getPlayerMatch();
			
			Shot shot = new Shot(posX, posY);
			
			match.applyShot(shot);
		}
		
		
		
		/** Alternative:
		 * rsAllShots = insertShot(posX, posY, playerID);
		 */
		
		return new JSONObject();
	}
	
	public static JSONObject getMatchDetails (String playerToken) {
		
		return new JSONObject();
	}
}
