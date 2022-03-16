package pl.streamsoft.szkolenie.waluty.data.sources;

import java.math.BigDecimal;
import java.time.LocalDate;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;

public interface SourceStrategy {

	Currency getCurrency();

	LocalDate getDate();

	String getFormat();

	void setCurrency(Currency currency);

	void setDate(LocalDate date);

	void setFormat(String format);

	String getResponse() throws NoDataException;

	BigDecimal getRate();

}
