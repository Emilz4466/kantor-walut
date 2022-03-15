package pl.streamsoft.szkolenie.waluty;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import pl.streamsoft.szkolenie.waluty.data.sources.url.DateValidator;

public class DateValidatorTest {

	DateValidator validator = new DateValidator();

	@Test
	public void shouldChangeDateIfGivenIsBeforeLastAvailableCurrency() {
		// given
		LocalDate givenDate = LocalDate.of(2000, 01, 03);
		LocalDate expectedDate = LocalDate.of(2002, 01, 02);
		validator.setDate(givenDate);

		// when
		LocalDate recievedDate = validator.dateValidation();

		// then
		Assert.assertEquals(expectedDate, recievedDate);

	}

	@Test
	public void shouldChangeDateIfGivenIsAfterNowDate() {
		// given
		LocalDate givenDate = LocalDate.of(2023, 01, 03);
		LocalDate expectedDate = LocalDate.now();
		validator.setDate(givenDate);

		// when
		LocalDate recievedDate = validator.dateValidation();

		// then
		Assert.assertEquals(expectedDate, recievedDate);

	}
}
