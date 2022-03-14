package pl.streamsoft.szkolenie.waluty;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.parser.JsonParser;
import pl.streamsoft.szkolenie.waluty.data.parser.XmlParser;
import pl.streamsoft.szkolenie.waluty.exchanger.CurrencyExchanger;

public class CurrencyExchangerTest {
	
	MathContext context = new MathContext(5, RoundingMode.HALF_UP);
	JsonParser jsonParser = new JsonParser();
	XmlParser xmlParser = new XmlParser();
	
	BigDecimal value = new BigDecimal(147.23);
	LocalDate date = LocalDate.of(2022, 03, 9);
	BigDecimal expectedValue =  new BigDecimal(30.401).round(context);
	
	CurrencyExchanger exchanger = new CurrencyExchanger();
	
	
	
	@Test
	public void shouldExchangeValueFromApiJsonRate() {
	
		Assert.assertEquals( expectedValue,  exchanger.exchange(Currency.EUR, date, value, jsonParser).round(context));
		
	}
	
	@Test
	public void shouldExchangeValueFromApiXmlRate() {
	
		Assert.assertEquals( expectedValue,  exchanger.exchange(Currency.EUR, date, value, xmlParser).round(context));
		
	}
	
	@Test
	public void shouldExchangeValueFromFileJsonRate() {
		
		String path = "src/test/java/json_test";
	
		Assert.assertEquals( expectedValue,  exchanger.exchange(path, value, jsonParser).round(context));
		
	}
	
	@Test
	public void shouldExchangeValueFromFileXmlRate() {
		
		String path = "src/test/java/xml_test";
	
		Assert.assertEquals( expectedValue,  exchanger.exchange(path, value, xmlParser).round(context));
		
	}
	
}
