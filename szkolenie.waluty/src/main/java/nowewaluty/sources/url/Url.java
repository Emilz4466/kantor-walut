package nowewaluty.sources.url;

import java.time.LocalDate;

import nowewaluty.Currency;

public class Url {
	private Currency currency;
	private LocalDate date;
	private String format;

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
		return "https://api.nbp.pl/api/exchangerates/rates/a/" + this.currency.getCode() + "/" + this.date + "/?format="
				+ this.format;
	}

}
