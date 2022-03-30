package nowewaluty.sources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import nowewaluty.Currency;
import nowewaluty.objects.RateData;
import nowewaluty.objects.RatesSeries;
import nowewaluty.strategies.ParserStrategy;
import nowewaluty.strategies.SourceStrategy;

public class FileSource implements SourceStrategy {

	private Currency currency;
	private LocalDate date;

	private String path;

	public FileSource() {
	}

	public FileSource(Currency currency, LocalDate date, String path) {
		super();
		this.currency = currency;
		this.date = date;
		this.path = path;
	}

	public String getResponse() {

		String response;

		try {
			response = new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			response = null;
		}

		return response;
	}

	@Override
	public RateData getRate(ParserStrategy parser) {

		if (parser.getRatesSeries(getResponse()) == null) {
			return null;

		} else {
			RatesSeries series = parser.getRatesSeries(getResponse());
			RateData rateData = new RateData(series, date, currency);
			if (rateData.getRate() == null) {
				return null;
			} else {
				return rateData;
			}
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
