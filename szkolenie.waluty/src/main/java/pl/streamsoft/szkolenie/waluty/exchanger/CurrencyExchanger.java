package pl.streamsoft.szkolenie.waluty.exchanger;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.LinkedList;
import java.util.List;

import pl.streamsoft.szkolenie.waluty.data.ConnectionMethod;
import pl.streamsoft.szkolenie.waluty.data.exceptions.ExchangeException;
import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;
import pl.streamsoft.szkolenie.waluty.data.parser.ParserStrategy;
import pl.streamsoft.szkolenie.waluty.data.service.ObjectService;
import pl.streamsoft.szkolenie.waluty.data.sources.SourceStrategy;
import pl.streamsoft.szkolenie.waluty.data.sources.cache.CacheSource;

public class CurrencyExchanger {

	private List<ConnectionMethod> connections;

	public CurrencyExchanger() {
	}

	private List<BigDecimal> setRate() throws NoDataException {

		List<BigDecimal> rates = new LinkedList<>();
		CacheWriter cacheWriter = new CacheWriter(connections);
		this.connections = cacheWriter.getConnectionsWithCache();

		for (int i = 0; i < connections.size(); i++) {
			ConnectionMethod method = connections.get(i);

			if (connections.get(i).getSource() instanceof CacheSource) {
				CacheSource source = (CacheSource) method.getSource();

				// TODO naprawic pobranie rate po kluczu
				BigDecimal newRate = source.getRate();

				rates.add(newRate);

			} else {
				SourceStrategy source = method.getSource();
				ParserStrategy parser = method.getParser();

				ObjectService objectService = new ObjectService(source, parser.getCurrencyObject(source));
				BigDecimal newRate = objectService.getRate();

				rates.add(newRate);
			}

		}

		return rates;
	}

	private List<BigDecimal> getCalculateRate(BigDecimal value) throws NoDataException {

		List<BigDecimal> rates = setRate();
		List<BigDecimal> exchangeRates = new LinkedList<>();

		BigDecimal divider = new BigDecimal(1);

		for (BigDecimal rate : rates) {
			BigDecimal invertedRate = divider.divide(rate, MathContext.DECIMAL128);
			exchangeRates.add(value.multiply(invertedRate));
		}

		return exchangeRates;
	}

	public List<BigDecimal> exchange(List<ConnectionMethod> connections, BigDecimal value) {

		this.connections = connections;

		try {
			return getCalculateRate(value);
		} catch (NoDataException e) {
			throw new ExchangeException("Błąd");
		}

	}
}
