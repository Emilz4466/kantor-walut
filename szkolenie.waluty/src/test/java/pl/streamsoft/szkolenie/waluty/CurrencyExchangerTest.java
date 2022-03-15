package pl.streamsoft.szkolenie.waluty;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.parser.JsonParser;
import pl.streamsoft.szkolenie.waluty.data.parser.ParserStrategy;
import pl.streamsoft.szkolenie.waluty.data.sources.ApiConnection;
import pl.streamsoft.szkolenie.waluty.data.sources.ResponseStrategy;
import pl.streamsoft.szkolenie.waluty.exchanger.CurrencyExchanger;

public class CurrencyExchangerTest {

	MathContext context = new MathContext(5, RoundingMode.HALF_UP);
	CurrencyExchanger exchanger = new CurrencyExchanger();

	@Test
	public void shouldReturnCorrectExchangeValue() {
		// given
		LocalDate date = LocalDate.of(2022, 03, 9);
		BigDecimal givenValue = new BigDecimal(147.23);
		BigDecimal expectedExchangedValue = new BigDecimal(30.401).round(context);
		ResponseStrategy connection = new ApiConnection(Currency.EUR, date);
		ParserStrategy parser = new JsonParser();

		// when
		BigDecimal recievedExchangedValue = exchanger.exchange(connection, givenValue, parser).round(context);

		// then
		Assert.assertEquals(expectedExchangedValue, recievedExchangedValue);

	}

	@Test
	public void shouldThrowNoDataExceptionWhenResponseIsNull() {
		// given
		ResponseStrategy response = new ApiConnection(Currency.EUR, LocalDate.of(2022, 03, 9));
		BigDecimal value = new BigDecimal(122);
		ParserStrategy parser = new JsonParser();

		// when
		BigDecimal recievedValue = exchanger.exchange(response, value, parser);

		// then
		Assert.assertEquals(null, recievedValue);
		fail("NoDataException was thrown");

	}
}
