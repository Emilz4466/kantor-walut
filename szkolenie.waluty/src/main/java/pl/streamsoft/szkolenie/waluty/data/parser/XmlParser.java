package pl.streamsoft.szkolenie.waluty.data.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import pl.streamsoft.szkolenie.waluty.data.exceptions.NoDataException;
import pl.streamsoft.szkolenie.waluty.data.objects.RatesSeries;
import pl.streamsoft.szkolenie.waluty.data.parser.deserializer.ArrayOfRatesSeriesXmlDeserializer;
import pl.streamsoft.szkolenie.waluty.data.sources.SourceStrategy;

public class XmlParser implements ParserStrategy {

	private String response;
	private RatesSeries exchangeRatesSeries = new RatesSeries();

	private void getObjectFromApi() throws NoDataException {

		XmlMapper mapper = new XmlMapper();
		SimpleModule module = new SimpleModule();

		module.addDeserializer(RatesSeries.class, new ArrayOfRatesSeriesXmlDeserializer());

		mapper.registerModule(module);

		try {
			this.exchangeRatesSeries = mapper.readValue(response, RatesSeries.class);
		} catch (JsonProcessingException e) {
			this.exchangeRatesSeries = null;
		}
	}

	@Override
	public RatesSeries getCurrencyObject(SourceStrategy source) throws NoDataException {
		this.response = source.getResponse();
		getObjectFromApi();
		return exchangeRatesSeries;
	}

	@Override
	public String getFormat() {
		return "json";
	}

}
