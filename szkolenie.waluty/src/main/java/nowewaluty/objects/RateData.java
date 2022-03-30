package nowewaluty.objects;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import nowewaluty.Currency;
import nowewaluty.objects.RatesSeries.Rate;

public class RateData {
	private LocalDate searchDate;
	private Currency searchCurrency;

	private LocalDate date;
	private Currency currency;
	private BigDecimal rate;

	private RatesSeries series;

	private int indexOfDate = -1;

	public RateData(RatesSeries series, LocalDate searchDate, Currency searchCurrency) {
		super();

		this.series = series;

		this.searchDate = searchDate;
		this.searchCurrency = searchCurrency;
		correctDateSearcher();

		if (indexOfDate == -1) {
			this.currency = null;
			this.date = null;
			this.rate = null;
		} else {
			this.currency = series.getCode();
			this.date = series.getRates().get(indexOfDate).getEffectiveDate();
			this.rate = series.getRates().get(indexOfDate).getMid();
		}
	}

	public RateData(LocalDate searchDate, Currency searchCurrency, BigDecimal rate) {
		this.date = searchDate;
		this.currency = searchCurrency;
		this.rate = rate;
	}

	private void correctDateSearcher() {
		List<Rate> rates = series.getRates();
		for (Rate rate : rates) {
			if (rate.getEffectiveDate().equals(searchDate)) {
				this.indexOfDate = rates.indexOf(rate);
			}
		}
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public LocalDate getSearchDate() {
		return searchDate;
	}

	public Currency getSearchCurrency() {
		return searchCurrency;
	}

	public LocalDate getDate() {
		return date;
	}

	public Currency getCurrency() {
		return currency;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public int getIndexOfDate() {
		return indexOfDate;
	}

}
