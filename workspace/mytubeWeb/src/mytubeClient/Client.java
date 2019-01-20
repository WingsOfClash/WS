package mytubeClient;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.ws.rs.core.MediaType;

public class Client {
	public static void main(String[] args) {
		//GET
		try {
			URL url = new URL ("http://localhost:8080/mytubeWeb/rest/text");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", MediaType.TEXT_PLAIN);
			if(conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode()); }
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;
			while((output = br.readLine()) != null)
			{
			System.out.println("\nClient text_plain: " + output );
			}
			conn.disconnect();
		} catch (MalformedURLException e) { e.printStackTrace();
		} catch (IOException e) { e.printStackTrace(); }

		//POST
		try {
			URL url = new URL ("http://localhost:8080/myRESTwsWeb/rest/post");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			String input = "{\"codi\":3,\"nom\":\"pepito\"}";
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			if(conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode()); }
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output;
			while((output = br.readLine()) != null)
			{
			System.out.println("\nClient POST. Resposta: " + output );
			}
			conn.disconnect();
		} catch (MalformedURLException e) { e.printStackTrace();
		} catch (IOException e) { e.printStackTrace(); }
	}
}
