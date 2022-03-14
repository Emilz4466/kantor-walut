package pl.streamsoft.szkolenie.waluty;


import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.sources.url.Url;
import pl.streamsoft.szkolenie.waluty.data.sources.url.UrlBuilder;

public class UrlBuilderTest {
	
	UrlBuilder builder = new UrlBuilder();
	Url url;
	
	Currency currency = Currency.EUR;

	
	@Before
	public void init() {
		builder.setCurrency(currency);
		builder.setDate(LocalDate.of(2022, 3, 9));
		builder.setFormat("json");
	}
	
	@Test
	public void shouldBuildUrl() {
		
		Assert.assertNotNull(builder);
	}
	
	@Test
	public void shouldReturnCorrectUrl() {
		
		//given
		String correctUrl = "https://api.nbp.pl/api/exchangerates/rates/a/eur/2022-03-09/?format=json";

		//when
		url = builder.buildUrl();
		String urlText = url.getUrl();
		
		//then
		Assert.assertEquals(correctUrl, urlText);
	}

}
