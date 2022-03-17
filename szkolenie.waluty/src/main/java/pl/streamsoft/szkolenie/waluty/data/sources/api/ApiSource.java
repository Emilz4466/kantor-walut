package pl.streamsoft.szkolenie.waluty.data.sources.api;

import java.math.BigDecimal;
import java.time.LocalDate;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;
import pl.streamsoft.szkolenie.waluty.data.service.ApiService;
import pl.streamsoft.szkolenie.waluty.data.service.ServiceStrategy;
import pl.streamsoft.szkolenie.waluty.data.sources.SourceStrategy;

public class ApiSource implements SourceStrategy {

	private Currency currency;
	private LocalDate date;
	private String format;

	public ApiSource(Currency currency, LocalDate date) {
		this.currency = currency;
		this.date = date;
	}

	@Override
	public Currency getCurrency() {
		return currency;
	}

	@Override
	public LocalDate getDate() {
		return date;
	}

	@Override
	public String getFormat() {
		return format;
	}

	@Override
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public String getResponse() throws NoDataException {
		ServiceStrategy service = new ApiService(currency, date, format);
		String response = service.getResponse();
		return response;
	}

	@Override
	public BigDecimal getRate() {
		return null;
	}

}
