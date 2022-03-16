package pl.streamsoft.szkolenie.waluty.data.sources.cache;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;
import pl.streamsoft.szkolenie.waluty.data.sources.SourceStrategy;

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

	public void putNewData(Currency currencySetter, LocalDate dateSetter, BigDecimal rateSetter) {
		Key newKey = new Key(currencySetter, dateSetter);
		this.cacheMap.put(newKey, rateSetter);
	}

	@Override
	public Currency getCurrency() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCurrency(Currency currency) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDate(LocalDate date) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFormat(String format) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getResponse() throws NoDataException {
		return null;
	}

	@Override
	public BigDecimal getRate() {
		BigDecimal rate = cacheMap.get(new Key(currency, date));
		return rate;
	}

}
