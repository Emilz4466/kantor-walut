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
import nowewaluty.parsers.parser.JsonParser;

public class JsonParserTest {
	RatesSeries expectedRatesSeries;

	@Before
	public void setUp() {
		Rate expectedRate = new Rate(LocalDate.of(2022, 03, 9), new BigDecimal("4.8429"));
		List<Rate> expectedRates = new ArrayList<>();
		expectedRates.add(expectedRate);
		expectedRatesSeries = new RatesSeries("A", Currency.EUR, expectedRates);
	}

	@Test
	public void test() {
		// given
		String responseFromSource = "{\"table\":\"A\",\"currency\":\"euro\",\"code\":\"EUR\",\"rates\":[{\"no\":\"047/A/NBP/2022\",\"effectiveDate\":\"2022-03-09\",\"mid\":4.8429}]}";

		JsonParser parser = new JsonParser();

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
