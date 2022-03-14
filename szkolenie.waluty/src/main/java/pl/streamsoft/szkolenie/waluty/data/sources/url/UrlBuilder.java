package pl.streamsoft.szkolenie.waluty.data.sources.url;

import java.time.LocalDate;

import pl.streamsoft.szkolenie.waluty.data.Currency;

public class UrlBuilder {

	private Currency currency;
	private LocalDate date;
	private String format;
	
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
	public Url buildUrl() {
		return new Url(currency, date, format);
	}
}
