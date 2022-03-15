package pl.streamsoft.szkolenie.waluty.data.sources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;
import pl.streamsoft.szkolenie.waluty.data.sources.url.DateValidator;
import pl.streamsoft.szkolenie.waluty.data.sources.url.Url;
import pl.streamsoft.szkolenie.waluty.data.sources.url.UrlBuilder;

public class ApiConnection implements ResponseStrategy {

	private Url url;
	UrlBuilder builder = new UrlBuilder();

	private Currency currency;
	private LocalDate date;
	private String format;

	public ApiConnection() {
	}

	public ApiConnection(Currency currency, LocalDate date) {

		this.currency = currency;
		this.date = date;

	}

	public Currency getCurrency() {
		return currency;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getFormat() {
		return format;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public void setFormat(String format) {
		this.format = format;
	}

	private Url setUrl() {

		DateValidator dateValidator = new DateValidator(date);

		builder.setCurrency(currency);
		builder.setDate(dateValidator.dateValidation());
		builder.setFormat(format);

		return builder.buildUrl();
	}

	private HttpURLConnection dataValidator(HttpURLConnection connection) throws IOException {
		LocalDate givenDate = date;
		HttpURLConnection newConnection = connection;

		for (int i = 0; i <= 20; i++) {
			if (newConnection.getResponseCode() >= 400) {
				givenDate = givenDate.minusDays(1);
				builder.setDate(givenDate);
				url = builder.buildUrl();
				URL urlConnector = new URL(url.getUrl());
				newConnection = (HttpURLConnection) urlConnector.openConnection();
				newConnection.setRequestMethod("GET");

			} else {
				break;
			}

		}

		return newConnection;
	}

	private StringBuffer response() throws IOException {

		URL urlConnector = new URL(setUrl().getUrl());
		HttpURLConnection connection;
		connection = (HttpURLConnection) urlConnector.openConnection();
		connection.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(dataValidator(connection).getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response;
	}

	@Override
	public String getResponse() throws NoDataException {
		try {
			return response().toString();
		} catch (IOException e) {
			throw new NoDataException("Brak danych pod wskazaną datą!", e);
		}
	}
}
