package szkolenie.waluty;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nowewaluty.Currency;
import nowewaluty.ResponseMethod;
import nowewaluty.objects.RateData;
import nowewaluty.parsers.parser.JsonParser;
import nowewaluty.sources.ApiSource;
import nowewaluty.strategies.ParserStrategy;
import nowewaluty.strategies.SourceStrategy;

public class ResponseMethodTest {

	SourceStrategy source;
	ParserStrategy parser;
	ResponseMethod method;

	@Before
	public void setUp() {
		source = new ApiSource(Currency.EUR, LocalDate.of(2022, 03, 25));
		parser = new JsonParser();
		method = new ResponseMethod(source, parser);
	}

	@Test
	public void shoudlReturnRateDataObject() {
		// given
		BigDecimal expectedValue = new BigDecimal("4.7459");
		LocalDate expectedDate = LocalDate.of(2022, 03, 25);
		Currency expectedCurrency = Currency.EUR;

		// when
		RateData rateData = method.getRateData();

		// then
		Assert.assertEquals(expectedValue, rateData.getRate());
		Assert.assertEquals(expectedDate, rateData.getDate());
		Assert.assertEquals(expectedCurrency, rateData.getCurrency());
	}

}
