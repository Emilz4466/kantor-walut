package szkolenie.waluty;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import nowewaluty.Currency;
import nowewaluty.sources.url.Url;
import nowewaluty.sources.url.UrlBuilder;

public class UrlTest {

	@Test
	public void shouldBuildCorrectUrl() {
		// given
		Currency currency = Currency.EUR;
		LocalDate date = LocalDate.of(2022, 03, 03);
		String format = "json";
		UrlBuilder builder = new UrlBuilder();

		String expectedUrl = "https://api.nbp.pl/api/exchangerates/rates/a/eur/2022-03-03/?format=json";

		// when
		builder.setCurrency(currency);
		builder.setDate(date);
		builder.setFormat(format);
		Url urlOb = builder.buildUrl();

		String url = urlOb.getUrl();

		// then
		Assert.assertEquals(expectedUrl, url);
	}

}
