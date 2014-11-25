package javaserver;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.LinkedList;

import org.json.simple.JSONObject;

import com.sun.jmx.snmp.Timestamp;


public class Game extends DB {
	
	protected String 			playerToken;
	protected String 			opponentToken;
	protected int 				gameID;
	protected LinkedList<Shot> 	gameShots;
	
	public Game (String playerToken, int gameID) {
		this.setPlayerToken(playerToken);
		this.setOpponentToken(getOpponentTokenFromDB(gameID, playerToken));
	}
	
	public Game() {
		this.playerToken = createToken();
		this.opponentToken = createToken();
		this.gameID = insertGame();
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
	
	public String getOpponentTokenFromDB(String playerToken, int gameID) {
		//GetOpponentToken Funktion in DB Klasse erstellen! 
		//SELECT Token FROM Player WHERE GameID = $GameID$ AND Token != playerToken
		return getOpponentToken(playerID, gameID); //DB Klasse
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
	
	
	public String createToken() {
		return "" + System.currentTimeMillis();
	}

	public static Game getGameByToken(String playerToken) {
		/*
		  Suche in der Datenbank nach einem game mit dem Player-Token playerToken
		  Wenn eins gefunden wurde, erstelle ein neues game-Objekt mit den Daten aus der Datenbank
		  
		  Wenn nicht, wirf 'ne Exception
	 
		*/
		 
		
		if (gameExists(playerToken)) {
			return new Game(playerToken, gameID);
		}
		
		return null; //Spiel gibt es noch nicht -- neues Spiel?
	}


	
	public static boolean gameExists (String playerToken) {
		ResultSet rsGame = getGame(playerToken);
		
		if (rsGame.wasNull()) {
			return false;
		} 
		return true;
	}
	
	
}
