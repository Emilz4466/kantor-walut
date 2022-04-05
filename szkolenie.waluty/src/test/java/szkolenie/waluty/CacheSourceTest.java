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
import nowewaluty.sources.CacheSource;

public class CacheSourceTest {

	CacheSource source;
	RateData rateData;

	@Before
	public void setUp() {
		source = new CacheSource();

		Rate rate = new Rate(LocalDate.of(2022, 03, 22), new BigDecimal("4.6975"));
		List<Rate> rates = new ArrayList<>();
		rates.add(rate);
		RatesSeries ratesSeries = new RatesSeries("a", Currency.EUR, rates);
		rateData = new RateData(ratesSeries, LocalDate.of(2022, 03, 22), Currency.EUR);
	}

	@Test
	public void shouldSaveDataInCache() {
		// given
		source = new CacheSource(Currency.EUR, LocalDate.of(2022, 03, 22));

		// when
		source.saveRate(rateData);

		// then
		Assert.assertNotNull(source.getRate(null));
	}

	@Test
	public void shouldReturnCorrectData() {
		// given
		source = new CacheSource(Currency.EUR, LocalDate.of(2022, 03, 22));

		// when
		source.saveRate(rateData);

		// then
		Assert.assertEquals(rateData.getRate(), source.getRate(null).getRate());
	}

}
