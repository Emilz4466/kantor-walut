package pl.streamsoft.szkolenie.waluty.data.sources.url;

import java.time.LocalDate;


public class Url {
	private final String currency;
	private final LocalDate date;
	private final String format;
	
	public Url(String currency, LocalDate date, String format) {
		this.currency = currency;
		this.date = date;
		this.format = format;
	}
	
	public String getCurrency() {
		return currency;
	}
	public LocalDate getDate() {
		return date;
	}
	public String getFormat() {
		return format;
	}
	
	public String getUrl() {
		return "https://api.nbp.pl/api/exchangerates/rates/a/" + this.currency + "/" + this.date + "/?format=" + this.format;
	}
}
