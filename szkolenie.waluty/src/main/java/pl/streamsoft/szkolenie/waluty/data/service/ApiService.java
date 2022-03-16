package pl.streamsoft.szkolenie.waluty.data.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;
import pl.streamsoft.szkolenie.waluty.data.sources.api.url.DateValidator;
import pl.streamsoft.szkolenie.waluty.data.sources.api.url.Url;
import pl.streamsoft.szkolenie.waluty.data.sources.api.url.UrlBuilder;

public class ApiService implements ServiceStrategy {

	private Url url;
	UrlBuilder builder = new UrlBuilder();

	private Currency currency;
	private LocalDate date;
	private String format;

	public ApiService() {
	}

	public ApiService(Currency currency, LocalDate date, String format) {

		this.currency = currency;
		this.date = date;
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
		LocalDate givenDate = builder.getDate();
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
