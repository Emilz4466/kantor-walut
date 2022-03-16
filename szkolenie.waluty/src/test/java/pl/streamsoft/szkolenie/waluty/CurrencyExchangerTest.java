package pl.streamsoft.szkolenie.waluty;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.parser.JsonParser;
import pl.streamsoft.szkolenie.waluty.data.parser.ParserStrategy;
import pl.streamsoft.szkolenie.waluty.data.service.ApiConnection;
import pl.streamsoft.szkolenie.waluty.data.service.ResponseStrategy;
import pl.streamsoft.szkolenie.waluty.exchanger.CurrencyExchanger;

public class CurrencyExchangerTest {

	CurrencyExchanger exchanger = new CurrencyExchanger();

	@Test
	public void shouldReturnCorrectExchangeValue() {
		// given
		LocalDate date = LocalDate.of(2022, 03, 9);
		BigDecimal givenValue = new BigDecimal(147.23);
		BigDecimal expectedExchangedValue = new BigDecimal(
				"30.4012058890334282698764387153476823783524844359789085501688532531261444091796875");
		ResponseStrategy connection = new ApiConnection(Currency.EUR, date);
		ParserStrategy parser = new JsonParser();

		// when
		BigDecimal recievedExchangedValue = exchanger.exchange(connection, givenValue, parser);

		// then
		Assert.assertEquals(expectedExchangedValue, recievedExchangedValue);

	}

}
