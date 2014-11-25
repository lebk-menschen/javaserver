package javaserver;

import org.json.simple.JSONObject;

public class Ship {
	int shipID;
	int gameID;
	int playerID;
	static int gamePlayerShipID;
	int hits;
	int length;
	int points;
	String line;
	String name;
	static DB db = new DB();
	
	public int getShipID() {
		return shipID;
	}
	public void setShipID(int shipID) {
		this.shipID = shipID;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public static int getGamePlayerShipID() {
		return gamePlayerShipID;
	}
	public void setGamePlayerShipID(int gamePlayerShipID) {
		this.gamePlayerShipID = gamePlayerShipID;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	public static JSONObject insertShip(int playerID, int gameID, int shipID, String coords) {
		JSONObject response = new JSONObject();
		gamePlayerShipID = db.insertGamePlayerShip(playerID, gameID, shipID, coords);
		
		response.put("gamePlayerShipID", getGamePlayerShipID());
		response.put("playerID", playerID);
		
		response.put("alles", "klar!");
		return response;
		
	}
	
}
