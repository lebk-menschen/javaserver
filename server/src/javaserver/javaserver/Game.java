package javaserver;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.LinkedList;

import org.json.simple.JSONObject;

import com.sun.jmx.snmp.Timestamp;


public class Game extends DB {
	
	protected String 			playerToken;
	protected String 			opponentToken;
	protected LinkedList<Shot> 	gameShots;
	
	public Game (String playerToken, String opponentToken) {
		this.setPlayerToken(playerToken);
		this.setOpponentToken(opponentToken);
	}
	
	
	
	/*
	 * Getter und Setter
	 */
	
	public String getPlayerToken() {
		return playerToken;
	}

	public void setPlayerToken(String playerToken) {
		this.playerToken = playerToken;
	}

	public String getOpponentToken() {
		return opponentToken;
	}

	public void setOpponentToken(String opponentToken) {
		this.opponentToken = opponentToken;
	}
	
	public LinkedList<Shot> getgameShots() {
		if (this.gameShots == null) {
			/*
			 * Wenn noch keine Züge geladen sind, sollen Datenbankzugriffe erfolgen, die die Züge auswerten
			 * 
			 * Sobald das getan ist, werden die Züge in gameTurns gespeichert.
			 *
			 * Anschließend wird this.gameTurns ausgegeben.
			 */
			
			this.gameShots = new LinkedList<Shot>();
		}
		
		return this.gameShots;
	}
	
	public String applyShot (Shot shot) {
		/*
		 * Ist dieser Zug möglich? ansonsten 'invalid'
		 * 
		 * Ist da ein Schiff? ''
		 */
		
		return "invalid";
	}
	
	
	
	/*
	 * Klassenmethoden
	 */
	
	
	/*
	 * Statische Methoden
	 */
	
	public Game create (String playerName, String opponentName) {
		/*
		 * Lege ein neues game in der Datenbank an
		 * Marke dir das Player-token
		 * 
		 * Anschlie�end soll das game zur�ckgegeben werden 
		 */
		
		
		 
		JSONObject response = new JSONObject();
		int gameID;

		gameID = insertGame(); 
		this.playerToken = createToken();
		this.opponentToken = createToken();
		insertPlayer(playerName, playerToken, gameID);
		insertPlayer(opponentName, opponentToken, gameID);
		
		response.put("GameID", gameID);
		response.put(playerName, playerToken);
		response.put(playerName, opponentToken);
		 
		
		return response;
	}
	
	public String createToken() {
		return "" + System.currentTimeMillis();
	}
/*
	public static Game getGameByToken(String playerToken) {
		*
		 * Suche in der Datenbank nach einem game mit dem Player-Token playerToken
		 * Wenn eins gefunden wurde, erstelle ein neues game-Objekt mit den Daten aus der Datenbank
		 * 
		 * Wenn nicht, wirf 'ne Exception
		 * 
		 * 
		 *
		
		if (exists(playerToken)) {
			return new Game("Token vom Player", "Token vom Gegner");
		}
		
		return null;
	}
*/
	
	public static boolean gameExists(String playerToken, String opponetToken) {
		
		if(tokenHasGame(playerToken) && tokenHasGame(opponetToken) ) {
			return true;
		}		
		return false;
	}
	
	public static boolean tokenHasGame (String playerToken) {
		ResultSet rsGame = getGame(playerToken);
		
		if (rsGame.wasNull()) {
			return false;
		} 
		return true;
	}
	
	
}
