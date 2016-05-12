package ru.tinkoff.TestAPI;



import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void givenUserExists_whenUserInformationIsRetrieved_thenRetrievedResourceIsCorrect() throws IOException  {
		// Given
		final HttpUriRequest request = new HttpGet("https://www.tinkoff.ru/api/v1/currency_rates/");

		// When
		final HttpResponse response = HttpClientBuilder.create().build().execute(request);

		// Then
		JsonNode resource = RetrieveUtil.retrieveResourceFromResponse(response, JsonNode.class);
		List<JsonNode> toCurrency = resource.findValues("toCurrency");
		
		assertEquals(resource.get("resultCode").asText(), "OK");	
		for ( JsonNode node : toCurrency ){
			String name = node.get("name").asText();
			int code = node.get("code").asInt();
			switch (name) {
			case "RUB":
				assertEquals(code, 643);
				break;
			case "EUR":
				assertEquals(code, 978);
				break;
			case "GBR":
				assertEquals(code, 826);
				break;
			case "USD":
				assertEquals(code, 840);
				break;
			default:
				break;
			}
		}

	}
}