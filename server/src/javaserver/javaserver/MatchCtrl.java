package javaserver;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;
import org.json.simple.JSONObject;
import javaserver.DB;
//import com.mysql.jdbc.UpdatableResultSet;

public class MatchCtrl extends DB {

	public JSONObject createMatch(String nameFirstPlayer, String nameSecondPlayer) {
		JSONObject response = new JSONObject();
		//int intGameID = insertGame();
		//int intFirstPlayerID = insert_player(nameFirstPlayer, createToken());
		response.put("token_user", "abcd");
		response.put("token_opponent", "asdf");
		
		return response;
	}
	
	public static String createToken() {
		String token = "asdo3434t5ztsdfh45tws8sdfknsa";
		return token;
	}
}
