package javaserver;

import org.json.simple.JSONObject;

public class MatchCtrl {

	public static JSONObject createMatch () {
		/*
		 * Datenbankzugriffe sollen im Model von Match.class erfolgen
		 */
		Match newMatch = Match.create();
		
		JSONObject response = new JSONObject();
		
		response.put("token_user", newMatch.getPlayerToken());
		response.put("token_opponent", newMatch.getOpponentToken());
		
		return response;
	}
	
	public static JSONObject getMatchDetails(String playertoken){
		
		Match match = Match.getMatchByToken(playertoken);
		JSONObject response = new JSONObject();
		
		response.put("token_opponnent", match.getOpponentToken());
		
		return response;
	}
	
	public static String createToken() {
		String token = "asdo3434t5ztsdfh45tws8sdfknsa";
		return token;
	}
}
