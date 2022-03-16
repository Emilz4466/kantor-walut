package pl.streamsoft.szkolenie.waluty;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;
import pl.streamsoft.szkolenie.waluty.data.service.ApiConnection;
import pl.streamsoft.szkolenie.waluty.data.sources.url.UrlBuilder;

public class ApiConnectionTest {

	UrlBuilder urlBuilder = new UrlBuilder();

	ApiConnection apiConnection = new ApiConnection();

	@Test
	public void shouldReturnStringResponse() throws NoDataException {

		// given
		apiConnection.setCurrency(Currency.EUR);
		apiConnection.setDate(LocalDate.of(2022, 03, 9));
		apiConnection.setFormat("json");

		// when
		String response = apiConnection.getResponse();

		// then
		Assert.assertNotNull(response);

	}

}
