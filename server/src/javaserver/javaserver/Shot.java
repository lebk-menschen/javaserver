package javaserver;

public class Shot {
	
	protected String playerToken;
	
	public Shot () {
		
	}

	public String getPlayerToken() {
		return playerToken;
	}

	public void setPlayerToken(String playerToken) {
		this.playerToken = playerToken;
	}
	
	
	
	/*
	 * Statische Methoden
	 */
	
	public static Shot getShot (String matchId, String shotId) {
		
		return new Shot();
	}
}
