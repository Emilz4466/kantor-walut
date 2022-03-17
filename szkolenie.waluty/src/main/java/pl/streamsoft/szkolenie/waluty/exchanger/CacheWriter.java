package pl.streamsoft.szkolenie.waluty.exchanger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import pl.streamsoft.szkolenie.waluty.data.ConnectionMethod;
import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;
import pl.streamsoft.szkolenie.waluty.data.parser.ParserStrategy;
import pl.streamsoft.szkolenie.waluty.data.service.ObjectService;
import pl.streamsoft.szkolenie.waluty.data.sources.SourceStrategy;
import pl.streamsoft.szkolenie.waluty.data.sources.cache.CacheSource;

public class CacheWriter {

	List<ConnectionMethod> connections;

	public CacheWriter(List<ConnectionMethod> connections) throws NoDataException {
		this.connections = connections;

	}

	private void dataOrganization() {
		if (connections.get(0).getSource() instanceof CacheSource) {
			Collections.swap(connections, 0, 1);
		}
	}

	private void write() throws NoDataException {
		dataOrganization();
		CacheSource cacheSource = new CacheSource();
		for (int i = 0; i < connections.size(); i++) {
			if (connections.get(i).getSource() instanceof CacheSource) {

				ConnectionMethod previousMethod = connections.get(i - 1);
				SourceStrategy previousSource = previousMethod.getSource();
				ParserStrategy previousParser = previousMethod.getParser();

				ObjectService objectService = new ObjectService(previousSource,
						previousParser.getCurrencyObject(previousSource));

				BigDecimal cacheRate = objectService.getRate();
				LocalDate cacheDate = objectService.getDate();
				Currency code = objectService.getCurrency();

				cacheSource.setCurrency(connections.get(i).getSource().getCurrency());
				cacheSource.setDate(connections.get(i).getSource().getDate());
				cacheSource.putNewData(code, cacheDate, cacheRate);

				this.connections.get(i).setSource(cacheSource);

			}
		}
	}

	public List<ConnectionMethod> getConnectionsWithCache() throws NoDataException {
		write();
		return connections;
	}
}
