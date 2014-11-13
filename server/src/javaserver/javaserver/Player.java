package javaserver;

import org.json.simple.JSONObject;

public class Player {

	public void setPlayerToken(String playerToken) {
		this.playerToken = playerToken;
	}

	public Match getPlayerMatch() {
		
		/*
		 * Beim ersten Aufruf wird eine Datenbankabfrage für das Match mit dem Token des Spielers gemacht.
		 * So werden pro Anfrage nur eine limitierte Anzahl an Datenbankzugriffen ermöglicht.
		 * 
		 * Da bei jeder HTTP-Anfrage neue Objekte für Player und Match erzeugt wird, ist es egal, 
		 * dass keine neuen Anfragen an die Datenbank erzeugt werden, weil sich in der Zwischenzeit nichts ändert.
		 */
		
		if (this.playerMatch == null) {
			this.setPlayerMatch(Match.getMatchByToken(this.getPlayerToken()));
		}
		
		return playerMatch;
	}

	public void setPlayerMatch(Match playerMatch) {
		this.playerMatch = playerMatch;
	}

	public Player getPlayerOpponent() {
		return playerOpponent;
	}

	public void setPlayerOpponent(Player playerOpponent) {
		this.playerOpponent = playerOpponent;
	}
	
	
	public boolean isPlayersMove () {
		/*
		 * Gibt true zurück, wenn der Spieler am Zug ist, oder false, 
		 * wenn der Gegner dran ist.
		 */
		return true;
	}
	

	public static Player getPlayer (String playerToken) {

		/*
		 * Sucht in der Datenbank nach einem Match, in dem der Spieler mit dem Token playerToken vorkommt.
		 * Wenn eins gefunden wird, wird ein neues Objekt vom Typ Player erzeugt, über welches dann auf das Match,
		 * die Matchzüge und den Gegner zugegriffen werden kann.
		 */
		
		if (true) {
			return new Player(playerToken);
		}
		
		return null;
	}
	
	public JSONObject insertPlayer() {
		
	}
}
