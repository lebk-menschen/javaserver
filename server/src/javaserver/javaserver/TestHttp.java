package javaserver;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import org.json.simple.JSONObject;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class TestHttp {
	
	 public static void main(String[] args) throws Exception {
	        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
	        
	        server.createContext("/api/create" , new MatchCreateHandler());
	        server.createContext("/api/match" , new MatchDetailsHandler());
	        
	        server.setExecutor(null); // creates a default executor
	        server.start();
	    }
	 
	    static class MatchCreateHandler implements HttpHandler {
	        public void handle(HttpExchange t) throws IOException {
	            
	            Headers h = t.getResponseHeaders();
	            h.add("Content-Type", "application/json");
	            
	            JSONObject responseData = MatchCtrl.createMatch();
	            
	            String responseBody = responseData.toJSONString();
	            t.sendResponseHeaders(200, responseBody.length());
	            
	            OutputStream os = t.getResponseBody();
	            os.write(responseBody.getBytes());
	            os.close();
	        }
	    }
	    

	    static class MatchDetailsHandler implements HttpHandler {
	        public void handle(HttpExchange t) throws IOException {
	            
	            Headers h = t.getResponseHeaders();
	            h.add("Content-Type", "application/json");
	        	
	        	
	            JSONObject responseData = MatchCtrl.getMatchDetails("Token vom anfragenden Player");
	            String response = responseData.toJSONString();
	            
	            t.sendResponseHeaders(200, response.length());
	            OutputStream os = t.getResponseBody();
	            os.write(response.getBytes());
	            os.close();
	        }
	    }
}
