package pl.streamsoft.szkolenie.waluty;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.parser.JsonParser;
import pl.streamsoft.szkolenie.waluty.data.sources.ApiConnection;
import pl.streamsoft.szkolenie.waluty.data.sources.FileConnection;
import pl.streamsoft.szkolenie.waluty.data.sources.ResponseStrategy;
import pl.streamsoft.szkolenie.waluty.data.sources.url.Url;
import pl.streamsoft.szkolenie.waluty.data.sources.url.UrlBuilder;

public class JsonParserTest {
	
	ResponseStrategy response;
	JsonParser parser = new JsonParser();
	
	MathContext context = new MathContext(5, RoundingMode.HALF_UP);
	
	Currency currency = Currency.EUR;

	
	
	@Test
	public void shouldParseDataFromApiBeforeMay2004() {
		
		UrlBuilder urlBuilder = new UrlBuilder();
		urlBuilder.setCurrency(currency.getCode());
		urlBuilder.setDate(LocalDate.of(2003, 3, 9));
		urlBuilder.setFormat("json");
		Url url = urlBuilder.buildUrl();
		
		response = new ApiConnection(url);
		
		BigDecimal rateFromGivenDate =  new BigDecimal(4.2977).round(context);
				
		Assert.assertEquals( rateFromGivenDate,  parser.getRate(response));
		
		
	}
	
	
	@Test
	public void shouldParseDataFromApiAfterMay2004() {
		
		UrlBuilder urlBuilder = new UrlBuilder();
		urlBuilder.setCurrency(currency.getCode());
		urlBuilder.setDate(LocalDate.of(2022, 3, 9));
		urlBuilder.setFormat("json");
		Url url = urlBuilder.buildUrl();
		
		response = new ApiConnection(url);
		
		BigDecimal rateFromGivenDate =  new BigDecimal(4.8429).round(context);
				
		Assert.assertEquals( rateFromGivenDate,  parser.getRate(response));
		
		
	}
	
	@Test
	public void shouldParseDataFromFile() {
		
		String path = "src/test/java/json_test";
		
		response = new FileConnection(path);
		
		BigDecimal rateFromGivenDate =  new BigDecimal(4.8429).round(context);

		Assert.assertEquals( rateFromGivenDate,  parser.getRate(response));

	}
}
