package ru.tinkoff.TestAPI;



import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

public class AppTest {

	@Test
	public void givenUserExists_whenUserInformationIsRetrieved_thenRetrievedResourceIsCorrect() throws ClientProtocolException, IOException   {
		// Given
		final HttpUriRequest request = new HttpGet(Constants.URL);
		final Map<Integer,String> testData = new Config().readConfig(new FileReader(Constants.CONFIG_FILE));

		// When
		final HttpResponse response = HttpClientBuilder.create().build().execute(request);

		// Then
		JsonNode resource = RetrieveUtil.retrieveResourceFromResponse(response, JsonNode.class);

		assertEquals(resource.get("resultCode").asText(), "OK");

		List<JsonNode> currency = resource.findValues("toCurrency");
		currency.addAll(resource.findValues("fromCurrency"));
		for ( JsonNode node : currency ){
			
			int code = node.get("code").asInt();
			String name = node.get("name").asText();
			
			if (testData.containsKey(code)){
				//Main assert
				assertEquals(testData.get(code),name);
			}
			else continue;
			
		}

	}
}