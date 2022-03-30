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
public class XmlDeserializer extends StdDeserializer<RatesSeries> {

	public XmlDeserializer() {
		this(null);
	}

	public XmlDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public RatesSeries deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {

		JsonNode currencyRatesNode = p.getCodec().readTree(p);

		RatesSeries rateSeries = new RatesSeries();

		rateSeries.setTable(currencyRatesNode.get("Table").textValue());
		rateSeries.setCode(currencyRatesNode.get("Code").textValue());

		List<Rate> rates = new ArrayList<>();

		if (currencyRatesNode.get("Rates").get("Rate").size() == 3) {
			Rate rate = new Rate();
			rate.setMid(currencyRatesNode.get("Rates").get("Rate").get("Mid").textValue());
			rate.setEffectiveDate(currencyRatesNode.get("Rates").get("Rate").get("EffectiveDate").textValue());

			rates.add(rate);
		} else {
			for (int j = 0; j < currencyRatesNode.get("Rates").get("Rate").size(); j++) {
				Rate rate = new Rate();
				rate.setMid(currencyRatesNode.get("Rates").get("Rate").get(j).get("Mid").textValue());
				rate.setEffectiveDate(
						currencyRatesNode.get("Rates").get("Rate").get(j).get("EffectiveDate").textValue());

				rates.add(rate);
			}
		}

		rateSeries.setRates(rates);

		// currencyRates.setRatesSeries(ratesSeries);

		return rateSeries;
	}
}
