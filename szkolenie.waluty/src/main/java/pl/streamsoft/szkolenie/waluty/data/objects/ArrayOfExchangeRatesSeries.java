package pl.streamsoft.szkolenie.waluty.data.objects;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "ArrayOfExchangeRatesSeries")
public class ArrayOfExchangeRatesSeries {
	@JacksonXmlElementWrapper(localName = "ExchangeRatesTable")
	private ExchangeRatesSeries array;
}
