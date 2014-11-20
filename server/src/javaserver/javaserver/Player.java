package javaserver;

import org.json.simple.JSONObject;

public class Player extends DB {
	String playerToken;
	String playerName;
	

	public void setPlayerToken(String playerToken) {
		this.playerToken = playerToken;
	}

	public Game getPlayerGame() {
		
		/*
		 * Beim ersten Aufruf wird eine Datenbankabfrage für das Game mit dem Token des Spielers gemacht.
		 * So werden pro Anfrage nur eine limitierte Anzahl an Datenbankzugriffen ermöglicht.
		 * 
		 * Da bei jeder HTTP-Anfrage neue Objekte für Player und Game erzeugt wird, ist es egal, 
		 * dass keine neuen Anfragen an die Datenbank erzeugt werden, weil sich in der Zwischenzeit nichts ändert.
		 */
		
		if (this.playerGame == null) {
			this.setPlayerGame(Game.getGameByToken(this.getPlayerToken()));
		}
		
		return playerGame;
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
		 * Sucht in der Datenbank nach einem Game, in dem der Spieler mit dem Token playerToken vorkommt.
		 * Wenn eins gefunden wird, wird ein neues Objekt vom Typ Player erzeugt, über welches dann auf das Game,
		 * die Gamezüge und den Gegner zugegriffen werden kann.
		 */
		
		if (true) {
			return new Player(playerToken);
		}
		
		return null;
	}
	
	public static Player insertPlayer(String name) {
		Game game = new Game();
		insertPlayer()
	}
}

/*
	public void setPlayerGame(Game playerGame) {
		this.playerGame = playerGame;
	}

	public Player getPlayerOpponent() {
		return playerOpponent;
	}

	public void setPlayerOpponent(Player playerOpponent) {
		this.playerOpponent = playerOpponent;
	}
	
 */