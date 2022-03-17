package pl.streamsoft.szkolenie.waluty.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.exceptions.ExchangeException;
import pl.streamsoft.szkolenie.waluty.data.objects.RatesSeries;
import pl.streamsoft.szkolenie.waluty.data.objects.RatesSeries.Rate;
import pl.streamsoft.szkolenie.waluty.data.sources.SourceStrategy;

public class ObjectService {

	private RatesSeries series;

	private LocalDate date;
	private Currency currency;

	private int indexOfDate = -1;
	private int indexOfCode = -1;

	public ObjectService(SourceStrategy source, RatesSeries series) {
		this.series = series;

		this.date = source.getDate();
		this.currency = source.getCurrency();
		setIndexOfDate();
		// setIndexOfCode();
	}

//	private void setIndexOfDate() {
//		RatesSeries ratesSeries = series.getRates();
//		for (RatesSeries serie : ratesSeries) {
//			if (serie.getEffectiveDate().equals(date)) {
//				this.indexOfDate = ratesSeries.indexOf(serie);
//				break;
//			}
//		}
//	}

	private void setIndexOfDate() {

		List<Rate> rates = series.getRates();
		for (Rate rate : rates) {
			if (rate.getEffectiveDate().equals(date)) {
				this.indexOfDate = rates.indexOf(rate);

			}
		}
	}

	public BigDecimal getRate() {
		if (indexOfDate == -1) {
			throw new ExchangeException("Brak wskazanej waluty w pliku!");
		} else {
			BigDecimal rate = series.getRates().get(indexOfDate).getMid();
			return rate;
		}
	}

	public Currency getCurrency() {
		return currency;
	}

	public LocalDate getDate() {
		LocalDate newDate = series.getRates().get(indexOfDate).getEffectiveDate();
		return newDate;
	}
}
