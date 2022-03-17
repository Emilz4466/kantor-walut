package pl.streamsoft.szkolenie.waluty.data.parser.deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import pl.streamsoft.szkolenie.waluty.data.objects.RatesSeries;
import pl.streamsoft.szkolenie.waluty.data.objects.RatesSeries.Rate;

public class ArrayOfRatesSeriesJsonDeserializer extends StdDeserializer<RatesSeries> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArrayOfRatesSeriesJsonDeserializer() {
		this(null);
	}

	public ArrayOfRatesSeriesJsonDeserializer(Class<?> vc) {
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
			rate.setMid(currencyRatesNode.get("rates").get(j).get("mid").decimalValue());

			rates.add(rate);
		}

		rateSeries.setRates(rates);

		// currencyRates.setRatesSeries(ratesSeries);

		return rateSeries;
	}

}
