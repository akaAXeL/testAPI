package ru.tinkoff.TestAPI;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
			URL jsonURL = new URL("https://www.tinkoff.ru/api/v1/currency_rates/");
			URLConnection yc = jsonURL.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                                yc.getInputStream()));
	        String inputLine="";
	        String alldoc="";
	        while ((inputLine = in.readLine()) != null) 
	            alldoc+=inputLine;
	        in.close();
			
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(alldoc);
			
			List <JsonNode> nodeList = rootNode.findValues("fromCurrency");
			System.out.println(nodeList.get(1).get("name"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }
}
