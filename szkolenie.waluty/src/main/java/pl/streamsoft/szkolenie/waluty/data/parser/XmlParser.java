package pl.streamsoft.szkolenie.waluty.data.parser;

import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;
import pl.streamsoft.szkolenie.waluty.data.objects.ExchangeRatesSeries;

public class XmlParser implements ParserStrategy {

	private String response;
	private ExchangeRatesSeries exchangeRatesSeries = new ExchangeRatesSeries();

	private void getObjectFromApi() throws NoDataException {

		XmlMapper mapper = new XmlMapper();

		try {
			this.exchangeRatesSeries = mapper.readValue(response, ExchangeRatesSeries.class);
		} catch (JsonProcessingException e) {
			this.exchangeRatesSeries = null;
		}

	}

	@Override
	public String getFormat() {
		return "xml";
	}

	@Override
	public BigDecimal getRate(String response) throws NoDataException {
		this.response = response;
		getObjectFromApi();
		return exchangeRatesSeries.getRates().get(0).getMid();
	}

}
