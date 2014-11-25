package javaserver;

import java.math.*;
import java.sql.ResultSet;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import org.json.simple.JSONObject;

import sun.org.mozilla.javascript.internal.annotations.JSConstructor;
import javaserver.DB;
//import com.mysql.jdbc.UpdatableResultSet;

public class GameCtrl extends DB {

	public static JSONObject createGame() {
		JSONObject response = new JSONObject();
		
		Game match = new Game();
		
		response.put("token_player", match.getPlayerToken());
		response.put("token_opponent", match.getOpponentToken());
		
		return response;
	}
	
	public static JSONObject shoot(int posX, int posY, String playerToken) {
		ResultSet rsAllShots;
		
		Player player = Player.getPlayer(playerToken);
		
		if (player != null) {

			//Game match = player.getGame();

			Match match = player.getPlayerMatch();

			
			Shot shot = new Shot(posX, posY);
			
			match.applyShot(shot);
		}
		
		
		
		/** Alternative:
		 * rsAllShots = insertShot(posX, posY, playerID);
		 */
		
		return new JSONObject();
	}
	
	public static JSONObject getGameDetails (String playerToken) {
		
		return new JSONObject();
	}
}
