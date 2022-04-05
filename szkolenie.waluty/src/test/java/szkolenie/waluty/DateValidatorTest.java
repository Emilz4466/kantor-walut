package szkolenie.waluty;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import nowewaluty.sources.url.DateValidator;

public class DateValidatorTest {

	DateValidator validator;

	@Test
	public void shouldSetFirstAvailableCurrency() {
		// given
		LocalDate expectedDate = LocalDate.of(2002, 01, 02);
		validator = new DateValidator(LocalDate.of(2000, 01, 01));

		// when
		LocalDate date = validator.dateValidation();

		// then
		Assert.assertEquals(expectedDate, date);
	}

	@Test
	public void shouldSetCurrentDateIfFutureIsGiven() {
		// given
		LocalDate expectedDate = LocalDate.now();
		validator = new DateValidator(LocalDate.of(2023, 01, 01));

		// when
		LocalDate date = validator.dateValidation();

		// then
		Assert.assertEquals(expectedDate, date);
	}

}
