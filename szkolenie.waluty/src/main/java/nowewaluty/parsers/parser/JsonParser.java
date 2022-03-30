package nowewaluty.parsers.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import nowewaluty.objects.RatesSeries;
import nowewaluty.parsers.deserializer.JsonDeserializer;
import nowewaluty.strategies.ParserStrategy;

public class JsonParser implements ParserStrategy {
	private String response;
	private RatesSeries exchangeRatesSeries = new RatesSeries();

	private void getObjectFromApi() {

		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();

		module.addDeserializer(RatesSeries.class, new JsonDeserializer());

		mapper.registerModule(module);

		if (response == null) {
			this.exchangeRatesSeries = null;
		}

		try {
			this.exchangeRatesSeries = mapper.readValue(response, RatesSeries.class);
		} catch (JsonProcessingException e) {
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
		return "json";
	}

}
