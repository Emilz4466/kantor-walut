package nowewaluty.sources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

import nowewaluty.Currency;
import nowewaluty.objects.RateData;
import nowewaluty.objects.RatesSeries;
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

	private StringBuffer response() throws IOException {
		URL urlConnector = new URL(setUrl().getUrl());
		HttpURLConnection connection;
		connection = (HttpURLConnection) urlConnector.openConnection();
		connection.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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
			return response().toString();
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

}
