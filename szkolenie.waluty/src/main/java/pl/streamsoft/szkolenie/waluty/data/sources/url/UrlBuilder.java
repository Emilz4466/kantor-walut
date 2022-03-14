package pl.streamsoft.szkolenie.waluty.data.sources.url;

import java.time.LocalDate;


public class UrlBuilder {

	private String currency;
	private LocalDate date;
	private String format;
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
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
