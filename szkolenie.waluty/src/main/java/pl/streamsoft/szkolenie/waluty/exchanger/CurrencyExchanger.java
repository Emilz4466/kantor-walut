package pl.streamsoft.szkolenie.waluty.exchanger;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;

import pl.streamsoft.szkolenie.waluty.data.Currency;
import pl.streamsoft.szkolenie.waluty.data.parser.ParserStrategy;
import pl.streamsoft.szkolenie.waluty.data.sources.ApiConnection;
import pl.streamsoft.szkolenie.waluty.data.sources.FileConnection;
import pl.streamsoft.szkolenie.waluty.data.sources.ResponseStrategy;
import pl.streamsoft.szkolenie.waluty.data.sources.url.Url;
import pl.streamsoft.szkolenie.waluty.data.sources.url.UrlBuilder;

public class CurrencyExchanger {
	
	private String currency;
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
	
	private BigDecimal getCalculateRate(ResponseStrategy connection) {
		BigDecimal rate = parser.getRate(connection);
		BigDecimal divider = new BigDecimal(1);
		
		BigDecimal invertedRate = divider.divide(rate, MathContext.DECIMAL128);
		
		return invertedRate;
	}
	
	
	public BigDecimal exchange(Currency currency, LocalDate date, BigDecimal value, ParserStrategy parser) {
		
		this.currency = currency.getCode();
		this.date = date;
		this.parser = parser;
		
		ApiConnection connection = new ApiConnection(setUrl());
		
		return value.multiply(getCalculateRate(connection));
		
	}
	
	public BigDecimal exchange(String path, BigDecimal value, ParserStrategy parser) {
		
		this.parser = parser;
		
		FileConnection connection = new FileConnection(path);
		
		return value.multiply(getCalculateRate(connection));
	}
}
