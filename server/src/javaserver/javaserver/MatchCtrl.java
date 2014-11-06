package javaserver;

import org.json.simple.JSONObject;

public class MatchCtrl extends DB {

	public static JSONObject createMatch() {
		JSONObject response = new JSONObject();
		
		response.put("token_user", "abcd");
		response.put("token_opponent", "asdf");
		
		return response;
	}
}
