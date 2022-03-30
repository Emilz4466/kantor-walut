package nowewaluty.parsers.deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import nowewaluty.objects.RatesSeries;
import nowewaluty.objects.RatesSeries.Rate;

@SuppressWarnings("serial")
public class JsonDeserializer extends StdDeserializer<RatesSeries> {
	public JsonDeserializer() {
		this(null);
	}

	public JsonDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public RatesSeries deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {

		JsonNode currencyRatesNode = (JsonNode) p.readValueAsTree();

		RatesSeries rateSeries = new RatesSeries();

		rateSeries.setTable(currencyRatesNode.get("table").textValue());
		rateSeries.setCode(currencyRatesNode.get("code").textValue());

		List<Rate> rates = new ArrayList<>();

		for (int j = 0; j < currencyRatesNode.get("rates").size(); j++) {
			Rate rate = new Rate();

			rate.setEffectiveDate(currencyRatesNode.get("rates").get(j).get("effectiveDate").textValue());
			rate.setMid(currencyRatesNode.get("rates").get(j).get("mid").decimalValue().toString());

			rates.add(rate);
		}

		rateSeries.setRates(rates);

		// currencyRates.setRatesSeries(ratesSeries);

		return rateSeries;
	}
}
