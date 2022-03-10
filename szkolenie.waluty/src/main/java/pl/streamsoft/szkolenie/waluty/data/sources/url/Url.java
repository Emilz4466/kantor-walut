package pl.streamsoft.szkolenie.waluty.data.sources.url;

import java.time.LocalDate;

import pl.streamsoft.szkolenie.waluty.data.Currency;

public class Url {
	private final Currency currency;
	private final LocalDate date;
	private final String format;
	
	public Url(Currency currency, LocalDate date, String format) {
		this.currency = currency;
		this.date = date;
		this.format = format;
	}
	
	public Currency getCurrency() {
		return currency;
	}
	public LocalDate getDate() {
		return date;
	}
	public String getFormat() {
		return format;
	}
	
	public String getUrl() {
		return "https://api.nbp.pl/api/exchangerates/rates/a/" + this.currency + "/" + this.date + "?format=" + this.format;
	}
}
