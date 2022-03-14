package pl.streamsoft.szkolenie.waluty;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.parser.XmlParser;
import pl.streamsoft.szkolenie.waluty.data.sources.ApiConnection;
import pl.streamsoft.szkolenie.waluty.data.sources.FileConnection;
import pl.streamsoft.szkolenie.waluty.data.sources.ResponseStrategy;
import pl.streamsoft.szkolenie.waluty.data.sources.url.Url;
import pl.streamsoft.szkolenie.waluty.data.sources.url.UrlBuilder;

public class XmlParserTest {
	
	ResponseStrategy response;
	XmlParser parser = new XmlParser();
	
	Currency currency = Currency.EUR;
	
	MathContext context = new MathContext(5, RoundingMode.HALF_UP);
	
	
	@Test
	public void shouldParseDataFromApiBeforeMay2004() {
		
		//given
		UrlBuilder urlBuilder = new UrlBuilder();
		urlBuilder.setCurrency(currency);
		urlBuilder.setDate(LocalDate.of(2003, 3, 9));
		urlBuilder.setFormat("xml");
		Url url = urlBuilder.buildUrl();
		
		response = new ApiConnection(url);
		
		BigDecimal rateFromGivenDate =  new BigDecimal(4.2977).round(context);
		
		//when
		BigDecimal parseRate =  parser.getRate(response);
			
		//then
		Assert.assertEquals( rateFromGivenDate, parseRate);
		
	}
	
	
	@Test
	public void shouldParseDataFromApiAfterMay2004() {
		
		//given
		UrlBuilder urlBuilder = new UrlBuilder();
		urlBuilder.setCurrency(currency);
		urlBuilder.setDate(LocalDate.of(2022, 3, 9));
		urlBuilder.setFormat("xml");
		Url url = urlBuilder.buildUrl();
		
		response = new ApiConnection(url);
		
		BigDecimal rateFromGivenDate =  new BigDecimal(4.8429).round(context);
		
		//when
		BigDecimal parseRate =  parser.getRate(response);
				
		//then
		Assert.assertEquals( rateFromGivenDate,  parseRate);
			
	}
	
	@Test
	public void shouldParseDataFromFile() {
		
		//given
		String path = "src/test/java/xml_test";
		
		response = new FileConnection(path);
		
		BigDecimal rateFromGivenDate =  new BigDecimal(4.8429).round(context);
		
		//when
		BigDecimal parseRate =  parser.getRate(response);

		//then
		Assert.assertEquals( rateFromGivenDate,  parseRate);

	}
}
