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
	        
	        server.createContext("/test", new MyHandler());
	        server.createContext("/api/create" , new CreateHandler());
	        server.createContext("/api/login" , new CreateHandler());
	        server.createContext("/api/match" , new CreateHandler());
	        
	        
	        server.setExecutor(null); // creates a default executor
	        server.start();
	    }

	    static class MyHandler implements HttpHandler {
	        public void handle(HttpExchange t) throws IOException {
	            String response = "This is the response";
	            Headers h = t.getResponseHeaders();
	            h.add("Content-Type", "application/json");
	            t.sendResponseHeaders(200, response.length());
	            OutputStream os = t.getResponseBody();
	            os.write(response.getBytes());
	            os.close();
	        }
	    }
	    static class CreateHandler implements HttpHandler {
	        public void handle(HttpExchange t) throws IOException {
//	            String response = "token_user: String, token_opponent: String";
	        	
	            JSONObject responseObj = new JSONObject();	
	            responseObj.put("token_user", "asdf");	
	            responseObj.put("token_opponent", "jkl");
	            
	            String response = responseObj.toJSONString();
	            
	            Headers h = t.getResponseHeaders();
	            h.add("Content-Type", "application/json");
	            t.sendResponseHeaders(200, response.length());
	            OutputStream os = t.getResponseBody();
	            os.write(response.getBytes());
	            os.close();
	        }
	    }
	    
	    static class LoginHandler implements HttpHandler {
	        public void handle(HttpExchange t) throws IOException {
//	            String response = "token_user: String, token_opponent: String";
	        	
	            JSONObject responseObj = new JSONObject();	
	            responseObj.put("user", "asdf");	
	            responseObj.put("pass", "jkl");
	            
	            String response = responseObj.toJSONString();
	            
	            Headers h = t.getResponseHeaders();
	            h.add("Content-Type", "application/json");
	            t.sendResponseHeaders(200, response.length());
	            OutputStream os = t.getResponseBody();
	            os.write(response.getBytes());
	            os.close();
	        }
	    }
	    static class MatchHandler implements HttpHandler {
	        public void handle(HttpExchange t) throws IOException {
	        	
//              LUKAS JSONObject erwartet!
	            JSONObject responseObj = new JSONObject();	
	       
	            String response = responseObj.toJSONString();
	            
	            Headers h = t.getResponseHeaders();
	            h.add("Content-Type", "application/json");
	            t.sendResponseHeaders(200, response.length());
	            OutputStream os = t.getResponseBody();
	            os.write(response.getBytes());
	            os.close();
	        }
	    }
}
