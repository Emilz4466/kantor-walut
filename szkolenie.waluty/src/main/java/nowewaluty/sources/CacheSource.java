package nowewaluty.sources;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import nowewaluty.Currency;
import nowewaluty.objects.RateData;
import nowewaluty.sources.cache_key.Key;
import nowewaluty.strategies.ParserStrategy;
import nowewaluty.strategies.SourceStrategy;

public class CacheSource implements SourceStrategy {
	Map<Key, BigDecimal> cacheMap = new HashMap<>();

	private Currency currency;
	private LocalDate date;

	public CacheSource() {
	}

	public CacheSource(Currency currency, LocalDate date) {
		this.currency = currency;
		this.date = date;
	}

	@Override
	public void saveRate(RateData rate) {
		Key key = new Key(rate.getCurrency(), rate.getDate());
		this.cacheMap.put(key, rate.getRate());
	}

	@Override
	public RateData getRate(ParserStrategy parser) {
		Key key = new Key(currency, date);
		if (cacheMap.containsKey(key)) {
			RateData rateData = new RateData(date, currency, cacheMap.get(key));
			return rateData;
		} else {
			return null;
		}
	}

	@Override
	public LocalDate getDate() {
		return date;
	}

	@Override
	public void setDate(LocalDate date) {
		this.date = date;
	}

}
