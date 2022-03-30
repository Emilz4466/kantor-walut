package nowewaluty.parsers.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import nowewaluty.objects.RatesSeries;
import nowewaluty.parsers.deserializer.XmlDeserializer;
import nowewaluty.strategies.ParserStrategy;

public class XmlParser implements ParserStrategy {
	private String response;
	private RatesSeries exchangeRatesSeries = new RatesSeries();

	private void getObjectFromApi() {

		XmlMapper mapper = new XmlMapper();
		SimpleModule module = new SimpleModule();

		module.addDeserializer(RatesSeries.class, new XmlDeserializer());

		mapper.registerModule(module);

		if (response == null) {
			this.exchangeRatesSeries = null;
		}

		try {
			this.exchangeRatesSeries = mapper.readValue(response, RatesSeries.class);
		} catch (JsonProcessingException | IllegalArgumentException e) {
			this.exchangeRatesSeries = null;
		}
	}

	@Override
	public RatesSeries getRatesSeries(String response) {
		this.response = response;
		getObjectFromApi();
		return exchangeRatesSeries;
	}

	@Override
	public String getFormat() {
		return "xml";
	}

}
