package szkolenie.waluty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nowewaluty.Currency;
import nowewaluty.objects.RatesSeries;
import nowewaluty.objects.RatesSeries.Rate;
import nowewaluty.parsers.parser.XmlParser;

public class XmlParserTest {

	RatesSeries expectedRatesSeries;

	@Before
	public void setUp() {
		Rate expectedRate = new Rate(LocalDate.of(2022, 03, 9), new BigDecimal("4.8429"));
		List<Rate> expectedRates = new ArrayList<>();
		expectedRates.add(expectedRate);
		expectedRatesSeries = new RatesSeries("A", Currency.EUR, expectedRates);
	}

	@Test
	public void shouldParseXmlToRatesSeries() {
		// given
		String responseFromSource = "<ExchangeRatesSeries xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><Table>A</Table><Currency>euro</Currency><Code>EUR</Code><Rates><Rate><No>047/A/NBP/2022</No><EffectiveDate>2022-03-09</EffectiveDate><Mid>4.8429</Mid></Rate></Rates></ExchangeRatesSeries>";
		XmlParser parser = new XmlParser();

		// when
		RatesSeries ratesSeries = parser.getRatesSeries(responseFromSource);

		// then
		Assert.assertEquals(expectedRatesSeries.getTable(), ratesSeries.getTable());
		Assert.assertEquals(expectedRatesSeries.getCode(), ratesSeries.getCode());
		Assert.assertEquals(expectedRatesSeries.getRates().get(0).getMid(), ratesSeries.getRates().get(0).getMid());
		Assert.assertEquals(expectedRatesSeries.getRates().get(0).getEffectiveDate(),
				ratesSeries.getRates().get(0).getEffectiveDate());

	}

}
