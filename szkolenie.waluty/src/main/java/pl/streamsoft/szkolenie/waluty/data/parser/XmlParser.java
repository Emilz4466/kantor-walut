package pl.streamsoft.szkolenie.waluty.data.parser;

import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import pl.streamsoft.szkolenie.waluty.data.objects.ExchangeRatesSeries;
import pl.streamsoft.szkolenie.waluty.data.sources.ResponseStrategy;

public class XmlParser implements ParserStrategy{
	
	private ResponseStrategy response;
	private ExchangeRatesSeries exchangeRatesSeries = new ExchangeRatesSeries();

	private void getObjectFromApi() {

		XmlMapper mapper = new XmlMapper();
				
		try {
			this.exchangeRatesSeries = mapper.readValue(response.getResponse(), ExchangeRatesSeries.class);
		} catch (JsonProcessingException e) {
			this.exchangeRatesSeries = null;
		}
		
	}
	
	@Override
	public String getFormat() {
		return "xml";
	}

	@Override
	public BigDecimal getRate(ResponseStrategy response) {
		this.response = response;
		getObjectFromApi();
		return exchangeRatesSeries.getRates().get(0).getMid();	
	}

}
