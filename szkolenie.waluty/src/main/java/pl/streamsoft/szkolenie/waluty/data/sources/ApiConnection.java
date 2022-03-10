package pl.streamsoft.szkolenie.waluty.data.sources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

import pl.streamsoft.szkolenie.waluty.data.sources.url.Url;
import pl.streamsoft.szkolenie.waluty.data.sources.url.UrlBuilder;

public class ApiConnection {
	
	private Url url;
	private UrlBuilder urlBuilder;
	
	
	
	ApiConnection(Url url) {
		this.url = url;
		dateValidator();
	}
	
	private Boolean isDataAvailable() {
		
		try {
			URL urlConnector = new URL(url.getUrl());
			HttpURLConnection connection;
			connection = (HttpURLConnection) urlConnector.openConnection();
			connection.setRequestMethod("GET");
			return true;
		
		} catch (IOException e) {
			return false;
		}
		
	}
	
	private void dateValidator() {
		LocalDate givenDate = url.getDate();
		LocalDate lastCurrency = LocalDate.of(2002, 01, 02);
		
		if(givenDate.isBefore(lastCurrency)) {
			givenDate = lastCurrency;
		}
		else if(givenDate.isBefore(LocalDate.now())) {
			givenDate = LocalDate.now();
		}
		
		urlBuilder.setDate(givenDate);
		urlBuilder.setCurrency(url.getCurrency());
		urlBuilder.setFormat(url.getFormat());
		
		while(!isDataAvailable()) {
			givenDate = givenDate.minusDays(1);
			urlBuilder.setDate(givenDate);
			url = urlBuilder.buildUrl();
		}
	}
	
	private StringBuffer response() throws IOException {
		
		URL urlConnector = new URL(url.getUrl());
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
}
