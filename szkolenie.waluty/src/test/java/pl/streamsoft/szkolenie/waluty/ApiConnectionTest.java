package pl.streamsoft.szkolenie.waluty;


import java.time.LocalDate;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.sources.ApiConnection;
import pl.streamsoft.szkolenie.waluty.data.sources.url.Url;
import pl.streamsoft.szkolenie.waluty.data.sources.url.UrlBuilder;

public class ApiConnectionTest {

	UrlBuilder urlBuilder = new UrlBuilder();
	
	@Before
	public void init() {
		urlBuilder.setCurrency(Currency.EUR);
		urlBuilder.setDate(LocalDate.of(2022, 3, 9));
		urlBuilder.setFormat("json");
		
	}
	
	@Test
	public void shouldReturnStringResponse() {
		
		// given
		Url url = urlBuilder.buildUrl();
		ApiConnection apiConnection = new ApiConnection(url);
		
		// when
		String response = apiConnection.getResponse();
		
		//then
		Assert.assertNotNull(response);
		
	}
	
	@Test
	public void shouldChangeDateBeforeLastCurrencyDateGiven() {
		
		// given
		urlBuilder.setDate(LocalDate.of(2000, 01, 01));
		Url url = urlBuilder.buildUrl();
		ApiConnection apiConnection = new ApiConnection(url);
		
		// when
		String response = apiConnection.getResponse();
		
		//then
		Assert.assertNotNull(response);
	}
	
	@Test
	public void shouldChangeDateWithFutureDateGiven() {
		
		// given
		urlBuilder.setDate(LocalDate.of(2023, 01, 01));
		Url url = urlBuilder.buildUrl();
		ApiConnection apiConnection = new ApiConnection(url);
		
		// when
		String response = apiConnection.getResponse();
		
		//then
		Assert.assertNotNull(response);
	}
	
	@Test
	public void shouldChangeDateWhenDataNotAvailable() {
		// given
		urlBuilder.setDate(LocalDate.of(2022, 03, 06)); 		//Niedziela
		Url url = urlBuilder.buildUrl();
		ApiConnection apiConnection = new ApiConnection(url);
		
		// given
		String response = apiConnection.getResponse();
		
		//then
		Assert.assertNotNull(response);
	}

}
