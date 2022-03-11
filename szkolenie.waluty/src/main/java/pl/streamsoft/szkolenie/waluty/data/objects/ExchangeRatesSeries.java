package pl.streamsoft.szkolenie.waluty.data.objects;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import pl.streamsoft.szkolenie.waluty.data.Currency;
@JacksonXmlRootElement(localName = "ExchangeRatesSeries")
public class ExchangeRatesSeries {
	@JacksonXmlElementWrapper(localName = "Table")
	private char table;
	@JacksonXmlElementWrapper(localName = "Country")
	private String country = null;
	@JacksonXmlElementWrapper(localName = "Symbol")
	private String symbol = null;
	@JacksonXmlElementWrapper(localName = "Currency")
	private String currency = null;
	@JacksonXmlElementWrapper(localName = "Code")
	private Currency code = null;
	@JacksonXmlElementWrapper(localName = "No")
	private String no = null;
	@JacksonXmlElementWrapper(localName = "Rates")
	private List<Rate> rates;
	
	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}
	public char getTable() {
		return table;
	}
	public String getCountry() {
		return country;
	}
	public String getSymbol() {
		return symbol;
	}
	public String getCurrency() {
		return currency;
	}
	public Currency getCode() {
		return code;
	}
	public String getNo() {
		return no;
	}
	public List<Rate> getRates() {
		return rates;
	}
}
