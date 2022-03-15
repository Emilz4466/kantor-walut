package pl.streamsoft.szkolenie.waluty;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Test;

import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;
import pl.streamsoft.szkolenie.waluty.data.parser.XmlParser;

public class XmlParserTest {

	XmlParser parser = new XmlParser();

	MathContext context = new MathContext(5, RoundingMode.HALF_UP);

	@Test
	public void shouldGetCorrectRateFromResponse() throws NoDataException {
		// given
		String givenResponse = "<ExchangeRatesSeries xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><Table>A</Table><Currency>euro</Currency><Code>EUR</Code><Rates><Rate><No>047/A/NBP/2022</No><EffectiveDate>2022-03-09</EffectiveDate><Mid>4.8429</Mid></Rate></Rates></ExchangeRatesSeries>";
		BigDecimal expectedRate = new BigDecimal(4.8429).round(context);

		// when
		BigDecimal recievedRate = parser.getRate(givenResponse);

		// then
		Assert.assertEquals(expectedRate, recievedRate);
	}
}
