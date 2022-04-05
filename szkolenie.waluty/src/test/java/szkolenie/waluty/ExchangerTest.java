package szkolenie.waluty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nowewaluty.Currency;
import nowewaluty.Exchanger;
import nowewaluty.ResponseMethod;
import nowewaluty.parsers.parser.JsonParser;
import nowewaluty.sources.ApiSource;
import nowewaluty.strategies.ParserStrategy;
import nowewaluty.strategies.SourceStrategy;

public class ExchangerTest {

	Exchanger exchanger = new Exchanger();

	List<ResponseMethod> responses = new ArrayList<>();
	SourceStrategy source;
	ParserStrategy parser;
	ResponseMethod method;

	@Before
	public void setUp() {
		source = new ApiSource(Currency.EUR, LocalDate.of(2022, 03, 27));
		parser = new JsonParser();
		method = new ResponseMethod(source, parser);
		responses.add(method);
	}

	@Test
	public void shouldBackDate() {
		// given
		LocalDate expectedDate = LocalDate.of(2022, 03, 25);

		// when
		exchanger.exchange(new BigDecimal(10), responses);

		// then
		Assert.assertEquals(expectedDate, responses.get(0).getSource().getDate());

	}

	@Test
	public void shouldReturnCorrectExchangedValue() {
		// given
		BigDecimal qouta = new BigDecimal(100);
		BigDecimal expectedValue = new BigDecimal("21.0708190227354137255315114098485000");

		// when
		List<BigDecimal> result = exchanger.exchange(qouta, responses);

		// then
		Assert.assertEquals(expectedValue, result.get(0));

	}

}
