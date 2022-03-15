package pl.streamsoft.szkolenie.waluty;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Test;

import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;
import pl.streamsoft.szkolenie.waluty.data.parser.JsonParser;

public class JsonParserTest {

	JsonParser parser = new JsonParser();

	MathContext context = new MathContext(5, RoundingMode.HALF_UP);

	@Test
	public void shouldGetCorrectRateFromResponse() throws NoDataException {
		// given
		String givenResponse = "{\"table\":\"A\",\"currency\":\"euro\",\"code\":\"EUR\",\"rates\":[{\"no\":\"047/A/NBP/2022\",\"effectiveDate\":\"2022-03-09\",\"mid\":4.8429}]}";
		BigDecimal expectedRate = new BigDecimal(4.8429).round(context);

		// when
		BigDecimal recievedRate = parser.getRate(givenResponse);

		// then
		Assert.assertEquals(expectedRate, recievedRate);
	}

	@Test
	public void shouldThrowNoDataExcepptionWhenResponseIsNull() throws NoDataException {
		String givenResponse = null;

		// when
		BigDecimal recievedRate = parser.getRate(givenResponse);

		// then
		Assert.assertEquals(null, recievedRate);
		fail("");
	}
}
