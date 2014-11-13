package javaserver;

public class Shot {
	
	protected String 	playerToken;
	protected int 		posX;
	protected int 		posY;
	
	public Shot (int x, int y) {
		this.posX = x;
		this.posY = y;
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
		
		return new Shot(1, 2);
	}
}
