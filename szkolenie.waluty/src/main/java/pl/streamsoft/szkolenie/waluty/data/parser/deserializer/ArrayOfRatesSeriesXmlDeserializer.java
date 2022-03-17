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

public class ArrayOfRatesSeriesXmlDeserializer extends StdDeserializer<RatesSeries> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArrayOfRatesSeriesXmlDeserializer() {
		this(null);
	}

	public ArrayOfRatesSeriesXmlDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public RatesSeries deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {

		JsonNode currencyRatesNode = p.getCodec().readTree(p);

		RatesSeries rateSeries = new RatesSeries();

		rateSeries.setTable(currencyRatesNode.get("Table").textValue());
		rateSeries.setCode(currencyRatesNode.get("Code").textValue());

		List<Rate> rates = new ArrayList<>();

		for (int j = 0; j < currencyRatesNode.get("Rates").size(); j++) {
			Rate rate = new Rate();

			rate.setEffectiveDate(currencyRatesNode.get("Rates").get(j).get("EffectiveDate").textValue());
			rate.setMid(currencyRatesNode.get("Rates").get(j).get("Mid").decimalValue());

			rates.add(rate);
		}

		rateSeries.setRates(rates);

		// currencyRates.setRatesSeries(ratesSeries);

		return rateSeries;
	}

}
