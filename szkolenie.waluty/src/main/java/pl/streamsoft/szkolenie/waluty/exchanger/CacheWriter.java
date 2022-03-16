package pl.streamsoft.szkolenie.waluty.exchanger;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import pl.streamsoft.szkolenie.waluty.data.ConnectionMethod;
import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;
import pl.streamsoft.szkolenie.waluty.data.parser.ParserStrategy;
import pl.streamsoft.szkolenie.waluty.data.sources.SourceStrategy;
import pl.streamsoft.szkolenie.waluty.data.sources.cache.CacheSource;

public class CacheWriter {

	List<ConnectionMethod> connections;

	public CacheWriter(List<ConnectionMethod> connections) throws NoDataException {
		this.connections = connections;
		dataOrganization();
		write();

	}

	private void dataOrganization() {
		if (connections.get(0).getSource() instanceof CacheSource) {
			Collections.swap(connections, 0, 1);
		}
	}

	private void write() throws NoDataException {
		for (int i = 0; i < connections.size(); i++) {
			if (connections.get(i).getSource() instanceof CacheSource) {

				ConnectionMethod previousMethod = connections.get(i - 1);
				SourceStrategy previousSource = previousMethod.getSource();
				ParserStrategy previousParser = previousMethod.getParser();

				CacheSource cacheSource = new CacheSource();

				BigDecimal nextRate = previousParser.getRate(previousSource.getResponse());

				cacheSource.putNewData(previousSource.getCurrency(), previousSource.getDate(), nextRate);

			}
		}
	}
}
