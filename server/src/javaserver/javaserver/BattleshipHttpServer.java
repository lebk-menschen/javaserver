package javaserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

import org.json.simple.JSONObject;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class BattleshipHttpServer {
	
	 public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
        
        server.createContext("/", new StaticFileHandler());
        server.createContext("/api/create", new MatchCreateHandler());
        server.createContext("/api/match", new MatchDetailsHandler());
        server.createContext("/api/turn/placeship", new PlaceshipHandler());
        server.createContext("/api/turn/shoot", new ShootHandler());
        
        
        server.setExecutor(null); // creates a default executor
        server.start();
    }
	 
	static class StaticFileHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			String root = "frontend/public";
            URI uri = t.getRequestURI();
            
            System.out.println("working directory + " + System.getProperty("user.dir"));
            
            String path = uri.getPath();
            
            System.out.println("looking for: "+ root + path);
            
            File file = new File(root + path).getCanonicalFile();

            if (!file.isFile()) {
              // Object does not exist or is not a file: reject with 404 error.
              String response = "404 (Not Found)\n";
              t.sendResponseHeaders(404, response.length());
              OutputStream os = t.getResponseBody();
              
              os.write(response.getBytes());
              os.close();
            } else {
              // Object exists and is a file: accept with response code 200.
              String mime = "text/html";
              if(path.substring(path.length()-3).equals(".js")) mime = "application/javascript";
              if(path.substring(path.length()-4).equals(".css")) mime = "text/css";            

              Headers h = t.getResponseHeaders();
              h.set("Content-Type", mime);
              t.sendResponseHeaders(200, 0);              

              OutputStream os = t.getResponseBody();
              FileInputStream fs = new FileInputStream(file);
              
              final byte[] buffer = new byte[0x10000];
              int count = 0;
              
              while ((count = fs.read(buffer)) >= 0) {
                os.write(buffer,0,count);
              }
              
              fs.close();
              os.close();
            }
		}
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
    
    static class PlaceshipHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            
            Headers h = t.getResponseHeaders();
            h.add("Content-Type", "application/json");
        	
        	
            JSONObject responseData = Ship.insertShip(1234, 1312, 11, "a");
            String response = responseData.toJSONString();
            
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    static class ShootHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            
            Headers h = t.getResponseHeaders();
            h.add("Content-Type", "application/json");
        	
        	
            JSONObject responseData = new JSONObject();
            String response = responseData.toJSONString();
            
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
