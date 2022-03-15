package pl.streamsoft.szkolenie.waluty.data.sources.url;

import java.time.LocalDate;

public class DateValidator {

	private LocalDate date;

	public DateValidator() {
	}

	public DateValidator(LocalDate date) {
		this.date = date;
	}

	public LocalDate dateValidation() {
		LocalDate lastCurrency = LocalDate.of(2002, 01, 02);

		if (date.isBefore(lastCurrency)) {
			date = lastCurrency;
		} else if (date.isAfter(LocalDate.now())) {
			date = LocalDate.now();
		}

		return date;

	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
