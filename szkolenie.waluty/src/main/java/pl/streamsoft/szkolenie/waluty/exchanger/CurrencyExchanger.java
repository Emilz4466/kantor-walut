package pl.streamsoft.szkolenie.waluty.exchanger;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.parser.ParserStrategy;
import pl.streamsoft.szkolenie.waluty.data.sources.ApiConnection;
import pl.streamsoft.szkolenie.waluty.data.sources.url.Url;
import pl.streamsoft.szkolenie.waluty.data.sources.url.UrlBuilder;

public class CurrencyExchanger {
	
	private Currency currency;
	private LocalDate date;
	
	private ParserStrategy parser;
	
	public CurrencyExchanger() {}
	
	private String setFormat() {	
		return parser.getFormat();
	}
	
	
	private Url setUrl() {
		
		UrlBuilder builder = new UrlBuilder();
		builder.setCurrency(currency);
		builder.setDate(date);
		builder.setFormat(setFormat());
		
		return builder.buildUrl();
	}
	
	
	public BigDecimal exchange(Currency currency, LocalDate date, BigDecimal value, ParserStrategy parser) {
		
		this.currency = currency;
		this.date = date;
		this.parser = parser;
		
		ApiConnection connection = new ApiConnection(setUrl());
		
		BigDecimal rate = parser.getRate(connection);
		BigDecimal divider = new BigDecimal(1);
		
		BigDecimal invertedRate = divider.divide(rate, MathContext.DECIMAL128);
		
		return value.multiply(invertedRate);
		
	}
}
