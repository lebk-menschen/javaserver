package javaserver;

import java.util.LinkedList;

import org.json.simple.JSONObject;


public class Match {
	
	protected String 			playerToken;
	protected String 			opponentToken;
	protected LinkedList<Shot> 	matchShots;
	
	public Match (String playerToken, String opponentToken) {
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
	
	public LinkedList<Shot> getMatchShots() {
		if (this.matchShots == null) {
			/*
			 * Wenn noch keine Züge geladen sind, sollen Datenbankzugriffe erfolgen, die die Züge auswerten
			 * 
			 * Sobald das getan ist, werden die Züge in matchTurns gespeichert.
			 *
			 * Anschließend wird this.matchTurns ausgegeben.
			 */
			
			this.matchShots = new LinkedList<Shot>();
		}
		
		return this.matchShots;
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
	
	public static Match create () {
		/*
		 * Lege ein neues Match in der Datenbank an
		 * Marke dir das Player-token
		 * 
		 * Anschlie�end soll das Match zur�ckgegeben werden 
		 */
		
		/*
		 * 
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
		 */
		
		return Match.getMatchByToken("playertoken");
	}
	
	public static Match getMatchByToken(String playerToken) {
		/*
		 * Suche in der Datenbank nach einem Match mit dem Player-Token playerToken
		 * Wenn eins gefunden wurde, erstelle ein neues Match-Objekt mit den Daten aus der Datenbank
		 * 
		 * Wenn nicht, wirf 'ne Exception
		 */
		
		if (Match.exists(playerToken)) {
			return new Match("Token vom Player", "Token vom Gegner");
		}
		
		return null;
	}
	
	public static boolean exists (String playerToken) {
		/*
		 * Suche in der Datenbank nach einem Match, in dem ein Playe das Token playerToken hat
		 * Falls es das gibt, gib true zur�ck, ansonsten false
		 */
		
		return true;
	}
	
	
}
