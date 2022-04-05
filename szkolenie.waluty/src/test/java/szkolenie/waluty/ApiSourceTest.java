package szkolenie.waluty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nowewaluty.Currency;
import nowewaluty.objects.RateData;
import nowewaluty.objects.RatesSeries;
import nowewaluty.objects.RatesSeries.Rate;
import nowewaluty.parsers.parser.XmlParser;
import nowewaluty.sources.ApiSource;
import nowewaluty.strategies.ParserStrategy;

public class ApiSourceTest {

	ApiSource source;
	ParserStrategy parser;

	@Before
	public void setUp() {
		source = new ApiSource(Currency.EUR, LocalDate.of(2022, 03, 22));
		parser = new XmlParser();
	}

	@Test
	public void shouldReturnResponse() {

		// when
		String response = source.getResponse();

		// then
		Assert.assertNotNull(response);

	}

	@Test
	public void shouldReturnCorrectRate() {
		// given
		Rate expectedRate = new Rate(LocalDate.of(2022, 03, 22), new BigDecimal("4.6975"));
		List<Rate> expectedRates = new ArrayList<>();
		expectedRates.add(expectedRate);
		RatesSeries expectedRatesSeries = new RatesSeries("a", Currency.EUR, expectedRates);
		RateData expectedRateData = new RateData(expectedRatesSeries, LocalDate.of(2022, 03, 22), Currency.EUR);

		// when
		RateData rateData = source.getRate(parser);

		// then
		Assert.assertEquals(expectedRateData.getDate(), rateData.getDate());
		Assert.assertEquals(expectedRateData.getCurrency(), rateData.getCurrency());
		Assert.assertEquals(expectedRateData.getRate(), rateData.getRate());

	}

}
