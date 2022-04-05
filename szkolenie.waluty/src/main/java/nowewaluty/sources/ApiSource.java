package nowewaluty.sources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import nowewaluty.Currency;
import nowewaluty.objects.RateData;
import nowewaluty.objects.RatesSeries;
import nowewaluty.objects.RatesSeries.Rate;
import nowewaluty.sources.url.DateValidator;
import nowewaluty.sources.url.Url;
import nowewaluty.sources.url.UrlBuilder;
import nowewaluty.strategies.ParserStrategy;
import nowewaluty.strategies.SourceStrategy;

public class ApiSource implements SourceStrategy {
	// private Url url;
	UrlBuilder builder = new UrlBuilder();

	private Currency currency;
	private LocalDate date;
	private String format;

	public ApiSource() {
	}

	public ApiSource(Currency currency, LocalDate date) {

		this.currency = currency;
		this.date = date;

	}

	private Url setUrl() throws IOException {

		DateValidator dateValidator = new DateValidator(date);

		builder.setCurrency(currency);
		builder.setDate(dateValidator.dateValidation());
		builder.setFormat(format);

		return builder.buildUrl();
	}

	private StringBuffer response(int urlStrategy, Currency code, LocalDate date1, LocalDate date2) throws IOException {
		URL urlConnector = null;
		if (urlStrategy == 0) {
			urlConnector = new URL(setUrl().getUrl());
		} else if (urlStrategy == 1) {
			Url urlForYearRates = new Url();
			urlConnector = new URL(urlForYearRates.getUrlForDb(code, date1, date2));
		}
		HttpURLConnection connection;
		BufferedReader in = null;
		try {
			connection = (HttpURLConnection) urlConnector.openConnection();
			connection.setRequestMethod("GET");

			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (FileNotFoundException e) {
			return null;
		}
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response;
	}

	public String getResponse() {
		try {
			return response(0, null, null, null).toString();
		} catch (IOException e) {
			return null;
		}
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public LocalDate getDate() {
		return date;
	}

	@Override
	public RateData getRate(ParserStrategy parser) {
		this.format = parser.getFormat();
		if (getResponse() == null || parser.getRatesSeries(getResponse()) == null) {
			return null;
		} else {
			RatesSeries series = parser.getRatesSeries(getResponse());
			RateData rateData = new RateData(series, date, currency);
			return rateData;
		}
	}

	public RatesSeries getRates(ParserStrategy parser, Currency code, LocalDate date1, LocalDate date2)
			throws Exception {
		RatesSeries series = null;
		if (response(1, code, date1, date2) == null) {
			return null;
		} else {
			series = parser.getRatesSeries(response(1, code, date1, date2).toString());
		}
		return series;

	}

}
